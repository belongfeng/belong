package com.belong.service.tx.manager.api.service;

import com.belong.service.tx.manager.compensate.model.TxModel;
import com.belong.service.tx.manager.model.ModelName;
import com.belong.service.tx.manager.model.TxState;
import com.lorne.core.framework.exception.ServiceException;

import java.util.List;
import java.util.Map;

/**
* @Description:    ApiAdminService
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:37
*/
public interface ApiAdminService {

	TxState getState();

	/**
	 * k/v 获取 值封装成map
	 *
	 * @return
	 */
	List<Map<String, Object>> getMapState();

	String loadNotifyJson();

	List<ModelName> modelList();


	List<String> modelTimes(String model);

	List<TxModel> modelInfos(String path);

	boolean compensate(String path) throws ServiceException;

	boolean hasCompensate();

	boolean delCompensate(String path);
}
