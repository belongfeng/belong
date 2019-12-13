package com.belong.service.tx.manager.api.service.impl;

import com.belong.service.tx.manager.api.service.ApiModelService;
import com.belong.service.tx.manager.manager.ModelInfoManager;
import com.belong.service.tx.manager.model.ModelInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @Description:    ApiModelServiceImpl
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:37
*/
@Service
public class ApiModelServiceImpl implements ApiModelService {


	@Override
	public List<ModelInfo> onlines() {
		return ModelInfoManager.getInstance().getOnlines();
	}


}
