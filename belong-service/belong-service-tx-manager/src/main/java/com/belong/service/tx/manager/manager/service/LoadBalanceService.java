package com.belong.service.tx.manager.manager.service;


import com.belong.service.tx.manager.model.LoadBalanceInfo;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
public interface LoadBalanceService {

	boolean put(LoadBalanceInfo loadBalanceInfo);

	LoadBalanceInfo get(String groupId, String key);

	boolean remove(String groupId);

	String getLoadBalanceGroupName(String groupId);

}
