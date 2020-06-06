package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Logradouro;
import br.com.repository.interfaces.RepositoryLogradouro;

@Repository
public class DaoLogradouro extends ImplementacaoCrud<Logradouro> implements
		RepositoryLogradouro {

	private static final long serialVersionUID = 1L;
}