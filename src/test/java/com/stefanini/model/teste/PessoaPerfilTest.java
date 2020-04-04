package com.stefanini.model.teste;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;

import mockit.Injectable;
import mockit.Tested;

public class PessoaPerfilTest {

	@Injectable
    EntityManager em;
	
	@Tested
	PessoaPerfil pessoaPerfil;
	
	@Test
	public void TestPessoaPerfil() {
		Long id = 1L;
		Long idPerfil = 1L;
		Long idPessoa = 1L;
		Perfil perfil = new Perfil();
		Pessoa pessoa = new Pessoa();
		pessoaPerfil = new PessoaPerfil();
		
		pessoaPerfil.setId(id);
		pessoaPerfil.setIdPerfil(idPerfil);
		pessoaPerfil.setIdPessoa(idPessoa);
		pessoaPerfil.setPerfil(perfil);
		pessoaPerfil.setPessoa(pessoa);
		
		Assert.assertEquals(pessoaPerfil.getId(), id);
		Assert.assertEquals(pessoaPerfil.getIdPerfil(), idPerfil);
		Assert.assertEquals(pessoaPerfil.getIdPessoa(), idPessoa);
		Assert.assertEquals(pessoaPerfil.getPerfil(), perfil);
		Assert.assertEquals(pessoaPerfil.getPessoa(), pessoa);
	}

}
