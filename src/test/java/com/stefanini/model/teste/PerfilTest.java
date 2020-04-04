package com.stefanini.model.teste;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import com.stefanini.model.Perfil;

import mockit.Injectable;
import mockit.Tested;

public class PerfilTest {

	@Injectable
    EntityManager em;
	
	@Tested
	Perfil perfil;
	
	@Test
	public void TestPerfil() {
		Long id = 1L;
		String nome = "ADMIN";
		String descricao = "DESCRICAO DE ADMIN";
		LocalDateTime dataHoraInclusao = null;
		dataHoraInclusao = dataHoraInclusao.now();
		LocalDateTime dataHoraAlteracao = null;
		dataHoraAlteracao = dataHoraAlteracao.now();
		String stringTo =
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
		
		Assert.assertEquals(perfil.getId(), id);
		Assert.assertEquals(perfil.getNome(), nome);
		Assert.assertEquals(perfil.getDescricao(), descricao);
		Assert.assertEquals(perfil.getDataHoraInclusao(), dataHoraInclusao);
		Assert.assertEquals(perfil.getDataHoraAlteracao(), dataHoraAlteracao);
		Assert.assertEquals(perfil.toString(), stringTo);
	}
	
	@Test
	public void TestPerfil1() {
		Long id = 1L;
		String nome = "ADMIN";
		String descricao = "DESCRICAO DE ADMIN";
		LocalDateTime dataHoraInclusao = null;
		dataHoraInclusao = dataHoraInclusao.now();
		LocalDateTime dataHoraAlteracao = null;
		dataHoraAlteracao = dataHoraAlteracao.now();
		
		perfil = new Perfil(nome, descricao, dataHoraInclusao, dataHoraAlteracao);
		
		Assert.assertEquals(perfil.getNome(), nome);
		Assert.assertEquals(perfil.getDescricao(), descricao);
		Assert.assertEquals(perfil.getDataHoraInclusao(), dataHoraInclusao);
		Assert.assertEquals(perfil.getDataHoraAlteracao(), dataHoraAlteracao);
		
		perfil = new Perfil(id);
		Assert.assertEquals(perfil.getId(), id);
	}

}
