package com.belong.service.tx.manager.redis;


import com.belong.service.tx.manager.netty.model.TxGroup;

import java.util.List;

/**
* @Description:    
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:46
*/
public interface RedisServerService {

	String loadNotifyJson();

	void saveTransaction(String key, String json);

	TxGroup getTxGroupByKey(String key);

	void saveCompensateMsg(String name, String json);

	List<String> getKeys(String key);

	List<String> getValuesByKeys(List<String> keys);

	String getValueByKey(String key);

	void deleteKey(String key);

	void saveLoadBalance(String groupName, String key, String data);


	String getLoadBalance(String groupName, String key);


}
