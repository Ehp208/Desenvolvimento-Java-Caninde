package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Estado;
import br.com.project.model.classes.Pais;

@Controller
public class UsuarioController extends ImplementacaoCrud<Pais> implements InterfaceCrud<Pais> {
	
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListPais() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<Pais> paises = super.finListOrderByProperty(Pais.class, "pai_nome");
		for (Pais p : paises) {
			list.add(new SelectItem(p, p.getPai_nome()));
		}
		return list;
	}
	
	public List<Pais> pesquisarPorNome(String nome) throws Exception {
		List<Pais> list = (List<Pais>) getSession().createQuery("select p from Pais p where p.pai_nome like '%" + nome + "%'").list();
		return list;
	}	
}
