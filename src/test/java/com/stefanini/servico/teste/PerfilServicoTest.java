package com.stefanini.servico.teste;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.PerfilDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Perfil;
import com.stefanini.servico.PerfilServico;
import com.stefanini.servico.PessoaPerfilServico;
import com.stefanini.servico.PessoaServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class PerfilServicoTest {

	@Injectable
    EntityManager em;
	
	@Tested
	PerfilServico perfilServico;
	
	@Injectable
	PessoaPerfilServico pessoaPerfilServico;
	
	@Injectable
    @Mocked
	PerfilDao perfilDao;
	
	@Tested
	Perfil perfil;
	
	private Long id;
	
	@Before
	public void setUp() {
		id = 1L;
		
		perfil = new Perfil();
		perfil.setId(id);
	}

	@Test
	public void TestPerfilServicoSalvar() {
		new Expectations() {
			{
				perfilDao.salvar((@Valid Perfil) any);
				result = perfil;
			}
		};
		
		Perfil retorno = perfilServico.salvar(perfil);
		assertEquals(retorno.getId(), perfil.getId());
	}
	
	@Test
	public void TestPerfilServicoValidarPerfil() {
		new Expectations() {
			{
				perfilServico.validarPerfil(perfil);
				result = false;
			}
		};
		
		Boolean retorno = perfilServico.validarPerfil(perfil);
		assertFalse(retorno);
	}
	
	@Test
	public void TestPerfilServicoAtualizar() {
		new Expectations() {
			{
				perfilDao.atualizar(perfil);
				result = perfil;
			}
		};
		
		Perfil retorno = perfilServico.atualizar(perfil);
		assertEquals(retorno, perfil);
	}
	
	@Test
	public void TestPerfilServicoRemover() throws NegocioException {
		new Expectations() {
			{
				perfilDao.remover(id);
			}
		};
		
		perfilServico.remover(id);
		
		new Verifications() {
			{
				perfilDao.remover(id);
				times = 1;
			}
		};
	}
	
	@Test
	public void TestPerfilServicoGetList() {
		new Expectations() {
			{
				perfilDao.getList();
				result = Optional.of(perfil);
			}
		};
		
		Optional<List<Perfil>> retorno = perfilServico.getList();
		assertEquals(retorno.get(), perfil);
	}
	
	@Test
	public void TestPerfilServicoEncontrar() {
		new Expectations() {
			{
				perfilDao.encontrar(anyLong);
				result = perfil;
			}
		};
		
		Optional<Perfil> retorno = perfilServico.encontrar(id);
		assertEquals(retorno.get(), perfil);
	}
}
