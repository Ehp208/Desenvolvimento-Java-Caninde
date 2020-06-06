package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryPessoaJuridica;
import br.com.srv.interfaces.SrvPessoaJuridica;

@Service
public class SrvPessoaJuridicaImpl implements SrvPessoaJuridica {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryPessoaJuridica repositoryPessoaJuridica;

	public void setRepositoryPessoaJuridica(RepositoryPessoaJuridica repositoryPessoaJuridica) {

		this.repositoryPessoaJuridica = repositoryPessoaJuridica;
	}

}