package org.hambuergueria.ejb.gerenciamento;

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

import org.hambuergueria.ejb.comparators.PedidoComparator;
import org.joda.time.LocalTime;
import org.joda.time.Period;

import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.morphia.dao.PedidoDao;
import com.services.messages.jms.MessageServices;
import com.services.messages.jms.QueueMessageType;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class GerenciadorDePedidos {

	private static PriorityQueue<Pedido> filaDePedidos;
	private static long TEMPO_MAX_PREPARO_PEDIDO_SEC = 10000;
	public static final int TEMPO_LIMITE_MIN = 40;
	
	@Resource
	private TimerService service;
	
	@PostConstruct
	public void inicializa(){
		System.out.println("STATING " + getClass().getSimpleName());
		filaDePedidos = new PriorityQueue<Pedido>(new PedidoComparator());
	}
	
	public static void loadFromDBCollection(){
		filaDePedidos.addAll(new PedidoDao().listAll());
	}
	
	public static Pedido entregaPedido(){
		return filaDePedidos.poll();
	}
	
	public static Pedido consultaMaisPrioritario(){
		return filaDePedidos.peek();
	}
	
//	@Schedule(second="10",dayOfWeek="*",hour="12-00")
	@Timeout
	public void enviaMensagemPedidosAposTimeout(Timer timer){
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
	
	public static int calculaTempoEmPreparo(Pedido pedido){
		Period periodo = Period.fieldDifference(LocalTime.fromDateFields(pedido.getDateCadastro()), LocalTime.now());
		int segundosEmPreparo = periodo.toStandardSeconds().getSeconds();
		return segundosEmPreparo / 60;
	}
	
	public static boolean expirado(int minutosEmPreparo){
		return minutosEmPreparo > TEMPO_LIMITE_MIN ;
	}
	
	private void startTimer(){
		service.createSingleActionTimer(TEMPO_MAX_PREPARO_PEDIDO_SEC, new TimerConfig(null,false));
	}
	
	public Boolean adicionarPedido(Pedido pedido){
		return filaDePedidos.offer(pedido);
	}
	
}
