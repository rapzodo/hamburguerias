package com.hamburgueria.admin.beans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hambuergueria.ejb.exception.EmptyOrderException;
import org.hambuergueria.ejb.exception.PedidoNotOpenException;
import org.hambuergueria.ejb.fluxo.AtendimentoSBean;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;


@ManagedBean
@SessionScoped
public class TiraPedidoMBean {

	@EJB
	private AtendimentoSBean atendimentoBean;
	private Cliente cliente;
	private Integer mesa;
	private Pedido pedido;
	private Produto item;
	private Long pedidoId;
	private static final String ORDER_CREATED = "Pedido criado ";
	
	public void abrePedido(){
		atendimentoBean.abrirPedido(cliente, mesa);
	}
	
	public void addItem(){
		atendimentoBean.adicionaItem(item, pedidoId);
	}
	
	public void removeItem(){
		try {
			atendimentoBean.removeItem(item, pedidoId);
		} catch (EmptyOrderException e) {
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void tirarPedido(){
		try {
			pedidoId = atendimentoBean.enviaPedido();
			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS, ORDER_CREATED + pedidoId);
		} catch (PedidoNotOpenException e) {
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Produto getItem() {
		return item;
	}

	public void setItem(Produto item) {
		this.item = item;
	}

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}
	
}
