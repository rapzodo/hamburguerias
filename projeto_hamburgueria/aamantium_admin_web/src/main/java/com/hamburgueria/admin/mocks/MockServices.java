package com.hamburgueria.admin.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.mongo.entities.Produto;

@ManagedBean
@ApplicationScoped
public class MockServices {

	public List<Pedido> generatePedidos(int size) {
		List<Pedido> result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Pedido p = new Pedido();
			p.set_id(UUID.randomUUID().getMostSignificantBits());
			p.setNumMesa(i);
			p.setItems(generateProdutos(5));
			p.setValorTotal(100.00);
			result.add(p);
		}
		return result;
	}

	public List<Produto> generateProdutos(int size) {
		List<Produto> result = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			Produto p = new Produto();
			p.set_id(i);
			p.setNome("Product-" + i);
			p.setPreco((Double) Math.random());
			result.add(p);
		}
		return result;
	}

}
