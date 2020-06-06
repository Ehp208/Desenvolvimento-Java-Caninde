package br.com.project.bean.view;

import java.util.List;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
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
import br.com.project.geral.controller.PessoaMotoristaController;
import br.com.project.model.classes.PessoaMotorista;

@Controller
@Scope(value = "session")
@ManagedBean(name = "pessoaMotoristaBeanView")
public class PessoaMotoristaBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<PessoaMotorista> list = new CarregamentoLazyListForObject<PessoaMotorista>();
	
	private PessoaMotorista objetoSelecionado = new PessoaMotorista();
	
	private String url = "/cadastro/cad_pessoaMotorista.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_pessoaMotorista.jsf?faces-redirect=true";

	@Resource
	private PessoaMotoristaController pessoaMotoristaController;

	public void setPessoaMotoristaController(PessoaMotoristaController pessoaMotoristaController) {
		this.pessoaMotoristaController = pessoaMotoristaController;
	}

	public PessoaMotoristaController getPessoaMotoristaController() {
		return pessoaMotoristaController;
	}
	
	public PessoaMotorista getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(PessoaMotorista objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}	
	
	public CarregamentoLazyListForObject<PessoaMotorista> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<PessoaMotorista> list) {
		this.list = list;
	}
	
	@Override
	@RequestMapping(value = { "**/find_pessoamotorista" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new PessoaMotorista();
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
			objetoSelecionado = new PessoaMotorista();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Invocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getPmo_id() != null
				&& objetoSelecionado.getPmo_id() > 0) {
			pessoaMotoristaController.delete(objetoSelecionado);
			list.getList().remove(objetoSelecionado);
			objetoSelecionado = new PessoaMotorista();
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
				objetoSelecionado = pessoaMotoristaController.merge(objetoSelecionado);
				list.getList().add(objetoSelecionado);
				objetoSelecionado = new PessoaMotorista();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	protected Class<?> getClassImplement() {
		return PessoaMotorista.class;
	}
	
	@Override
	protected InterfaceCrud<?> getController() {
		return pessoaMotoristaController;
	}

	/**
	 *   Relatórios
	 *
	 */
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_pessoaMotorista");
		super.setNomeRelatorioSaida("report_pessoaMotorista");
		List<?> list = pessoaMotoristaController.finListOrderByProperty(PessoaMotorista.class, "pmo_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@RequestMapping("**/findPessoaMotorista")
	public void findPessoaMotorista(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idPessoaMotorista") Long idPessoaMotorista)
			throws Exception {
			PessoaMotorista pessoaMotorista = pessoaMotoristaController.findUninqueByPropertyId(
					PessoaMotorista.class, idPessoaMotorista, "pmo_id");
			if (pessoaMotorista != null) {
				httpServletResponse.getWriter().write(
						pessoaMotorista.getJson().toString());
			}

	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (PessoaMotorista) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (PessoaMotorista) super.onRowUnselect(event);
		return null;
	}	 
}
