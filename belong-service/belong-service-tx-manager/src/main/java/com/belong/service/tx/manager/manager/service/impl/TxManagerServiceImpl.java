package com.belong.service.tx.manager.manager.service.impl;


import com.belong.service.tx.manager.compensate.service.CompensateService;
import com.belong.service.tx.manager.config.ConfigReader;
import com.belong.service.tx.manager.manager.ModelInfoManager;
import com.belong.service.tx.manager.manager.service.LoadBalanceService;
import com.belong.service.tx.manager.manager.service.TxManagerSenderService;
import com.belong.service.tx.manager.manager.service.TxManagerService;
import com.belong.service.tx.manager.model.ModelInfo;
import com.belong.service.tx.manager.netty.model.TxGroup;
import com.belong.service.tx.manager.netty.model.TxInfo;
import com.belong.service.tx.manager.redis.RedisServerService;
import com.belong.service.tx.manager.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
@Service
public class TxManagerServiceImpl implements TxManagerService {
	@Autowired
	private ConfigReader configReader;
	@Autowired
	private RedisServerService redisServerService;


	@Autowired
	private TxManagerSenderService transactionConfirmService;


	@Autowired
	private LoadBalanceService loadBalanceService;

	@Autowired
	private CompensateService compensateService;


	private Logger logger = LoggerFactory.getLogger(TxManagerServiceImpl.class);


	@Override
	public TxGroup createTransactionGroup(String groupId) {
		TxGroup txGroup = new TxGroup();
		if (compensateService.getCompensateByGroupId(groupId) != null) {
			txGroup.setIsCompensate(1);
		}

		txGroup.setStartTime(System.currentTimeMillis());
		txGroup.setGroupId(groupId);

		String key = configReader.getKeyPrefix() + groupId;
		redisServerService.saveTransaction(key, txGroup.toJsonString());

		return txGroup;
	}


	@Override
	public TxGroup addTransactionGroup(String groupId, String taskId, int isGroup, String channelAddress, String methodStr) {
		String key = getTxGroupKey(groupId);
		TxGroup txGroup = getTxGroup(groupId);
		if (txGroup == null) {
			return null;
		}
		TxInfo txInfo = new TxInfo();
		txInfo.setChannelAddress(channelAddress);
		txInfo.setKid(taskId);
		txInfo.setAddress(Constants.address);
		txInfo.setIsGroup(isGroup);
		txInfo.setMethodStr(methodStr);


		ModelInfo modelInfo = ModelInfoManager.getInstance().getModelByChannelName(channelAddress);
		if (modelInfo != null) {
			txInfo.setUniqueKey(modelInfo.getUniqueKey());
			txInfo.setModelIpAddress(modelInfo.getIpAddress());
			txInfo.setModel(modelInfo.getModel());
		}

		txGroup.addTransactionInfo(txInfo);

		redisServerService.saveTransaction(key, txGroup.toJsonString());

		return txGroup;
	}

	@Override
	public boolean rollbackTransactionGroup(String groupId) {
		String key = getTxGroupKey(groupId);
		TxGroup txGroup = getTxGroup(groupId);
		if (txGroup == null) {
			return false;
		}
		txGroup.setRollback(1);
		redisServerService.saveTransaction(key, txGroup.toJsonString());
		return true;
	}

	@Override
	public int cleanNotifyTransaction(String groupId, String taskId) {
		int res = 0;
		logger.info("start-cleanNotifyTransaction->groupId:" + groupId + ",taskId:" + taskId);
		String key = getTxGroupKey(groupId);
		TxGroup txGroup = getTxGroup(groupId);
		if (txGroup == null) {
			logger.info("cleanNotifyTransaction - > txGroup is null ");
			return res;
		}

		if (txGroup.getHasOver() == 0) {

			//整个事务回滚.
			txGroup.setRollback(1);
			redisServerService.saveTransaction(key, txGroup.toJsonString());

			logger.info("cleanNotifyTransaction - > groupId " + groupId + " not over,all transaction must rollback !");
			return 0;
		}

		if (txGroup.getRollback() == 1) {
			logger.info("cleanNotifyTransaction - > groupId " + groupId + " only rollback !");
			return 0;
		}

		//更新数据
		boolean hasSet = false;
		for (TxInfo info : txGroup.getList()) {
			if (info.getKid().equals(taskId)) {
				if (info.getNotify() == 0 && info.getIsGroup() == 0) {
					info.setNotify(1);
					hasSet = true;
					res = 1;

					break;
				}
			}
		}

		//判断是否都结束
		boolean isOver = true;
		for (TxInfo info : txGroup.getList()) {
			if (info.getIsGroup() == 0 && info.getNotify() == 0) {
				isOver = false;
				break;
			}
		}

		if (isOver) {
			deleteTxGroup(txGroup);
		}

		//有更新的数据，需要修改记录
		if (!isOver && hasSet) {
			redisServerService.saveTransaction(key, txGroup.toJsonString());
		}

		logger.info("end-cleanNotifyTransaction->groupId:" + groupId + ",taskId:" + taskId + ",res(1:commit,0:rollback):" + res);
		return res;
	}


	@Override
	public int closeTransactionGroup(String groupId, int state) {
		String key = getTxGroupKey(groupId);
		TxGroup txGroup = getTxGroup(groupId);
		if (txGroup == null) {
			return 0;
		}
		txGroup.setState(state);
		txGroup.setHasOver(1);
		redisServerService.saveTransaction(key, txGroup.toJsonString());
		return transactionConfirmService.confirm(txGroup);
	}


	@Override
	public void dealTxGroup(TxGroup txGroup, boolean hasOk) {
		if (hasOk) {
			deleteTxGroup(txGroup);
		}
	}


	@Override
	public void deleteTxGroup(TxGroup txGroup) {
		String groupId = txGroup.getGroupId();

		String key = getTxGroupKey(groupId);
		redisServerService.deleteKey(key);

		loadBalanceService.remove(groupId);
	}


	@Override
	public TxGroup getTxGroup(String groupId) {
		String key = getTxGroupKey(groupId);
		return redisServerService.getTxGroupByKey(key);
	}

	@Override
	public String getTxGroupKey(String groupId) {
		return configReader.getKeyPrefix() + groupId;
	}
}
