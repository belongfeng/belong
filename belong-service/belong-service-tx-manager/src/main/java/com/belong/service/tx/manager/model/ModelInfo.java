package com.belong.service.tx.manager.model;

/**
* @Description:    模块信息
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
public class ModelInfo {

	private String model;

	private String ipAddress;

	private String channelName;

	private String uniqueKey;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getUniqueKey() {
		return uniqueKey;
	}

	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
}
