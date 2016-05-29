package com.hamburgueria.admin.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.morphia.dao.ClienteDao;

@ManagedBean
@ViewScoped
public class ClienteMBean extends BaseMBean<Cliente>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -669373584118048179L;
	private Endereco endereco;
	private ClienteDao cDao;
	private List<Cliente> clientesFiltrados;
	private static final String LASTSTEP = "confirma";
	private static final String FIRST_STEP = "dados";
	
	public ClienteMBean(){
		super(new ClienteDao(), new Cliente());
		endereco = new Endereco();
	}
	
	@PostConstruct
	public void inicializar() {
		super.inicializar();
		getResults();
	}
	
	public void cadastraCliente(){
		getModel().setEndereco(endereco);
		inserir();
	}
	
	@Override
	public void excluir() {
		super.excluir();
		if(clientesFiltrados.contains(getModel())){
			clientesFiltrados.remove(getModel());
		}
	}
	
	public ClienteDao getcDao() {
		return cDao;
	}
	public void setcDao(ClienteDao cDao) {
		this.cDao = cDao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String onFlowProcess(FlowEvent event){
		return event.getNewStep();
	}
	
	public void onRowEdit(RowEditEvent event){
		Object object = event.getObject();
		if(object instanceof Cliente){
			setModel(((Cliente) object));
		}
		inserir();
	}

	public List<Cliente> getClientesFiltrados() {
		return clientesFiltrados;
	}

	public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
		this.clientesFiltrados = clientesFiltrados;
	}
}
