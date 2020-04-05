package com.stefanini.dao;

import javax.persistence.EntityManager;

import org.junit.Test;

import mockit.Injectable;
import mockit.Tested;

public class EnderecoDaoTest {

	@Injectable
    EntityManager em;
	
	@Tested
	EnderecoDao enderecoDao;
	
	@Test
	public void test() {

	}

}
