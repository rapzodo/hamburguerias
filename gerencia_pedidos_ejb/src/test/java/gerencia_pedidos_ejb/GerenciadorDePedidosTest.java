package gerencia_pedidos_ejb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import org.hambuergueria.ejb.exception.EmptyOrderException;
import org.hambuergueria.ejb.gerenciamento.GerenciadorDePedidos;
import org.hambuergueria.ejb.gerenciamento.PedidoSBean;
import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.DaoFactory;
import com.hamburgueria.morphia.dao.PedidoDao;

public class GerenciadorDePedidosTest {
	List<Pedido> pedidos;
	Pedido p1;
	Pedido p2;
	Produto prod1;
	GerenciadorDePedidos gerenciador;
	PedidoSBean pedidoBean;
	
	@Before
	public void init(){
		PedidoDao dao = (PedidoDao) DaoFactory.getDao(CommonConstants.TIPO_PEDIDO);
		gerenciador = new GerenciadorDePedidos();
		gerenciador.inicializa();
		pedidoBean = new PedidoSBean();
		pedidos = dao.listAll();
		p1 = dao.getById(1L);
		p2 = dao.getById(2L);
		prod1 = new Produto();
		prod1.set_id(1L);
	}

	@Test
	public void testCalculaPedidoExpirado() {
		int minEmPreparo = GerenciadorDePedidos.calculaTempoEmPreparo(p1);
		assertTrue(GerenciadorDePedidos.expirado(minEmPreparo));
	}
	@Test
	public void testCalculaPedidoNaoExpirado() {
		p1.setDateCadastro(LocalDateTime.now().toDate());
		int minEmPreparo = GerenciadorDePedidos.calculaTempoEmPreparo(p1);
		assertFalse(GerenciadorDePedidos.expirado(minEmPreparo));
	}
	@Test
	public void testTodosAtrasados(){
		List<Pedido> atrados = new ArrayList<Pedido>();
		for (Pedido pedido : pedidos) {
			if(GerenciadorDePedidos.expirado(GerenciadorDePedidos.calculaTempoEmPreparo(pedido))){
				atrados.add(pedido);
			}
		}
		assertTrue(pedidos.size() == atrados.size());
	}
	@Test
	public void testUmAtrasado(){
		List<Pedido> atrados = new ArrayList<Pedido>();
		for (Pedido pedido : pedidos) {
			if(GerenciadorDePedidos.expirado(GerenciadorDePedidos.calculaTempoEmPreparo(pedido))){
				atrados.add(pedido);
			}
		}
		assertTrue(atrados.size() == 2);
	}

	@Test
	public void testaPrioridadeCarregandoDobanco(){
		GerenciadorDePedidos.loadFromDBCollection();
		assertEquals(p2.get_id(), GerenciadorDePedidos.consultaMaisPrioritario().get_id());
	}
	
	@Test
	public void testaPrioritario(){
		p2.setLastUpdate(new Date());
		gerenciador.adicionarPedido(p2);
		gerenciador.adicionarPedido(p1);
		Assert.assertNotEquals(p2.get_id(), GerenciadorDePedidos.consultaMaisPrioritario().get_id());
	}

	@Test
	public void addManyItems(){
		pedidoBean.setPedido(p1);
		pedidoBean.adicionaProdutos(prod1);
		pedidoBean.adicionaProdutos(prod1);
		pedidoBean.adicionaProdutos(prod1);
		assertTrue(pedidoBean.getPedido().getItems().size() == 3);
	}
	@Test
	public void removeManyItems(){
		pedidoBean.setPedido(p1);
		pedidoBean.adicionaProdutos(prod1);
		pedidoBean.adicionaProdutos(prod1);
		pedidoBean.adicionaProdutos(prod1);
		try {
			pedidoBean.removerProdutos(prod1);
			pedidoBean.removerProdutos(prod1);
			assertEquals(1,pedidoBean.getPedido().getItems().size());
		} catch (EmptyOrderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
