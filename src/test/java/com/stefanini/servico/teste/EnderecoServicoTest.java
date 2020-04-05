package com.stefanini.servico.teste;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.servico.EnderecoServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class EnderecoServicoTest {
	
	@Injectable
    EntityManager em;
	
	@Tested
	EnderecoServico enderecoServico;
	
	@Injectable
    @Mocked
    EnderecoDao enderecoDao;
	
	@Tested
	Endereco endereco;
	
	private Long id;
	
	@Before
	public void setUp() {
		id = 1L;
		
		endereco = new Endereco();
		endereco.setId(id);
	}
	
	@Test
	public void TestEnderecoServicoSalvar() {
		new Expectations() {
			{
				enderecoDao.salvar((@Valid Endereco) any);
				result = endereco;
			}
		};
		
		Endereco retorno = enderecoServico.salvar(endereco);
		assertEquals(retorno.getId(), endereco.getId());
	}
	
	@Test
	public void TestEnderecoServicoAtualizar() {
		new Expectations() {
			{
				enderecoDao.atualizar(endereco);
				result = endereco;
			}
		};
		
		Endereco retorno = enderecoServico.atualizar(endereco);
		assertEquals(retorno, endereco);
	}
	
	@Test
	public void TestEnderecoServicoRemover() {
		new Expectations() {
			{
				enderecoDao.remover(id);
			}
		};
		
		enderecoServico.remover(id);
		
		new Verifications() {
			{
				enderecoDao.remover(id);
				times = 1;
			}
		};
	}
	
	@Test
	public void TestEnderecoServicoGetList() {
		new Expectations() {
			{
				enderecoDao.getList();
				result = Optional.of(endereco);
			}
		};
		
		Optional<List<Endereco>> retorno = enderecoServico.getList();
		assertEquals(retorno.get(), endereco);
	}
	
	@Test
	public void TestEnderecoServicoEncontrar() {
		new Expectations() {
			{
				enderecoDao.encontrar(anyLong);
				result = endereco;
			}
		};
		
		Optional<Endereco> retorno = enderecoServico.encontrar(id);
		assertEquals(retorno.get(), endereco);
	}
	
	@Test
	public void TestEnderecoServicoBuscarCEP() {
		String retorno = enderecoServico.buscarCEP("71880602");
		assertFalse(retorno.isEmpty());
	}
}
