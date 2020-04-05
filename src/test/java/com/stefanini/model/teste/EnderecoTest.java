package com.stefanini.model.teste;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.model.Endereco;

import mockit.Injectable;
import mockit.Tested;

public class EnderecoTest {
	
	@Injectable
    EntityManager em;
	
	@Tested
	Endereco endereco;
	
	private Long id;
	private String cep;
	private String uf;
	private String localidade;
	private String bairro;
	private String complemento;
	private String logradouro;
	private Long idPessoa;
	private String stringTo;
	
	@Before
	public void setUp() {
		id = 1L;
		cep = "12345678";
		uf = "DF";
		localidade = "Brasilia";
		bairro = "Riacho Fundo II";
		complemento = "Rua";
		logradouro = "QN 25";
		idPessoa = 1L;
		stringTo =
			"Endereco{" +
            "id=" + id +
            ", cep='" + cep + '\'' +
            ", uf='" + uf + '\'' +
            ", localidade='" + localidade + '\'' +
            ", bairro='" + bairro + '\'' +
            ", complemento='" + complemento + '\'' +
            ", logradouro='" + logradouro + '\'' +
            ", idPessoa=" + idPessoa +
            '}';
		
		endereco = new Endereco();
		
		endereco.setId(id);
		endereco.setCep(cep);
		endereco.setUf(uf);
		endereco.setLocalidade(localidade);
		endereco.setBairro(bairro);
		endereco.setComplemento(complemento);
		endereco.setLogradouro(logradouro);
		endereco.setIdPessoa(idPessoa);
	}

	@Test
	public void TestEndereco() {
		Assert.assertEquals(endereco.getId(), id);
		Assert.assertEquals(endereco.getCep(), cep);
		Assert.assertEquals(endereco.getUf(), uf);
		Assert.assertEquals(endereco.getLocalidade(), localidade);
		Assert.assertEquals(endereco.getBairro(), bairro);
		Assert.assertEquals(endereco.getComplemento(), complemento);
		Assert.assertEquals(endereco.getLogradouro(), logradouro);
		Assert.assertEquals(endereco.getIdPessoa(), idPessoa);
	}
	
	@Test
	public void TestEndereco1() {
		endereco = new Endereco(id, cep, uf, localidade, bairro, complemento, logradouro, idPessoa);
		
		Assert.assertEquals(endereco.getId(), id);
		Assert.assertEquals(endereco.getCep(), cep);
		Assert.assertEquals(endereco.getUf(), uf);
		Assert.assertEquals(endereco.getLocalidade(), localidade);
		Assert.assertEquals(endereco.getBairro(), bairro);
		Assert.assertEquals(endereco.getComplemento(), complemento);
		Assert.assertEquals(endereco.getLogradouro(), logradouro);
		Assert.assertEquals(endereco.getIdPessoa(), idPessoa);
		Assert.assertEquals(endereco.toString(), stringTo);
	}

}
