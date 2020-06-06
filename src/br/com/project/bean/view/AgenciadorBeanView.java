package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.geral.controller.AgenciadorController;
import br.com.project.model.classes.Agenciador;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Mensagem;

@Controller
@Scope(value = "session")
@ManagedBean(name = "agenciadorBeanView")
public class AgenciadorBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<Agenciador> list = new CarregamentoLazyListForObject<Agenciador>();
	
	private Agenciador objetoSelecionado = new Agenciador();
	
	private String url = "/cadastro/cad_agenciador.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_agenciador.jsf?faces-redirect=true";

	@Resource
	private AgenciadorController agenciadorController;

	public void setAgenciadorController(AgenciadorController agenciadorController) {
		this.agenciadorController = agenciadorController;
	}

	public AgenciadorController getAgenciadorController() {
		return agenciadorController;
	}
	
	public Agenciador getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Agenciador objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}	
	
	public CarregamentoLazyListForObject<Agenciador> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Agenciador> list) {
		this.list = list;
	}
	
	@Override
	@RequestMapping(value = { "**/find_agenciador" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Agenciador();
	}
	
	/**
	 * Invocado pelo botão novo
	 */
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	/**
	 * Editar
	 */
	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}
	
	/**
	 * Invocado pelo botão consultar
	 */
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}
	
	/**
	 * Invocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Agenciador();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Invocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getAge_id() != null
				&& objetoSelecionado.getAge_id() > 0) {
			agenciadorController.delete(objetoSelecionado);
			list.getList().remove(objetoSelecionado);
			objetoSelecionado = new Agenciador();
			sucesso();
		}
	}
	
	/**
	 * Invocado pelo botão salvar
	 */
	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
				list.clear();
				objetoSelecionado = agenciadorController.merge(objetoSelecionado);
				list.getList().add(objetoSelecionado);
				objetoSelecionado = new Agenciador();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	protected Class<?> getClassImplement() {
		return Agenciador.class;
	}
	
	@Override
	protected InterfaceCrud<?> getController() {
		return agenciadorController;
	}

	/**
	 *   Relatórios
	 *
	 */
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_agenciador");
		super.setNomeRelatorioSaida("report_agenciador");
		List<?> list = agenciadorController.finListOrderByProperty(Agenciador.class, "age_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@RequestMapping("**/findAgenciador")
	public void findAgenciador(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idAgenciador") Long idAgenciador)
			throws Exception {
		Agenciador agenciador = agenciadorController.findUninqueByPropertyId(
				Agenciador.class, idAgenciador, "age_id");
		if (agenciador != null) {
			httpServletResponse.getWriter().write(agenciador.getJson().toString());
		}
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Agenciador) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Agenciador) super.onRowUnselect(event);
		return null;
	}	 
		
/********************      FIM DAS ROTINAS STANDBY        ******************/	
	
/**
 * 			MUDANÇAS NA EVOLUÇÃO DO SISTEMA
 * 		
 * 			AUTOR: Eduardo H. Paula
*/

	public List<SelectItem> getAgenciadores() {
		return agenciadorController.getListAgenciador();
	}
			
//	public void atualizaAgenciador(AjaxBehaviorEvent event) {
//		Agenciador agenciadorAux = (Agenciador) ((HtmlSelectOneMenu) event.getSource()).getValue();
//		setObjetoSelecionado(agenciadorAux);
//	}	
	
}
