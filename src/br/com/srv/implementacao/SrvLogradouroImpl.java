package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryLogradouro;
import br.com.srv.interfaces.SrvLogradouro;

@Service
public class SrvLogradouroImpl implements SrvLogradouro {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryLogradouro repositoryLogradouro;

	public void setRepositoryLogradouro(RepositoryLogradouro repositoryLogradouro) {

		this.repositoryLogradouro = repositoryLogradouro;
	}
}