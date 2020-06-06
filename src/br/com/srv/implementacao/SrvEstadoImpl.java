package br.com.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.repository.interfaces.RepositoryEstado;
import br.com.srv.interfaces.SrvEstado;

@Service
public class SrvEstadoImpl implements SrvEstado {
	
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryEstado repositoryEstado;

	public void setRepositoryEstado(RepositoryEstado repositoryEstado) {

		this.repositoryEstado = repositoryEstado;
	}

}