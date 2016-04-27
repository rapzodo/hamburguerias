package com.hamburgueria.admin.beans;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.morphia.dao.CategoriaDao;
import com.hamburgueria.morphia.dao.ProdutoDao;

public class MenuMBeanTest {

	private ProdutoDao pDao;
	private CategoriaDao catDao;
	private List<Categoria> catList;
	@Before
	public void init(){
		pDao = new ProdutoDao();
		catDao = new CategoriaDao();
	}
	
	@Test
	public void listAllProductsTest() {
		assertTrue(pDao.listAll().size() == 0);
	}
	
	@Test
	public void listAllCategorias(){
		assertTrue(catDao.listAll().size() == 5);
	}
	@Test
	public void listAllCategoriasOnlyFields(){
		catList = catDao.getAllOnlyFields(false,"_id");
		assertNotEquals(1,catList.get(0).get_id());
		assertEquals("BEBIDAS",catList.get(0).getDescricao());
	}

}
