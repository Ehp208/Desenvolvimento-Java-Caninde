package br.com.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Pais;
import br.com.repository.interfaces.RepositoryPais;

@Repository
public class DaoPais extends ImplementacaoCrud<Pais> implements
		RepositoryPais {
	
	private static final long serialVersionUID = 1L;
}