/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hambuergueria.ejb.gerenciamento;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.ejb.TimerService;

import org.hambuergueria.ejb.exception.EmptyOrderException;

import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.DaoFactory;
import com.hamburgueria.morphia.dao.PedidoDao;
import com.mongodb.MongoException;

/**
 *
 * @author Danilo
 */
@Stateful
@LocalBean
public class PedidoSBean {

	private PedidoDao dao;
	private Pedido pedido;
	private List<Produto> items;
	@Resource
	private TimerService service;
	
	@PostConstruct
	public void inicializar(){
		items = new ArrayList<Produto>();
	}
	
	@PostActivate
	public void abreRecursos(){
		dao = (PedidoDao) DaoFactory.getDao(CommonConstants.TIPO_PEDIDO);
	}
	
    @PrePassivate
    public void fecharRecursos(){
    	dao = null;
    }
    
    @Remove
    public void removeBean(){
    	submeterPedido();
    	items = null;
    	pedido = null;
    	dao=null;
    }
    
	public void criarPedido(Cliente cliente) {
    	pedido = new Pedido();
    	pedido.setCliente(cliente);
    }
    
	public Boolean adicionaProdutos(Produto item) {
		if(pedido.getItems()==null){
			pedido.setItems(new ArrayList<Produto>());
		}
		return pedido.getItems().add(item);
	}
	public Boolean removerProdutos(Produto item) throws EmptyOrderException {
		if(pedido.getItems()==null){
			throw new EmptyOrderException();
		}
		return pedido.getItems().add(item);
	}
	
	public Long submeterPedido() throws MongoException{
		pedido.set_id(dao.saveOrUpdate(pedido));
		return pedido.get_id();
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public List<Produto> getItems() {
		return items;
	}

	public void setItems(List<Produto> items) {
		this.items = items;
	}

}
