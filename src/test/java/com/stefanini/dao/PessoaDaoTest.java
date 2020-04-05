package com.stefanini.dao;

import static org.junit.Assert.*;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.Test;

import com.stefanini.model.Pessoa;

import mockit.Injectable;
import mockit.Tested;

public class PessoaDaoTest {


    @Injectable
    EntityManager em;

    @Tested
    PessoaDao pessoaDao;

    @Test
    public void TestBuscarPessoaPorEmail() {
        String email = "email";
        Optional<Pessoa> pessoa = pessoaDao.buscarPessoaPorEmail(email);
        assertTrue(pessoa.isEmpty());
    }

}
