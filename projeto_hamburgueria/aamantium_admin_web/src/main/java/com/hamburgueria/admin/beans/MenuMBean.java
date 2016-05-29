package com.hamburgueria.admin.beans;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import com.hamburgueria.admin.common.FileUtils;
import com.hamburgueria.admin.common.HTTPUtils;
import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.CategoriaDao;
import com.hamburgueria.morphia.dao.ProdutoDao;

@ManagedBean
@ViewScoped
public class MenuMBean extends BaseMBean<Produto>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8015122514879281193L;
	private static final String DEFAULT_TAB_CATEGORIA = "BEBIDAS";
	private static final String DESC_FIELD = "descricao";
	private static final String CATEGORIA_FIELD = "categoria";
	private String categoria;
	private UploadedFile foto;
	private Map<String, Object> parametros;
	private CategoriaDao categoriaDao;
	
	public MenuMBean(){
		super(new ProdutoDao(), new Produto());
		parametros = new HashMap<String, Object>();
	}
	
	@PostConstruct
	public void inicializar(){
		super.inicializar();
		categoriaDao = new CategoriaDao();
		categoria = DEFAULT_TAB_CATEGORIA;
		listarProdutoPorCategoria();
	}
	public void cadastra(){
		List<Categoria> cat = categoriaDao.getModelByfield(DESC_FIELD, categoria);
		getModel().setCategoria(cat.get(0));
		inserir();
		getResults();
	}
	
	@Override
	protected void getResults() {
		listarProdutoPorCategoria();
	}
	
//	public String editar(){
//		return "/produtos/p_cadastrar";
//	}
	
	public void enviaFoto(FileUploadEvent event){
		foto = event.getFile();
		if(foto != null && !foto.getFileName().isEmpty()){
			String fileName = foto.getFileName();
			try {
				FileUtils.createFile(CommonConstants.IMG_FILES_PATH, fileName, foto.getContents());
				Produto model = getModel();
				model.setImagem(fileName);
				MessagesUtil.createMsg(FacesMessage.SEVERITY_INFO, "growlFoto", ServiceConstants.SUCCESS,"Fotos enviadas com sucesso");
			} catch (IOException e) {
				MessagesUtil.createMsg(FacesMessage.SEVERITY_ERROR, "growlFoto", "IO Error", e.getMessage());
				e.printStackTrace();
			}
		}else
			MessagesUtil.createMsg(FacesMessage.SEVERITY_ERROR, "growlFoto", "Upload ERRO", "foto não enviada");
	}
	
	public void listarProdutoPorCategoria(){
		List<Categoria> cat = categoriaDao.getModelByfield(DESC_FIELD, categoria);
		setModelList(getDao().getModelByfield(CATEGORIA_FIELD, cat.get(0)));
	}
	
	public void listarProdutosPorFiltro(){
		setModelList(getDao().getByComplexQueryAnd(parametros));
	}
	
	public void mudaTab(TabChangeEvent event){
		Tab tab = event.getTab();
		categoria = tab.getTitle();
		List<Categoria> cat = categoriaDao.getModelByfield(DESC_FIELD, categoria);
		getModel().setCategoria(cat.get(0));
		listarProdutoPorCategoria();
	}
	
	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public Map<String, Object> getParametros() {
		return parametros;
	}

	public void setParametros(Map<String, Object> parametros) {
		this.parametros = parametros;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
