package br.com.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Banco;
import br.com.repository.interfaces.RepositoryBanco;
import br.com.srv.interfaces.SrvBanco;

@Controller
public class BancoController extends ImplementacaoCrud<Banco> implements
		InterfaceCrud<Banco> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvBanco srvBanco;
	@Resource
	private RepositoryBanco repositoryBanco;

	public void setSrvBanco(SrvBanco srvBanco) {
		this.srvBanco = srvBanco;
	}

	public void setRepositoryBanco(RepositoryBanco repositoryBanco) {
		this.repositoryBanco = repositoryBanco;
	}

}
