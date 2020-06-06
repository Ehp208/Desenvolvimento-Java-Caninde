package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.PessoaFisica;
import br.com.repository.interfaces.RepositoryPessoaFisica;

@Repository
public class DaoPessoaFisica extends ImplementacaoCrud<PessoaFisica> implements	RepositoryPessoaFisica {
	private static final long serialVersionUID = 1L;
}