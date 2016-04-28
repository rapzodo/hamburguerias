package com.hamburgueria.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hambuergueria.ejb.gerenciamento.PedidoSBean;
import org.hambuergueria.ejb.gerenciamento.GerenciadorDePedidos;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;

@Path("pedidos")
public class PedidosResource {

	@EJB
	private PedidoSBean cartBean;
	@EJB
	private GerenciadorDePedidos gerenciadorBean;
	
	@Path("abrirPedido/{mesa}")
	@POST
	@Consumes(value=MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido abrirPedido(Cliente cliente,@PathParam(value="mesa") Integer mesa){
		return cartBean.criarPedido(cliente, mesa);
	}
	
	@Path("addItem")
	@POST
	@Consumes(value=MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido addItens(Produto item){
		cartBean.getPedido().getItems().add(item);
		return cartBean.getPedido();
	}
	@Path("submeterPedido")
	@POST
	@Consumes(value=MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido submetePedido(){
		cartBean.submeterPedido();
		return cartBean.getPedido();
	}
	
	@GET
	@Produces
	public List<Pedido> buscaPedidos(){
		return null;
	}
}
