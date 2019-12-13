package com.belong.service.tx.manager.model;

/**
* @Description:    负载均衡模块信息
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:43
*/
public class LoadBalanceInfo {

	private String groupId;

	private String key;

	private String data;


	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
