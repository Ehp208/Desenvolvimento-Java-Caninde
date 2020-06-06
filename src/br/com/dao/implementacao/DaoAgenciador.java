package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Agenciador;
import br.com.repository.interfaces.RepositoryAgenciador;

@Repository
public class DaoAgenciador extends ImplementacaoCrud<Agenciador> implements
		RepositoryAgenciador {

	private static final long serialVersionUID = 1L;
}