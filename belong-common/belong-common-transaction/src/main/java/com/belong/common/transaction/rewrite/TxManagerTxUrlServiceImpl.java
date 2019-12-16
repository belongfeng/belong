package com.belong.common.transaction.rewrite;

import com.belong.common.core.constant.ServiceNameConstants;
import com.codingapi.tx.config.service.TxManagerTxUrlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;

/**
* @Description:    使用服务发现重写 Txmanager 获取规则
* @Author:         fengyu
* @CreateDate:     2019/12/16 10:05
*/
@Slf4j
@Service
@AllArgsConstructor
public class TxManagerTxUrlServiceImpl implements TxManagerTxUrlService {
	private final LoadBalancerClient loadBalancerClient;

	@Override
	public String getTxUrl() {
		ServiceInstance serviceInstance = loadBalancerClient.choose(ServiceNameConstants.BELONG_SERVICE_TX_MANAGER);
		String host = serviceInstance.getHost();
		Integer port = serviceInstance.getPort();
		String url = String.format("http://%s:%s/tx/manager/", host, port);
		log.info("tm.manager.url -> {}", url);
		return url;
	}
}
