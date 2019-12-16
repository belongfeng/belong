package com.belong.common.transaction.tx.springcloud.http;

import com.codingapi.tx.aop.bean.TxTransactionLocal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/16 10:05
*/
@Slf4j
public class TransactionHttpRequestInterceptor implements ClientHttpRequestInterceptor {

	@Override
	@SneakyThrows
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) {

		TxTransactionLocal txTransactionLocal = TxTransactionLocal.current();
		String groupId = txTransactionLocal == null ? null : txTransactionLocal.getGroupId();

		log.info("LCN-SpringCloud TxGroup info -> groupId:" + groupId);

		if (txTransactionLocal != null) {
			request.getHeaders().add("tx-group", groupId);
		}
		return execution.execute(request, body);
	}
}
