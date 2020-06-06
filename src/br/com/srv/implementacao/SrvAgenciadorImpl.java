package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryAgenciador;
import br.com.srv.interfaces.SrvAgenciador;

@Service
public class SrvAgenciadorImpl implements SrvAgenciador {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryAgenciador repositoryAgenciador;

	public void setRepositoryBanco(RepositoryAgenciador repositoryAgenciador) {

		this.repositoryAgenciador = repositoryAgenciador;
	}
}