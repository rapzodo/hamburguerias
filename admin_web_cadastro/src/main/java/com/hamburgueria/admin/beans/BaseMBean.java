package com.hamburgueria.admin.beans;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.morphia.dao.BaseMongoDao;
import com.mongodb.DuplicateKeyException;

public class BaseMBean<MODEL> {

	protected BaseMongoDao<MODEL> dao ;
	
	public BaseMBean(){
		
	}
	
	public BaseMBean (BaseMongoDao<MODEL> dao){
		this.dao = dao;
	}
	
	@SuppressWarnings("unchecked")
	protected MODEL inserir(MODEL entity) {
		long id = -1;
		try{
			id = dao.saveOrUpdate(entity);
			if(id != -1){
				entity = (MODEL) entity.getClass().newInstance();
				MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,DBConstants.SUCCESS_UPDATED + " id : " + id);
			}
		}catch(DuplicateKeyException dupExc){
			String extractErrorMessage = DuplicateKeyException.extractErrorMessage(dupExc.getResponse());
			MessagesUtil.createErrMsg(null, "CHAVE DUPLICADA","Já existe um registro com a mesma chave");
		}catch(Exception e){
			e.printStackTrace();
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE,DBConstants.FAIL_MESSAGE + e.getMessage());
		}
		return entity;
	}
}
