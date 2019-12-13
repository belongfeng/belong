package com.belong.service.tx.manager.manager.service.impl;

import com.belong.service.tx.manager.config.ConfigReader;
import com.belong.service.tx.manager.manager.service.MicroService;
import com.belong.service.tx.manager.model.TxServer;
import com.belong.service.tx.manager.model.TxState;
import com.belong.service.tx.manager.utils.Constants;
import com.belong.service.tx.manager.utils.SocketManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
@Service
public class MicroServiceImpl implements MicroService {


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ConfigReader configReader;


	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private Registration registration;

	private boolean isIp(String ipAddress) {
		String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
		Pattern pattern = Pattern.compile(ip);
		Matcher matcher = pattern.matcher(ipAddress);
		return matcher.matches();
	}


	@Override
	public TxState getState() {
		TxState state = new TxState();
		String ipAddress = registration.getHost();
		if (!isIp(ipAddress)) {
			ipAddress = "127.0.0.1";
		}
		state.setIp(ipAddress);
		state.setPort(Constants.socketPort);
		state.setMaxConnection(SocketManager.getInstance().getMaxConnection());
		state.setNowConnection(SocketManager.getInstance().getNowConnection());
		state.setRedisSaveMaxTime(configReader.getRedisSaveMaxTime());
		state.setTransactionNettyDelayTime(configReader.getTransactionNettyDelayTime());
		state.setTransactionNettyHeartTime(configReader.getTransactionNettyHeartTime());
		state.setNotifyUrl(configReader.getCompensateNotifyUrl());
		state.setCompensate(configReader.isCompensateAuto());
		state.setCompensateTryTime(configReader.getCompensateTryTime());
		state.setCompensateMaxWaitTime(configReader.getCompensateMaxWaitTime());
		state.setSlbList(getServices());
		return state;
	}

	private List<String> getServices() {
		List<String> urls = new ArrayList<>();
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(TMKEY);
		for (ServiceInstance instanceInfo : serviceInstances) {
			urls.add(instanceInfo.getUri().toASCIIString());
		}
		return urls;
	}

	@Override
	public TxServer getServer() {
		List<String> urls = getServices();
		List<TxState> states = new ArrayList<>();
		for (String url : urls) {
			try {
				TxState state = restTemplate.getForObject(url + "/tx/manager/state", TxState.class);
				states.add(state);
			} catch (Exception e) {
			}

		}
		if (states.size() <= 1) {
			TxState state = getState();
			if (state.getMaxConnection() > state.getNowConnection()) {
				return TxServer.format(state);
			} else {
				return null;
			}
		} else {
			//找默认数据
			TxState state = getDefault(states, 0);
			if (state == null) {
				//没有满足的默认数据
				return null;
			}
			return TxServer.format(state);
		}
	}

	private TxState getDefault(List<TxState> states, int index) {
		TxState state = states.get(index);
		if (state.getMaxConnection() == state.getNowConnection()) {
			index++;
			if (states.size() - 1 >= index) {
				return getDefault(states, index);
			} else {
				return null;
			}
		} else {
			return state;
		}
	}

}
