package com.stefanini.model.teste;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.EntityManager;

import org.junit.Assert;
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
	
	@Test
	public void TestPessoa() {
		Long id = 1L;
		String nome = "Joao";
		String email = "joao@gmail.com";
		LocalDate dataNascimento = null;
		dataNascimento = dataNascimento.parse("1995-08-25");
		Boolean situacao = true;
		String imagem = "C:/usuario/img";
		Set<Endereco> enderecos = null;
		Set<Perfil> perfils = null;
		String stringTo =
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
		String nome = "Joao";
		String email = "joao@gmail.com";
		LocalDate dataNascimento = null;
		dataNascimento = dataNascimento.parse("1995-08-25");
		Boolean situacao = true;
		pessoa = new Pessoa(nome, email, dataNascimento, situacao);
		
		Assert.assertEquals(pessoa.getNome(), nome);
		Assert.assertEquals(pessoa.getEmail(), email);
		Assert.assertEquals(pessoa.getDataNascimento(), dataNascimento);
		Assert.assertEquals(pessoa.getSituacao(), situacao);
	}
	
	@Test
	public void TestPessoaHashCode() {
		Long id = 1L;
		String nome = "Joao";
		String email = "joao@gmail.com";
		LocalDate dataNascimento = null;
		dataNascimento = dataNascimento.parse("1995-08-25");
		Boolean situacao = true;
		String imagem = "C:/usuario/img";
		Set<Endereco> enderecos = null;
		Set<Perfil> perfils = null;
		pessoa = new Pessoa();
		
		pessoa.setId(id);
		pessoa.setNome(nome);
		pessoa.setEmail(email);
		pessoa.setDataNascimento(dataNascimento);
		pessoa.setSituacao(situacao);
		pessoa.setImagem(imagem);
		pessoa.setEnderecos(enderecos);
		pessoa.setPerfils(perfils);
		
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
