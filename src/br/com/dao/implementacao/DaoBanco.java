package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Banco;
import br.com.repository.interfaces.RepositoryBanco;

@Repository
public class DaoBanco extends ImplementacaoCrud<Banco> implements
		RepositoryBanco {

	private static final long serialVersionUID = 1L;
}