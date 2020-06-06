package br.com.project.geral.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Cep;
import br.com.project.model.classes.Pais;
import br.com.project.model.classes.PessoaFisica;

@Controller
public class CepController extends ImplementacaoCrud<Cep> implements InterfaceCrud<Cep> {
	
	private static final long serialVersionUID = 1L;

	public List<SelectItem> getListCep() throws Exception {
		List<SelectItem> list = new ArrayList<SelectItem>();
		
/**
 * 		List<Cep> ceps = super.finListOrderByProperty(Cep.class, "cep_codigo");
 */

		List<Cep> ceps = super.findListByQueryDinamica(" from Cep");
		
		for (Cep e : ceps) {
			list.add(new SelectItem(e, e.getCep_codigo()));
		}
		return list;
	}

	public List<Cep> pesquisarPorCodigo(String codigo, Boolean unico) throws Exception {
		List<Cep> list = (List<Cep>) super.findListByQueryDinamica("from Cep where cep_codigo = '" + codigo + "' and cep_unico = '" + unico + "'");		
		return list;
	}
	
	public List<Cep> pesquisarPorNome(String nome) throws Exception {
		List<Cep> list = (List<Cep>) super.findListByQueryDinamica("from Cep where retira_acentos(upper(registroLogradouro.log_nome)) = retira_acentos(upper('" + nome + "'))");		
		return list;
	}

	public List<Cep> pesquisarPorCepUnico(String cod, String log, String bai) throws Exception {
		List<Cep> list = (List<Cep>) super.findListByQueryDinamica("from Cep where cep_codigo = '" + cod + "' and retira_acentos(upper(registroLogradouro.log_nome)) = retira_acentos(upper('" + log + "')) and retira_acentos(upper(registroBairro.bai_nome)) = retira_acentos(upper('" + bai + "'))");		
		return list;
	}

	public boolean existeCep(String cep_codigo) throws Exception {
		List<Cep> list = (List<Cep>) super.findListByQueryDinamica("from Cep where cep_codigo = '" + cep_codigo + "' and cep_unico'");
		if (list.size() == 0) {
			return false;
		} else {
			return true;
		}
	}	
}
