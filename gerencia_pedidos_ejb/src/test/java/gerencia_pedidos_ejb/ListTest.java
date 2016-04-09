package gerencia_pedidos_ejb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;

public class ListTest {

	@Test
	public void test() {
		List<Produto> lista = new ArrayList<Produto>();
		Pedido p1 = new Pedido();
		p1.set_id(1L);
		lista.add(new Produto());
		p1.setItems(lista);
		Pedido p2 = p1;
		p2.getItems().add(new Produto());
		assertTrue(p1.getItems().size() == 2);
		assertTrue(p2.getItems().size() == 2);
	}

}
