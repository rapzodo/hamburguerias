package com.hamburgueria.admin.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ProdutoDao;

@ManagedBean
@SessionScoped
public class MenuMBean {

	private List<Produto> menu;
	private ProdutoDao prodDao = new ProdutoDao();
	private Produto produto = new Produto();
	private Categoria categoria ;
	int counter=0;
	
	public MenuMBean(){
		menu = new ArrayList<Produto>();
	}
	
	public void cadastraProduto(){
		try{
			long id = prodDao.saveOrUpdate(produto);
			produto = new Produto();
			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,DBConstants.SUCCESS_UPDATED + " id : " + id);
//			System.out.println("Chamou " + counter + " vez");
//			counter ++;
//			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,"Chamou " + counter + " vez");
		}catch(Exception e){
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE,DBConstants.FAIL_MESSAGE + e.getMessage());
		}
	}
	
	public void listarMenu(){
		menu = prodDao.listAll();
	}

	public List<Produto> getMenu() {
		return menu;
	}

	public void setMenu(List<Produto> menu) {
		this.menu = menu;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
}
