package br.com.project.geral.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.PessoaMotorista;
import br.com.repository.interfaces.RepositoryPessoaMotorista;
import br.com.srv.interfaces.SrvPessoaMotorista;

@Controller
public class PessoaMotoristaController extends ImplementacaoCrud<PessoaMotorista> implements InterfaceCrud<PessoaMotorista> {

	private static final long serialVersionUID = 1L;

	@Resource
	private SrvPessoaMotorista srvPessoaMotorista;

	@Resource
	private RepositoryPessoaMotorista repositoryPessoaMotorista;

	public void setSrvPessoaMotorista(SrvPessoaMotorista srvPessoaMotorista) {
		this.srvPessoaMotorista = srvPessoaMotorista;
	}

	public void setRepositoryPessoaMotorista(RepositoryPessoaMotorista repositoryPessoaMotorista) {
		this.repositoryPessoaMotorista = repositoryPessoaMotorista;
	}
	
	
	public List<PessoaMotorista> pesquisarMotoristaExiste(Long idEntidadeMotorista) throws Exception {		
		List<PessoaMotorista> list = (List<PessoaMotorista>) super.findListByQueryDinamica("from PessoaMotorista p where p.registroentidade.ent_id = '" + idEntidadeMotorista + "'");
		return list;
	}	
}
