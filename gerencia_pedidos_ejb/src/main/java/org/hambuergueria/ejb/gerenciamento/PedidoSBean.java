/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hambuergueria.ejb.gerenciamento;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
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
@DependsOn(value="GerenciadorDePedidos")
public class PedidoSBean {

	private PedidoDao dao;
	private Pedido pedido;
	@Resource
	private TimerService service;
	
	@PostConstruct
	public void inicializar(){
		System.out.println("INICIALIZANDO " + getClass().getSimpleName());
		dao = (PedidoDao) DaoFactory.getDao(CommonConstants.TIPO_PEDIDO);
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
    	System.out.println("DESTROING BEAN " + getClass().getSimpleName());
    	submeterPedido();
    	pedido = null;
    	dao=null;
    }
    
	public Pedido criarPedido(Cliente cliente, int mesa) {
    	pedido = new Pedido();
    	pedido.setNumMesa(mesa);
    	pedido.set_id(dao.getCounterSeq());
    	if(cliente.getEmailId() != null){
    		pedido.setCliente(cliente);
    	}
    	return pedido;
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
		return pedido.getItems().remove(item);
	}
	
	public Long submeterPedido() throws MongoException{
		pedido.set_id(dao.saveOrUpdate(pedido));
		return pedido.get_id();
	}
	
	public void calculaValorTotal(Double servico){
		Double total = 0.0;
		for(Produto item : pedido.getItems()){
			total += item.getPreco();
		}
		total += servico;
		pedido.setValorTotal(total);
		pedido.setServico(servico);
	}
	
	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

}
