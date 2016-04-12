package com.hamburgueria.admin.beans;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.model.UploadedFile;

import com.hamburgueria.admin.common.FileUtils;
import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ProdutoDao;
import com.mongodb.DuplicateKeyException;

@ManagedBean
@SessionScoped
public class MenuMBean {

	private List<Produto> menu;
	private ProdutoDao prodDao = new ProdutoDao();
	private Produto produto = new Produto();
	private Categoria categoria ;
	private UploadedFile foto;
	private String tipo;
	
	private String defaultImg="http://parkresto.com/wp-content/themes/parkrestaurant/images/11onlinereservationpark.jpg";
	int counter=0;
	
	public MenuMBean(){
		menu = new ArrayList<Produto>();
//		listarMenu();
	}
	
	public void cadastraProduto(){
		long id = -1;
		try{
			enviaFoto();
			id = prodDao.saveOrUpdate(produto);
//			System.out.println("Chamou " + counter + " vez");
			if(id != -1){
				produto = new Produto();
				MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,DBConstants.SUCCESS_UPDATED + " id : " + id);
			}
//			counter ++;
//			MessagesUtil.createSuccessMsg(null, ServiceConstants.SUCCESS,"Chamou " + counter + " vez");
		}catch(DuplicateKeyException dupExc){
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE," Já existe um produto com o nome " + produto.getNome());
		}catch(Exception e){
			MessagesUtil.createErrMsg(null, ServiceConstants.FAIL_MESSAGE,DBConstants.FAIL_MESSAGE + e.getMessage());
		}
	}
	
	public void enviaFoto(){
		if(foto != null){
			defaultImg = foto.getFileName();
			String filePath = CommonConstants.IMG_FILES_PATH + File.separatorChar + foto.getFileName();
			try {
				FileUtils.createFile(filePath, foto.getContents());
			} catch (IOException e) {
				MessagesUtil.createMsg(FacesMessage.SEVERITY_ERROR, null, "IO Error", e.getMessage());
				e.printStackTrace();
			}
			produto.setImagem(filePath);
			MessagesUtil.createMsg(FacesMessage.SEVERITY_INFO, null, ServiceConstants.SUCCESS,"Fotos enviadas com sucesso");
		}else{
			MessagesUtil.createMsg(FacesMessage.SEVERITY_WARN, null, "UPLOAD", "foto nula");
		}
	}
	
	public String listarLanches(){
		if(getTipo()!= null){
			if(getTipo().equalsIgnoreCase("B")){
				setMenu(prodDao.getModelByfield("nome", "Da casa"));
			}else{
				setMenu(prodDao.listAll());
			}
		}
		return "/produtos/listar";
	}
	
	public List<Produto> getMenu() {
//		HttpServletRequest request =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//		setTipo(request.getParameter("tipo"));
//		System.out.println(tipo);
		
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

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
