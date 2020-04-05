package com.stefanini.model.teste;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.model.Perfil;

import mockit.Injectable;
import mockit.Tested;

public class PerfilTest {

	@Injectable
    EntityManager em;
	
	@Tested
	Perfil perfil;
	
	private Long id;
	private String nome;
	private String descricao;
	private LocalDateTime dataHoraInclusao = null;
	private LocalDateTime dataHoraAlteracao = null;
	private String stringTo;
	
	@Before
	public void setUp() {
		id = 1L;
		nome = "ADMIN";
		descricao = "DESCRICAO DE ADMIN";
		dataHoraInclusao = dataHoraInclusao.now();
		dataHoraAlteracao = dataHoraAlteracao.now();
		stringTo =
			"Perfil{" +
	                "id=" + id +
	                ", nome='" + nome + '\'' +
	                ", descricao='" + descricao + '\'' +
	                ", dataHoraInclusao=" + dataHoraInclusao +
	                ", dataHoraAlteracao=" + dataHoraAlteracao +
// 		                ", pessoas=" + pessoas +
	                '}';
		
		perfil = new Perfil();
		
		perfil.setId(id);
		perfil.setNome(nome);
		perfil.setDescricao(descricao);
		perfil.setDataHoraInclusao(dataHoraInclusao);
		perfil.setDataHoraAlteracao(dataHoraAlteracao);
	}
	
	@Test
	public void TestPerfil() {
		Assert.assertEquals(perfil.getId(), id);
		Assert.assertEquals(perfil.getNome(), nome);
		Assert.assertEquals(perfil.getDescricao(), descricao);
		Assert.assertEquals(perfil.getDataHoraInclusao(), dataHoraInclusao);
		Assert.assertEquals(perfil.getDataHoraAlteracao(), dataHoraAlteracao);
		Assert.assertEquals(perfil.toString(), stringTo);
	}
	
	@Test
	public void TestPerfil1() {
		perfil = new Perfil(nome, descricao, dataHoraInclusao, dataHoraAlteracao);
		
		Assert.assertEquals(perfil.getNome(), nome);
		Assert.assertEquals(perfil.getDescricao(), descricao);
		Assert.assertEquals(perfil.getDataHoraInclusao(), dataHoraInclusao);
		Assert.assertEquals(perfil.getDataHoraAlteracao(), dataHoraAlteracao);
		
		perfil = new Perfil(id);
		Assert.assertEquals(perfil.getId(), id);
	}

}
