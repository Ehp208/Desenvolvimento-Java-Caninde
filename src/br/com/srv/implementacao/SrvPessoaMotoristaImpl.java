package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryPessoaMotorista;
import br.com.srv.interfaces.SrvPessoaMotorista;

@Service
public class SrvPessoaMotoristaImpl implements SrvPessoaMotorista {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryPessoaMotorista repositoryPessoaMotorista;

	public void setRepositoryPessoaMotorista(RepositoryPessoaMotorista repositoryPessoaMotorista) {

		this.repositoryPessoaMotorista = repositoryPessoaMotorista;
	}

}