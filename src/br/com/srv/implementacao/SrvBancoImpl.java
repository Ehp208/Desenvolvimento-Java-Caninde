package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryBanco;
import br.com.srv.interfaces.SrvBanco;

@Service
public class SrvBancoImpl implements SrvBanco {

	private static final long serialVersionUID = 1L;

	@Resource
	private RepositoryBanco repositoryBanco;

	public void setRepositoryBanco(RepositoryBanco repositoryBanco) {

		this.repositoryBanco = repositoryBanco;
	}
}