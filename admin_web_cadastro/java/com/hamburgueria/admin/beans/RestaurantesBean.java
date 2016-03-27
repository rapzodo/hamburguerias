package com.hamburgueria.admin.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.mongo.entities.Restaurante;
import com.hamburgueria.morphia.dao.RestauranteDao;

@ManagedBean
@SessionScoped
public class RestaurantesBean {

	private Restaurante restaurante;
	private List<Restaurante> restaurantes;
	private RestauranteDao dao = new RestauranteDao();
	
	public void cadastra(){
		try{
			dao.saveOrUpdate(restaurante);
			restaurante = new Restaurante();
			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,DBConstants.SUCCESS_UPDATED);
		}catch(Exception e){
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE,DBConstants.FAIL_MESSAGE);
		}
	}
	
	public Restaurante getRestaurante() {
		if(restaurante == null){
			restaurante = new Restaurante();
		}
		return restaurante;
	}

	public List<Restaurante> getRestaurantes() {
		if(restaurantes == null){
			restaurantes = new ArrayList<Restaurante>();
		}
		return restaurantes;
	}

	public void setRestaurantes(List<Restaurante> restaurantes) {
		this.restaurantes = restaurantes;
	}

	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}
	
}
