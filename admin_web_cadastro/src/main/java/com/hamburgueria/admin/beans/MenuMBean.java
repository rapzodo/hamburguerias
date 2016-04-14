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
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ProdutoDao;

@ManagedBean
@SessionScoped
public class MenuMBean extends BaseMBean<Produto>{

	private List<Produto> menu;
	private ProdutoDao prodDao = new ProdutoDao();
	private Produto produto = new Produto();
	private Categoria categoria ;
	private UploadedFile foto;
	private String tipo;
	
	private String defaultImg="http://parkresto.com/wp-content/themes/parkrestaurant/images/11onlinereservationpark.jpg";
	int counter=0;
	
	public MenuMBean(){
		super(new ProdutoDao());
		menu = new ArrayList<Produto>();
	}
	
	public void cadastra(){
	}
	
	public void cadastraProduto(){
		enviaFoto();
		setProduto(inserir(produto));
	}
	
	public void enviaFoto(){
		if(foto != null && !foto.getFileName().isEmpty()){
			String fileName = foto.getFileName();
			defaultImg = fileName;
			try {
				FileUtils.createFile(fileName, foto.getContents());
				produto.setImagem(fileName);
				MessagesUtil.createMsg(FacesMessage.SEVERITY_INFO, null, ServiceConstants.SUCCESS,"Fotos enviadas com sucesso");
			} catch (IOException e) {
				MessagesUtil.createMsg(FacesMessage.SEVERITY_ERROR, null, "IO Error", e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public List<Produto> getMenu() {
		menu = prodDao.getModelByfield("categoria", categoria);
	return menu;
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
		HttpServletRequest request =  (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		setTipo(request.getParameter("tipo"));
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
