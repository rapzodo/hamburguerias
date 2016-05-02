package org.hambuergueria.ejb.comparators;

import java.util.Comparator;

import com.hamburgueria.mongo.entities.Pedido;

public class PedidoComparator implements Comparator<Pedido>{

	@Override
	public int compare(Pedido p1, Pedido p2) {
		return p1.getLastUpdate().compareTo(p2.getLastUpdate());
	}

}
