package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryPessoaFisica;
import br.com.srv.interfaces.SrvPessoaFisica;

@Service
public class SrvPessoaFisicaImpl implements SrvPessoaFisica {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryPessoaFisica repositoryPessoaFisica;

	public void setRepositoryPessoaFisica(RepositoryPessoaFisica repositoryPessoaFisica) {

		this.repositoryPessoaFisica = repositoryPessoaFisica;
	}

}