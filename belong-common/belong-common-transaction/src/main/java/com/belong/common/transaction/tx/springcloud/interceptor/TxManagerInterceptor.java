package com.belong.common.transaction.tx.springcloud.interceptor;

import com.codingapi.tx.aop.service.AspectBeforeService;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/16 10:06
*/
@Component
public class TxManagerInterceptor {

	@Autowired
	private AspectBeforeService aspectBeforeService;

	@SneakyThrows
	public Object around(ProceedingJoinPoint point) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		String groupId = request.getHeader("tx-group");
		return aspectBeforeService.around(groupId, point);
	}
}
