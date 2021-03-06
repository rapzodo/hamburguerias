package org.hambuergueria.ejb.fluxo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import org.hambuergueria.ejb.exception.EmptyOrderException;
import org.hambuergueria.ejb.exception.PedidoNotOpenException;
import org.hambuergueria.ejb.gerenciamento.PedidoSBean;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ClienteDao;
import com.hamburgueria.morphia.dao.PedidoDao;

@Stateful
@Local
public class AtendimentoSBean {

	@EJB
	private PedidoSBean pedidoBean;
	private PedidoDao dao;
	private ClienteDao clienteDao;
	
	@PostConstruct
	public void init(){
		dao = new PedidoDao();
		clienteDao = new ClienteDao();
	}
	
	public Pedido abrirPedido(Cliente cliente, Integer mesa){
		return pedidoBean.criarPedido(cliente, mesa);
	}
	
	public Boolean adicionaItem(Produto produto, Long pedidoId){
		recuperaPedido(pedidoId);
		return pedidoBean.adicionaProdutos(produto);
	}
	
	public Boolean removeItem(Produto produto, Long pedidoId) throws EmptyOrderException{
		recuperaPedido(pedidoId);
		return pedidoBean.removerProdutos(produto);
	}
	
	public void fecharPedido(Long idPedido){
    	Pedido pedido = dao.getById(idPedido);
    	pedido.setFechado(true);
    	dao.updateFieldById("fechado",true,idPedido);
    }
	
	public Long enviaPedido() throws PedidoNotOpenException{
		Long id = pedidoBean.submeterPedido();
		if(id == null){
			throw new PedidoNotOpenException();
		}
		return id;
	}
	
	public Pedido recuperaPedido(Long pedidoId){
//		Se primeiro atendimento
		if(pedidoBean.getPedido() != null){
			return pedidoBean.getPedido();
		}else{
			pedidoBean.setPedido(dao.getById(pedidoId));
			return pedidoBean.getPedido();
		}
	}

	public PedidoSBean getPedidoBean() {
		return pedidoBean;
	}

	public void setPedidoBean(PedidoSBean pedidoBean) {
		this.pedidoBean = pedidoBean;
	}
}
