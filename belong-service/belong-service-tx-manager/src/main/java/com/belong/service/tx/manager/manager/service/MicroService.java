package com.belong.service.tx.manager.manager.service;


import com.belong.service.tx.manager.model.TxServer;
import com.belong.service.tx.manager.model.TxState;

/**
* @Description:
* @Author:         fengyu
* @CreateDate:     2019/12/13 18:42
*/
public interface MicroService {

	String TMKEY = "tx-manager";

	TxServer getServer();

	TxState getState();
}
