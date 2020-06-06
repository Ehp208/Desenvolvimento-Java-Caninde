package br.com.project.bean.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.LogradouroController;
import br.com.project.model.classes.Logradouro;
import br.com.project.model.classes.PessoaFisica;
import br.com.project.util.all.Messagens;

@Controller
@Scope(value = "session")
@ManagedBean(name = "logradouroBeanView")
public class LogradouroBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<Logradouro> list = new CarregamentoLazyListForObject<Logradouro>();
	
	private Logradouro objetoSelecionado = new Logradouro();
	
	private String url = "/cadastro/localidade/cad_logradouro.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/localidade/find_logradouro.jsf?faces-redirect=true";

	@Resource
	private LogradouroController logradouroController;
			
	public void setLogradouroController(LogradouroController logradouroController) {
		this.logradouroController = logradouroController;
	}

	public LogradouroController getLogradouroController() {
		return logradouroController;
	}
	
	public List<Logradouro> pesquisarLogradouroGenerico(String nome) throws Exception {
		List listLogradouro = logradouroController.pesquisarPorNomeGenerico(nome);		
		return listLogradouro;
	}

	private String urlLogradouro = "/cadastro/localidade/cad_logradourorapido.jsf?faces-redirect=true";
	
	public void cadastraLogradouro() {
		objetoSelecionado = new Logradouro();
		
		Map<String, Object> telaLogradouro = new HashMap<>();
		telaLogradouro.put("modal", true);
		telaLogradouro.put("resizable", false);
		telaLogradouro.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog(urlLogradouro, telaLogradouro, null);
	}

	public void salvarLogradouro() throws Exception {
		try {
			if(! objetoSelecionado.getLog_nome().isEmpty()) 
			{
				if( logradouroController.existeLogradouro(objetoSelecionado.getLog_nome()))
				{ Messagens.msgSeverityInfor("Já existe logradouro cadastrado com este nome ...!"); }
				else
				{
					objetoSelecionado = logradouroController.merge(objetoSelecionado);
					Messagens.msgSeverityInfor("Registro incluído com sucesso ...!");
					logradouroController.getListLogradouro();
					finalizarLogradouro();
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarLogradouro() {
		RequestContext.getCurrentInstance().closeDialog(objetoSelecionado);
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_logradouro");
		super.setNomeRelatorioSaida("report_logradouro");
		List<?> list = logradouroController.finListOrderByProperty(Logradouro.class, "log_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
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
	 * Invocado pelo botão salvar
	 */
	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = logradouroController.merge(objetoSelecionado);
			list.getList().add(objetoSelecionado);
			objetoSelecionado = new Logradouro();
			sucesso();
		}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	/**
	 * Invocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getLog_id() != null && objetoSelecionado.getLog_id() > 0) {
			logradouroController.delete(objetoSelecionado);
			list.getList().remove(objetoSelecionado);
			objetoSelecionado = new Logradouro();
			sucesso();
		}
	}

	/**
	 * Invocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Logradouro();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Invocado pelo botão consultar
	 */
	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}
	
	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@Override
	@RequestMapping({ "**/find_logradouro" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Logradouro();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Logradouro.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return logradouroController;
	}
	
	public List<SelectItem> getLogradouros() {
		return logradouroController.getListLogradouro();
	}
	
	public CarregamentoLazyListForObject<Logradouro> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Logradouro> list) {
		this.list = list;
	}

	public Logradouro getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Logradouro objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@RequestMapping("**/findLogradouro")
	public void findLogradouro(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idLogradouro") Long idLogradouro)
			throws Exception {
			Logradouro logradouro = logradouroController.findUninqueByPropertyId(Logradouro.class, idLogradouro, "log_id");
			if (logradouro != null) {
				httpServletResponse.getWriter().write(logradouro.getJson().toString());
			}
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Logradouro) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Logradouro) super.onRowUnselect(event);
		return null;
	}	
	
/**
 * 					CUSTOMIZAÇÕES EFETUADAS POR EDUARDO H. PAULA 
 */
	
//	public void atualizaLogradouro(AjaxBehaviorEvent event) {
//		Logradouro logradouroAux = (Logradouro) ((HtmlSelectOneMenu) event.getSource()).getValue();
//		setObjetoSelecionado(logradouroAux);
//	}

}
