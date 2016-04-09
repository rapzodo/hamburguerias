package org.hambuergueria.ejb.gerenciamento;

import java.util.Date;
import java.util.PriorityQueue;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.jms.JMSException;
import javax.jms.Session;

import sun.misc.Queue;

import com.hamburgueria.mongo.entities.Pedido;
import com.services.messages.jms.MessageServices;
import com.services.messages.jms.QueueMessageType;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class GerenciadorDePedidos {

	public static PriorityQueue<Pedido> filaDePedidos;
	private static long TEMPO_MAX_PREPARO_PEDIDO_SEC = 10000;
	
	@Resource
	private TimerService service;
	
	@PostConstruct
	public void inicializa(){
		filaDePedidos = new PriorityQueue<Pedido>();
	}
	
	public static void priorizarPedido(Pedido pedido){
		
	}
	
	public static Pedido entregaPedido(){
		return filaDePedidos.poll();
	}
	
	public static Pedido consultaMaisPrioritario(){
		return filaDePedidos.peek();
	}
	
//	@Schedule(second="10",dayOfWeek="*",hour="12-00")
	@Timeout
	public void pedidosAtrasados(Timer timer){
		/**TO DO
		 * criar mensagem de alerta JMS e enviar pra fila
		 */
		MessageServices service = new MessageServices(new QueueMessageType());
		try {
			service.createJmsSession(false, Session.AUTO_ACKNOWLEDGE);
			service.sendJMSMessage("TA Atrasado, Corre com essa porra ae");
		} catch (JMSException e) {
			e.printStackTrace();
		}
		System.out.println("Repriorizar e criar alertas para Pedidos atrasados");
	}
	
	public String adicionarPedido(Pedido pedido){
		filaDePedidos.offer(pedido);
		service.createSingleActionTimer(TEMPO_MAX_PREPARO_PEDIDO_SEC, new TimerConfig(null,false));
		return "Estourou o pedido as " + new Date();
	}
	
}
