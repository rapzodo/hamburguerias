package com.hamburgueria.tirapedido.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import org.hambuergueria.ejb.exception.EmptyOrderException;
import org.hambuergueria.ejb.gerenciamento.PedidoSBean;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.DaoFactory;


@ManagedBean
@SessionScoped
public class TiraPedidoMBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2084413011802636425L;
	@EJB
	private PedidoSBean atendimentoBean;
	private Cliente cliente;
	private Produto item;
	private Long pedidoId;
	private Integer mesa;
	private Integer quantidade;
	private static final String ORDER_CREATED = "Pedido criado";
	private static final String ORDER_SENT = "Pedido Enviado";
	
	@PostConstruct
	public void init(){
		cliente = new Cliente();
		item = new Produto();
	}
	
	public void abrePedido(){
		pedidoId = atendimentoBean.criarPedido(cliente, mesa).get_id();
		MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS, ORDER_CREATED);
	}
	
	public void addItem(){
		try{
			for (int i = 0; i < quantidade ; i++) {
				atendimentoBean.adicionaProdutos(item);
			}
			atendimentoBean.calculaValorTotal(0.0);
			quantidade = 0;
		}catch(Exception e){
			e.printStackTrace();
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE, e.getMessage());
		}
	}
	
	public void removeItem(){
		try {
			atendimentoBean.removerProdutos(item);
			atendimentoBean.calculaValorTotal(0.0);
		} catch (EmptyOrderException e) {
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void tirarPedido(){
		try {
			pedidoId = atendimentoBean.submeterPedido();
			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS, ORDER_CREATED + pedidoId);
		} catch (Exception e) {
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE, e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void fechaPedido(){
		atendimentoBean.calculaValorTotal(0.0);
		Pedido pedido = atendimentoBean.getPedido();
		pedido.setFechado(true);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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


	public Integer getMesa() {
		return mesa;
	}

	public void setMesa(Integer mesa) {
		this.mesa = mesa;
	}

	@SuppressWarnings("unchecked")
	public List<Produto> getCardapio() {
		return (List<Produto>) DaoFactory.getDao(CommonConstants.TIPO_PRODUTO).listAll();
	}

	public Integer getTotalItems(){
		if(atendimentoBean.getPedido() != null){
			if(atendimentoBean.getPedido().getItems() != null){
				List<Produto> items = atendimentoBean.getPedido().getItems();
				return items.size();
			}
		}
		return 0;
	}

	public PedidoSBean getAtendimentoBean() {
		return atendimentoBean;
	}

	public void setAtendimentoBean(PedidoSBean atendimentoBean) {
		this.atendimentoBean = atendimentoBean;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
}
