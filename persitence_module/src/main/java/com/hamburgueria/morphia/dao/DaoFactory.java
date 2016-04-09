package com.hamburgueria.morphia.dao;

import com.hamburgueria.constants.CommonConstants;

public class DaoFactory {

	public static BaseMongoDao<?> getDao(String userType) {
		if (userType == null) {
			return null;
		} else if (userType.equals(CommonConstants.TIPO_CLIENTE)) {
			return new ClienteDao();
		} else if (userType.equals(CommonConstants.TIPO_RESTAURANTE)) {
			return new RestauranteDao();
		} else if (userType.equals(CommonConstants.TIPO_PEDIDO)) {
			return new PedidoDao();
		} else if (userType.equals(CommonConstants.TIPO_PRODUTO)) {
			return new ProdutoDao();
		} else if (userType.equals(CommonConstants.TIPO_CATEGORIA)) {
			return new CategoriaDao();
		}else{
			return null;
		}
	}
}
