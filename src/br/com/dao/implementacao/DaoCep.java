package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Cep;
import br.com.repository.interfaces.RepositoryCep;

@Repository
public class DaoCep extends ImplementacaoCrud<Cep> implements
		RepositoryCep {

	private static final long serialVersionUID = 1L;
}