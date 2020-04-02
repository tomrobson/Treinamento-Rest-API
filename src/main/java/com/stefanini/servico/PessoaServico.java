package com.stefanini.servico;

import com.stefanini.dao.PessoaDao;
import com.stefanini.exception.NegocioException;
import com.stefanini.model.Endereco;
import com.stefanini.model.Pessoa;

import javax.ejb.*;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.validation.Valid;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Base64.Decoder;
import java.util.regex.Pattern;

/**
 * 
 * Classe de servico, as regras de negocio devem estar nessa classe
 * 
 * @author joaopedromilhome
 *
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PessoaServico implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private PessoaDao dao;

	@Inject
	private PessoaPerfilServico pessoaPerfilServico;
	
	@Inject
	private EnderecoServico enderecoServico;

	/**
	 * Salvar os dados de uma Pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Pessoa salvar(@Valid Pessoa pessoa) {
		Optional<Endereco> endereco = pessoa.getEnderecos().stream().findFirst();
		
		if (!pessoa.getImagem().isEmpty()) {			
			pessoa.setImagem(decodeBase64(pessoa.getImagem()));
		}
		
		if (endereco.isPresent()) {
			pessoa.getEnderecos().clear();
			
			Pessoa pessoaCadastrada = dao.salvar(pessoa);
			Long id = pessoaCadastrada.getId();
			
			endereco.get().setIdPessoa(id);
			enderecoServico.salvar(endereco.get());
			
			return pessoaCadastrada;
		}
		return dao.salvar(pessoa);
	}
	/**
	 * Validando se existe pessoa com email
	 */
	public Boolean validarPessoa(@Valid Pessoa pessoa){
		if(pessoa.getId() != null){
			Optional<Pessoa> encontrar = dao.encontrar(pessoa.getId());
			if(encontrar.get().getEmail().equals(pessoa.getEmail())){
				return Boolean.TRUE;
			}
		}
		Optional<Pessoa> pessoa1 = dao.buscarPessoaPorEmail(pessoa.getEmail());
		return pessoa1.isEmpty();
	}

	/**
	 * Atualizar o dados de uma pessoa
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa atualizar(@Valid Pessoa entity) {
		if (entity.getImagem() != null) {
			String[] imagem = entity.getImagem().split(Pattern.quote(":"));
			
			if (imagem[0].equals("data")) {
				String[] img = entity.getImagem().split(Pattern.quote(","));
				
				entity.setImagem(decodeBase64(entity.getImagem()));
				
				if (!img[2].isEmpty()) {					
					deletarImagem(img[2]);
				}
			}
		}
		
		return dao.atualizar(entity);
	}

	/**
	 * Remover uma pessoa pelo id
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void remover(@Valid Long id) throws NegocioException {
		if(pessoaPerfilServico.buscarPessoaPerfil(id,null).count() == 0){
			dao.remover(id);
			return;
		}
		throw new NegocioException("Não foi possivel remover a pessoa");
	}

	/**
	 * Buscar uma lista de Pessoa
	 */
	public Optional<List<Pessoa>> getList() {
		return dao.getList();
	}

	/**
	 * Buscar uma Pessoa pelo ID
	 */
	public Optional<Pessoa> encontrar(Long id) {
		Optional<Pessoa> pessoa = dao.encontrar(id);
		
		if (pessoa.get().getImagem() != null) {
			String urlPath = "http://localhost:8080/treinamento/api/pessoas/imagem/imagem0.";
			String local = pessoa.get().getImagem();
			
			String[] separado = local.split(Pattern.quote("."));
			
			pessoa.get().setImagem(urlPath + separado[1] + "." + separado[2]);
		}
		
		return pessoa;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public FileInputStream urlImagem (String localImagem) {
		String local = "C:\\Users\\tomro\\eclipse-workspace\\Treinamento-Rest-API\\src\\image\\" + localImagem;
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(local);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return file;
	}
	
	public String decodeBase64 (String imagem) {
		imagem = imagem.split(Pattern.quote(","))[1];
		
		String url = "C:\\Users\\tomro\\eclipse-workspace\\Treinamento-Rest-API\\src\\image";
		String random = "\\imagem" + Math.random() + ".jpg";
		
		BufferedImage image = null;
		byte[] imageByte;
		
		try {
			imageByte = Base64.getDecoder().decode(imagem.getBytes());
			
			ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);
			image = ImageIO.read(bais);
			bais.close();
			ImageIO.write((RenderedImage) image, "jpg", new File(url + random));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url + random;
	}
	
	public void deletarImagem (String imagem) {
		String[] img = imagem.split(Pattern.quote("."));
		
		String url = "C:\\Users\\tomro\\eclipse-workspace\\Treinamento-Rest-API\\src\\image";
		String random = "\\imagem0." + img[1] + ".jpg";
		
		File file = new File(url + random);
		file.delete();
	}

}
