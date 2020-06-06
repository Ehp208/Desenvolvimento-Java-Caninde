package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.PessoaJuridica;
import br.com.repository.interfaces.RepositoryPessoaJuridica;

@Repository
public class DaoPessoaJuridica extends ImplementacaoCrud<PessoaJuridica> implements	RepositoryPessoaJuridica {
	private static final long serialVersionUID = 1L;
}