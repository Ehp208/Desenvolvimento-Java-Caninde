package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryCep;
import br.com.srv.interfaces.SrvCep;

@Service
public class SrvCepImpl implements SrvCep {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryCep repositoryCep;

	public void setRepositoryCep(RepositoryCep repositoryCep) {

		this.repositoryCep = repositoryCep;
	}

}