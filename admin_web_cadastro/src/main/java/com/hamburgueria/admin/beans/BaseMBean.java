package com.hamburgueria.admin.beans;

import javax.faces.bean.ManagedBean;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.morphia.dao.BaseMongoDao;
import com.mongodb.DuplicateKeyException;

@ManagedBean
@SuppressWarnings(value={"unchecked"})
public class BaseMBean<MODEL> {

	protected BaseMongoDao<MODEL> dao;
	private MODEL model;

	public BaseMBean() {
	}

	public BaseMBean(BaseMongoDao<MODEL> dao, MODEL model) {
		this.model = model;
		this.dao = dao;
	}

	protected void inserir() {
		long id = -1;
		try {
			id = dao.saveOrUpdate(model);
			if (id != -1) {
				model = (MODEL) model.getClass().newInstance();
				MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,
						DBConstants.SUCCESS_UPDATED + " id : " + id);
			}
		} catch (DuplicateKeyException dupExc) {
			String extractErrorMessage = DuplicateKeyException
					.extractErrorMessage(dupExc.getResponse());
			MessagesUtil.createErrMsg(null, "CHAVE DUPLICADA",
					"Já existe um registro com a mesma chave");
		} catch (Exception e) {
			e.printStackTrace();
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE,
					DBConstants.FAIL_MESSAGE + e.getMessage());
		}
	}

	public MODEL getModel() {
		return model;
	}

	public void setModel(MODEL model) {
		this.model = model;
	}

	public BaseMongoDao<MODEL> getDao() {
		return dao;
	}

	public void setDao(BaseMongoDao<MODEL> dao) {
		this.dao = dao;
	}
}
