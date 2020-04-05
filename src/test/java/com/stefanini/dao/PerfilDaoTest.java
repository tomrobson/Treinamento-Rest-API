package com.stefanini.dao;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.model.Perfil;

import mockit.Injectable;
import mockit.Tested;

public class PerfilDaoTest {

	@Injectable
    EntityManager em;

    @Tested
    PerfilDao perfilDao;
    
    @Test
    public void buscarPessoaPorNome() {
    	String nome = "nome";
        Optional<Perfil> perfil = perfilDao.buscarPessoaPorNome(nome);
        assertTrue(perfil.isEmpty());
    }
}
