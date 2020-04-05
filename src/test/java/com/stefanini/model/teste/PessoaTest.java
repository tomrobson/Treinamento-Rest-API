package com.stefanini.model.teste;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.model.Endereco;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;

import mockit.Injectable;
import mockit.Tested;

public class PessoaTest {
	
	@Injectable
    EntityManager em;

	@Tested
	Pessoa pessoa;
	
	private Long id;
	private String nome;
	private String email;
	private LocalDate dataNascimento = null;
	private Boolean situacao;
	private String imagem;
	private Set<Endereco> enderecos = new HashSet<>();
	private Set<Perfil> perfils = new HashSet<>();
	private String stringTo;
	
	@Before
	public void setUp() {
		id = 1L;
		nome = "Joao";
		email = "joao@gmail.com";
		dataNascimento = dataNascimento.parse("1995-08-25");
		situacao = true;
		imagem = "C:/usuario/img";
		stringTo =
			"Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", dataNascimento=" + dataNascimento
			+ ", situacao=" + situacao + ", imagem=" + imagem + ", enderecos=" + enderecos + ", perfils=" + perfils
			+ "]";
		
		pessoa = new Pessoa();
		
		pessoa.setId(id);
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setSituacao(situacao);
		pessoa.setImagem(imagem);
		pessoa.setEnderecos(enderecos);
		pessoa.setPerfils(perfils);
	}
	
	@Test
	public void TestPessoa() {
		Assert.assertEquals(pessoa.getId(), id);
		Assert.assertEquals(pessoa.getNome(), nome);
		Assert.assertEquals(pessoa.getEmail(), email);
		Assert.assertEquals(pessoa.getDataNascimento(), dataNascimento);
		Assert.assertEquals(pessoa.getSituacao(), situacao);
		Assert.assertEquals(pessoa.getImagem(), imagem);
		Assert.assertEquals(pessoa.getEnderecos(), enderecos);
		Assert.assertEquals(pessoa.getPerfils(), perfils);
		Assert.assertEquals(pessoa.toString(), stringTo);
	}
	
	@Test
	public void TestPessoa1() {
		pessoa = new Pessoa(nome, email, dataNascimento, situacao);
		
		Assert.assertEquals(pessoa.getNome(), nome);
		Assert.assertEquals(pessoa.getEmail(), email);
		Assert.assertEquals(pessoa.getDataNascimento(), dataNascimento);
		Assert.assertEquals(pessoa.getSituacao(), situacao);
	}
	
	@Test
	public void TestPessoaHashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((enderecos == null) ? 0 : enderecos.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((imagem == null) ? 0 : imagem.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((perfils == null) ? 0 : perfils.hashCode());
		result = prime * result + ((situacao == null) ? 0 : situacao.hashCode());
		
		int resultMetodo = pessoa.hashCode();
		Assert.assertEquals(resultMetodo, result);
	}
}
