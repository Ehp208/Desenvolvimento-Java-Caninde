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
import br.com.project.geral.controller.BancoController;
import br.com.project.model.classes.Banco;

@Controller
@Scope(value = "session")
@ManagedBean(name = "bancoBeanView")
public class BancoBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<Banco> list = new CarregamentoLazyListForObject<Banco>();
	
	private Banco objetoSelecionado = new Banco();
	
	private String url = "/financeiro/cadastro/cad_banco.jsf?faces-redirect=true";
	private String urlFind = "/financeiro/cadastro/find_banco.jsf?faces-redirect=true";

	@Resource
	private BancoController bancoController;

	public void setBancoController(BancoController bancoController) {
		this.bancoController = bancoController;
	}

	public BancoController getBancoController() {
		return bancoController;
	}
	
	public Banco getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Banco objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}	
	
	public CarregamentoLazyListForObject<Banco> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Banco> list) {
		this.list = list;
	}
	
	@Override
	@RequestMapping(value = { "**/find_banco" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		dataInicialPesquisa = "";
		dataFinalPesquisa = "";
		valorInicialPesquisa = "";
		valorFinalPesquisa = "";
		list.clear();
		objetoSelecionado = new Banco();
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
		dataInicialPesquisa = "";
		dataFinalPesquisa = "";
		valorInicialPesquisa = "";
		valorFinalPesquisa = "";
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
			objetoSelecionado = new Banco();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Invocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getBan_id() != null
				&& objetoSelecionado.getBan_id() > 0) {
			bancoController.delete(objetoSelecionado);
			list.getList().remove(objetoSelecionado);
			objetoSelecionado = new Banco();
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
				objetoSelecionado = bancoController.merge(objetoSelecionado);
				list.getList().add(objetoSelecionado);
				objetoSelecionado = new Banco();
				sucesso();
			}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	@Override
	protected Class<?> getClassImplement() {
		return Banco.class;
	}
	
	@Override
	protected InterfaceCrud<?> getController() {
		return bancoController;
	}

	/**
	 *   Relatórios
	 *
	 */
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_banco");
		super.setNomeRelatorioSaida("report_banco");
		List<?> list = bancoController.finListOrderByProperty(Banco.class, "ban_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@RequestMapping("**/findBanco")
	public void findBanco(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idBanco") Long idBanco)
			throws Exception {
			Banco banco = bancoController.findUninqueByPropertyId(
					Banco.class, idBanco, "ban_id");
			if (banco != null) {
				httpServletResponse.getWriter().write(
						banco.getJson().toString());
			}

	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Banco) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Banco) super.onRowUnselect(event);
		return null;
	}	 
}
