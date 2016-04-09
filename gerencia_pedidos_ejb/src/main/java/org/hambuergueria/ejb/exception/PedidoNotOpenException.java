package org.hambuergueria.ejb.exception;

import com.hamburgueria.constants.CommonConstants;

public class PedidoNotOpenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7343011647871226880L;
	
	@Override
	public String getMessage() {
		return CommonConstants.ORDER_NOT_CREATED_MESSAGE_EXCEPTION;
	}
}
