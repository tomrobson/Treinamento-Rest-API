package com.stefanini.dao;

import static org.junit.Assert.*;

import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.model.PessoaPerfil;

import mockit.Injectable;
import mockit.Tested;

public class PessoaPerfilDaoTest {

	@Injectable
    EntityManager em;
	
	@Tested
	PessoaPerfilDao pessoaPerfilDao;
	
	@Test
	public void test() {
		Long id = 1L;
		Stream<PessoaPerfil> pessoaPerfil = pessoaPerfilDao.buscarPessoaPerfil(id, id);
        assertTrue(pessoaPerfil != null);
	}

}
