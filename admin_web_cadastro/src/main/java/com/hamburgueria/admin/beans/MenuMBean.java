package com.hamburgueria.admin.beans;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.tabview.Tab;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.UploadedFile;

import com.hamburgueria.admin.common.FileUtils;
import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ProdutoDao;

@ManagedBean
@SessionScoped
public class MenuMBean extends BaseMBean<Produto>{

	private List<Produto> menu;
	private UploadedFile foto;
	private Map<String, Object> parametros;
	
	public MenuMBean(){
		super(new ProdutoDao(), new Produto());
		parametros = new HashMap<String, Object>();
	}
	
	@PostConstruct
	public void inicializar(){
		getModel().setCategoria("Bebidas");
		listarProdutoPorCategoria();
	}
	
	public void cadastra(){
		enviaFoto();
		inserir();
	}
	
	public String editar(){
		return "/produtos/p_cadastrar";
	}
	
	public void enviaFoto(){
		if(foto != null && !foto.getFileName().isEmpty()){
			String fileName = foto.getFileName();
			try {
				FileUtils.createFile(fileName, foto.getContents());
				getModel().setImagem(fileName);
				MessagesUtil.createMsg(FacesMessage.SEVERITY_INFO, null, ServiceConstants.SUCCESS,"Fotos enviadas com sucesso");
			} catch (IOException e) {
				MessagesUtil.createMsg(FacesMessage.SEVERITY_ERROR, null, "IO Error", e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void listarProdutoPorCategoria(){
		menu = getDao().getModelByfield("categoria", getModel().getCategoria());
	}
	
	public void listarProdutosPorFiltro(){
		menu = getDao().getByComplexQueryAnd(parametros);
	}
	
	public void mudaTab(TabChangeEvent event){
		Tab tab = event.getTab();
		getModel().setCategoria(tab.getTitle());
		listarProdutoPorCategoria();
	}
	
	public List<Produto> getMenu() {
		listarProdutoPorCategoria();
		return menu;
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

	public void setMenu(List<Produto> menu) {
		this.menu = menu;
	}
}
