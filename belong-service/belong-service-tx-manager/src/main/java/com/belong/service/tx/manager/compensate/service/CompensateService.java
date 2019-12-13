package com.belong.service.tx.manager.compensate.service;

import com.belong.service.tx.manager.compensate.model.TransactionCompensateMsg;
import com.belong.service.tx.manager.compensate.model.TxModel;
import com.belong.service.tx.manager.model.ModelName;
import com.belong.service.tx.manager.netty.model.TxGroup;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:40
*/
public interface CompensateService {

	boolean saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<ModelName> loadModelList();

	List<String> loadCompensateTimes(String model);

	List<TxModel> loadCompensateByModelAndTime(String path);

	void autoCompensate(String compensateKey, TransactionCompensateMsg transactionCompensateMsg);

	boolean executeCompensate(String key) throws ServiceException;

	void reloadCompensate(TxGroup txGroup);

	boolean hasCompensate();

	boolean delCompensate(String path);

	TxGroup getCompensateByGroupId(String groupId);
}
