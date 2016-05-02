package com.hamburgueria.admin.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import com.hamburgueria.admin.common.HTTPUtils;
import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.DomainSuperClass;
import com.hamburgueria.morphia.dao.BaseMongoDao;
import com.mongodb.DuplicateKeyException;

@ManagedBean
@SuppressWarnings(value={"unchecked"})
public class BaseMBean<MODEL extends DomainSuperClass> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7549883211371837727L;
	protected BaseMongoDao<MODEL> dao;
	private MODEL model;
	private List<MODEL> modelList;

	public BaseMBean() {
	}

	public BaseMBean(BaseMongoDao<MODEL> dao, MODEL model) {
		this.model = model;
		this.dao = dao;
	}

	@PostConstruct
	public void inicializar(){
		if(getModel() != null){
			String id = (String)HTTPUtils.getParameter("id");
			if(id != null){
				setModel(getDao().getById(Long.valueOf(id)));
			}
		}
	}	
	
	protected void inserir() {
		long id = -1;
		try {
			id = dao.saveOrUpdate(model);
			if (id != -1) {
				model = (MODEL) model.getClass().newInstance();
				MessagesUtil.createSuccessMsg("growlMsg", ServiceConstants.SUCCESS,
						DBConstants.SUCCESS_UPDATED + " id : " + id);
			}
		} catch (DuplicateKeyException dupExc) {
			String extractErrorMessage = DuplicateKeyException
					.extractErrorMessage(dupExc.getResponse());
			MessagesUtil.createErrMsg("growlMsg", "CHAVE DUPLICADA",
					"Já existe um registro com a mesma chave");
		} catch (Exception e) {
			e.printStackTrace();
			MessagesUtil.createErrMsg("growlMsg", ServiceConstants.FAIL_MESSAGE,
					DBConstants.FAIL_MESSAGE + e.getMessage());
		}
	}

	public void excluir(){
		dao.deleteModel(model);
		getResults();
		MessagesUtil.createSuccessMsg("growlMsg", ServiceConstants.SUCCESS, "excluido com sucesso!");
	}
	
	protected void getResults(){
		modelList = dao.listAll();
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

	public List<MODEL> getModelList() {
		return modelList;
	}

	public void setModelList(List<MODEL> modelList) {
		this.modelList = modelList;
	}
}
