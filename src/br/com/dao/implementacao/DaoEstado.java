package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Estado;
import br.com.repository.interfaces.RepositoryEstado;

@Repository
public class DaoEstado extends ImplementacaoCrud<Estado> implements
		RepositoryEstado {
	
	private static final long serialVersionUID = 1L;
}