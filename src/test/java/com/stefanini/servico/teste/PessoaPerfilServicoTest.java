package com.stefanini.servico.teste;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.stefanini.dao.PessoaDao;
import com.stefanini.dao.PessoaPerfilDao;
import com.stefanini.model.Perfil;
import com.stefanini.model.Pessoa;
import com.stefanini.model.PessoaPerfil;
import com.stefanini.servico.PessoaPerfilServico;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;

public class PessoaPerfilServicoTest {

	@Injectable
    EntityManager em;
	
    @Tested
    PessoaPerfilServico pessoaPerfilServico;
    
    @Injectable
    @Mocked
    PessoaPerfilDao pessoaPerfilDao;
    
    @Tested
    PessoaPerfil pessoaPerfil;
    
    @Tested
    Pessoa pessoa;
    
    @Tested
    Perfil perfil;
    
    private Long idPessoa;
    private Long idPerfil;
    
    @Before
    public void setUp() {
    	idPessoa = 3L;
    	idPerfil = 5L;
    	
    	pessoa = new Pessoa();
    	perfil = new Perfil();
    	
    	pessoa.setId(idPessoa);
    	perfil.setId(idPerfil);
    	
    	pessoaPerfil = new PessoaPerfil(perfil, pessoa);
    }
    
	@Test
	public void TestPessoaPerfil() {
//		new Expectations() {
//			{
//				pessoaPerfilDao.buscarPessoaPerfil(anyLong, anyLong);
//				result = pessoaPerfil;
//			}
//		};
//		
//		Stream<PessoaPerfil> stream = pessoaPerfilServico.buscarPessoaPerfil(idPessoa, idPerfil);
//		
//		Assert.assertEquals(stream.collect(Collectors.toMap(p -> p.getId(), null)), idPerfil);
	}

}
