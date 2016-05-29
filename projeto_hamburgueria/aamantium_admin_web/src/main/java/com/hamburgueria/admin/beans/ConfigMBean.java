package com.hamburgueria.admin.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.mongo.entities.Configuracoes;
import com.hamburgueria.morphia.dao.ConfiguracoesDao;

@ManagedBean
@ViewScoped
public class ConfigMBean extends BaseMBean<Configuracoes>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -669373584118048179L;
	@ManagedProperty(value="#{appMBean}")
	private AppMBean appBean;
	
	public ConfigMBean(){
		super(new ConfiguracoesDao(), new Configuracoes());
	}
	
	@PostConstruct
	public void inicializar() {
		super.inicializar();
	}
	
	public void addConfig(){
		inserir();
		appBean.setConfigs(dao.listAll());
	}

	public Configuracoes getConfigValue(String configKey){
		List<Configuracoes> result = dao.getModelByfield("configName", configKey);
		if(result != null && result.size() > 0){
			return result.get(0);
		}
		Configuracoes notFoundConfig = new Configuracoes();
		notFoundConfig.setConfigValue("0");
		return notFoundConfig;
	}
	
	public String getAutoUpload(){
		return getConfigValue(CommonConstants.AUTO_UPLOAD_CONF).getConfigValue();
	}
	public String getSkinSimple(){
		return getConfigValue(CommonConstants.SKIN_SIMPLE_CONF).getConfigValue();
	}
	public String getUploadMode(){
		return getConfigValue(CommonConstants.UPLOAD_MODE_CONF).getConfigValue();
	}
	public String getChunkSize(){
		return getConfigValue(CommonConstants.CHUNK_SIZE_CONF).getConfigValue();
	}
	public String getTabOrientation(){
		return getConfigValue(CommonConstants.TAB_ORIENT_CONF).getConfigValue();
	}
	public String getImagePath(){
		return getConfigValue(CommonConstants.IMG_PATH_CONF).getConfigValue();
	}
	public String getLife(){
		return getConfigValue(CommonConstants.GROWL_LIFE_CONF).getConfigValue();
	}
	
	public void onRowEdit(RowEditEvent event){
		setModel((Configuracoes)event.getObject());
		inserir();
	}
	
	public void setAppBean(AppMBean appBean) {
		this.appBean = appBean;
	}
	public AppMBean getAppBean() {
		return appBean;
	}
	
}
