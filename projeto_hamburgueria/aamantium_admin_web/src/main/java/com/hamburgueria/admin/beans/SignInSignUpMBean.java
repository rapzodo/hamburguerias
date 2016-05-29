package com.hamburgueria.admin.beans;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.acesso.ejb.sbeans.SignInSignUpSBean;
import org.joda.time.format.ISODateTimeFormat;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.constants.DBConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.User;
import com.hamburgueria.morphia.dao.UserDao;

@ManagedBean
@SessionScoped
public class SignInSignUpMBean {

	public static final String KEEP_COOKIE_KEY = "klgme";
	public static final String PWD_COOKIE = "pwd";
	private String email;
	private String senha;
	private String brand = "HamburgWeb";
	@EJB
	private SignInSignUpSBean signInBean;
	private User usuario = new User();
	private Boolean remember = true;

	
	@PostConstruct
	public void init(){
		getRememberCookies();
	}
	
	public String logar() {
		usuario = (User) signInBean.loginUser(email, senha);
		if (usuario != null) {
			if(remember){
				setRememBerCookies(String.valueOf(usuario.get_id()),(int)TimeUnit.DAYS.toSeconds(365));
			}else{
				removeUserCookie();
			}
			setLoggedInSession();
			return "/publico/home";
		} else {
			MessagesUtil.createErrMsg("loginMsg", DBConstants.FAIL_MESSAGE,
					DBConstants.USER_NOT_FOUND);
			return null;
		}
	}

	private void removeRememberCookies() {
		// TODO Auto-generated method stub
		
	}

	public String logOut(){
		removeLoggedInSession();
		removeUserCookie();
		return "/publico/login";
	}
	
	private void removeUserCookie() {
		setRememBerCookies("", 0);
	}

	public String signUp() {
		long id = signInBean.signUp(usuario);
		if (id > 0) {
			MessagesUtil.createSuccessMsg("growlMsg", ServiceConstants.SUCCESS,
					String.valueOf(id));
			setLoggedInSession();
			if(remember){
				getRememberCookies();
			}
			return "/publico/home";
		} else {
			MessagesUtil.createErrMsg("growlMsg",
					ServiceConstants.FAIL_MESSAGE,
					ServiceConstants.FAIL_MESSAGE);
			return null;

		}
	}

	private void setLoggedInSession() {
		Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
		sessionMap
				.put(CommonConstants.LOGGED_USER, usuario.get_id());
	}

	public void removeLoggedInSession() {
		usuario = new User();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
				.remove(CommonConstants.LOGGED_USER);
	}

	public void setRememBerCookies(String value, int maxAge){
		HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Cookie cookie = new Cookie(KEEP_COOKIE_KEY, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}
	
	public void getRememberCookies(){
		Map<String, Object> requestCookieMap = FacesContext.getCurrentInstance().getExternalContext().getRequestCookieMap();
		Cookie cookie =(Cookie) requestCookieMap.get(KEEP_COOKIE_KEY);
		if(cookie != null){
			usuario = new UserDao().getById(Long.parseLong(cookie.getValue()));
			if(usuario != null){
				setLoggedInSession();
			}
		}
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public User getUsuario() {
		return usuario;
	}

	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}

	public Boolean getRemember() {
		return remember;
	}

	public void setRemember(Boolean remember) {
		this.remember = remember;
	}

}
