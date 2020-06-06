package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.PessoaMotorista;
import br.com.repository.interfaces.RepositoryPessoaMotorista;

@Repository
public class DaoPessoaMotorista extends ImplementacaoCrud<PessoaMotorista> implements	RepositoryPessoaMotorista {
	private static final long serialVersionUID = 1L;
}