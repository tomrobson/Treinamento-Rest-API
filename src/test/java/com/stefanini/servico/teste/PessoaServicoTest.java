package com.stefanini.servico.teste;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.EnderecoDao;
import com.stefanini.dao.PessoaDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.servico.EnderecoServico;
import com.stefanini.servico.PessoaPerfilServico;
import com.stefanini.servico.PessoaServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;

public class PessoaServicoTest {
	
	@Injectable
    EntityManager em;
	
	@Tested
	PessoaServico pessoaServico;
	
	@Injectable
	EnderecoServico enderecoServico;
	
	@Injectable
	PessoaPerfilServico pessoaPerfilServico;
	
	@Injectable
	@Mocked
	PessoaDao pessoaDao;
	
	@Injectable
	@Mocked
	EnderecoDao enderecoDao;
	
	@Tested
	Pessoa pessoa;
	
	@Tested
	Endereco endereco;
	
	@Tested
	Perfil perfil;
	
	private Long id;
	private Long idEndereco;
	private String email;
	private Set<Endereco> enderecos = new HashSet<>();
	
	@Before
	public void setUp() {
		id = 1L;
		idEndereco = 1L;
		email = "teste@gmail.com";
		
		pessoa = new Pessoa();
		endereco = new Endereco();
		
		pessoa.setId(id);
		pessoa.setEmail(email);
		endereco.setId(idEndereco);
		enderecos.add(endereco);
		
		pessoa.setEnderecos(enderecos);
	}
	
	@Test
	public void TestPessoaServicoSalvar() {
		new Expectations() {
			{
				pessoaDao.salvar((@Valid Pessoa) any);
				result = pessoa;
			}
		};
		
		Pessoa retorno = pessoaServico.salvar(pessoa);
		assertEquals(retorno, pessoa);
	}
	
	@Test
	public void TestPessoaServicoValidar() {
		new Expectations() {
			{
				pessoaDao.encontrar(id);
				result = pessoa;
			}
		};
		
		Boolean retorno = pessoaServico.validarPessoa(pessoa);
		assertTrue(retorno);
	}
	
	@Test
	public void TestPessoaServicoGetList() {
		new Expectations() {
			{
				pessoaDao.getList();
				result = Optional.of(pessoa);
			}
		};
		
		Optional<List<Pessoa>> retorno = pessoaServico.getList();
		assertEquals(retorno.get(), pessoa);
	}
	
	@Test
	public void TestPessoaServicoAtualizarComImagem() {
		pessoa.setImagem("data:construcao,base64");
		new Expectations() {
			{
				pessoaDao.atualizar(pessoa);
				result = pessoa;
			}
		};
		
		Pessoa retorno = pessoaServico.atualizar(pessoa);
		assertEquals(retorno.getId(), pessoa.getId());
	}
	
	@Test
	public void TestPessoaServicoRemover() throws NegocioException {
		new Expectations() {
			{
				pessoaDao.remover(id);
			}
		};
		
		pessoaServico.remover(id);
		
		new Verifications() {
			{
				pessoaDao.remover(id);
				times = 1;
			}
		};
	}
	
	@Test
	public void TestPerfilServicoEncontrar() {
		pessoa.setImagem("imagem0.000.jpg");
		new Expectations() {
			{
				pessoaDao.encontrar(anyLong);
				result = pessoa;
			}
		};
		
		Optional<Pessoa> retorno = pessoaServico.encontrar(id);
		assertEquals(retorno.get(), pessoa);
	}
	
	@Test
	public void TestPessoaServicoUrlImagem() {
		FileInputStream file = pessoaServico.urlImagem("imagem0.123.jpg");
		assertFalse(file.toString().isEmpty());
	}
	
	@Test
	public void TestPessoaServicoDeletarImagem() {
		pessoaServico.deletarImagem("imagem0.123456.jpg");
		
		new Verifications() {
			{
				pessoaServico.deletarImagem("imagem0.123456.jpg");
				times = 1;
			}
		};
	}

}
