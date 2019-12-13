package com.belong.service.tx.manager.compensate.dao;

import com.belong.service.tx.manager.compensate.model.TransactionCompensateMsg;

import java.util.List;

/**
* @Description:    
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:38
*/
public interface CompensateDao {

	String saveCompensateMsg(TransactionCompensateMsg transactionCompensateMsg);

	List<String> loadCompensateKeys();

	List<String> loadCompensateTimes(String model);

	List<String> loadCompensateByModelAndTime(String path);

	String getCompensate(String key);

	String getCompensateByGroupId(String groupId);

	void deleteCompensateByPath(String path);

	void deleteCompensateByKey(String key);

	boolean hasCompensate();
}
