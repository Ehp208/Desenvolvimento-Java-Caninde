package br.com.project.bean.view;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.PessoaJuridicaController;
import br.com.project.model.classes.PessoaJuridica;
import br.com.project.util.all.Messagens;

@Controller
@Scope("session")
@ManagedBean(name = "pessoaJuridicaBeanView")
public class PessoaJuridicaBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<PessoaJuridica> list = new CarregamentoLazyListForObject<PessoaJuridica>();

	private PessoaJuridica objetoSelecionado = new PessoaJuridica();

	private String url = "/administrativo/cad_pessoaJuridica.jsf?faces-redirect=true";
	private String urlFind = "/administrativo/find_pessoaJuridica.jsf?faces-redirect=true";
	
	public Boolean digitarDadosPessoaJuridica;
	
	public Boolean getDigitarDadosPessoaJuridica() {
		return digitarDadosPessoaJuridica;
	}

	public void setDigitarDadosPessoaJuridica(Boolean digitarDadosPessoaJuridica) {
		this.digitarDadosPessoaJuridica = digitarDadosPessoaJuridica;
	}

	@PostConstruct
	public void init() {
		setDigitarDadosPessoaJuridica(false);
	}
	@Autowired
	private PessoaJuridicaController pessoaJuridicaController;

	public void setPessoaJuridicaController(PessoaJuridicaController pessoaJuridicaController) {
		this.pessoaJuridicaController = pessoaJuridicaController;
	}

	public PessoaJuridicaController getPessoaJuridicaController() {
		return pessoaJuridicaController;
	}
	
	public Void pesquisaCnpj(AjaxBehaviorEvent event) throws Exception {		
		String cnpjPesquisa = objetoSelecionado.getPju_cnpj();
		setarVariaveisNulas();
	
		try {			
			List<PessoaJuridica> listPesJurAux = pessoaJuridicaController.findListByProperty(PessoaJuridica.class, "pju_cnpj", cnpjPesquisa);
			
			if (listPesJurAux.size() == 1) {
				objetoSelecionado.setPju_id(listPesJurAux.get(0).getPju_id());
				objetoSelecionado.setPju_ie(listPesJurAux.get(0).getPju_ie());
				objetoSelecionado.setPju_dataFundacao(listPesJurAux.get(0).getPju_dataFundacao());
				objetoSelecionado.setPju_foto(listPesJurAux.get(0).getPju_foto());
				objetoSelecionado.setPju_inativo(listPesJurAux.get(0).getPju_inativo());
				setDigitarDadosPessoaJuridica(false);
			} else {
				setDigitarDadosPessoaJuridica(true);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_pessoaJuridica");
		super.setNomeRelatorioSaida("report_pessoaJuridica");
		List<?> list = pessoaJuridicaController.finListOrderByProperty(PessoaJuridica.class, "pessoaJuridica.pju_nome");
		super.setListDataBeanColletionReport(list);
		return super.getArquivoReport();
	}

	@Override
	protected Class<?> getClassImplement() {
		return PessoaJuridica.class;
	}

	public void setObjetoSelecionado(PessoaJuridica objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	public PessoaJuridica getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<PessoaJuridica> getList() {
		return list;
	}

	@Override
	@RequestMapping(value = { "**/find_pessoaJuridica" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new PessoaJuridica();
	}

	@Override
	public void saveNotReturn() throws Exception {
		Boolean continuaGravando = true;
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();

			if (pessoaJuridicaController.existeCnpj(objetoSelecionado.getPju_cnpj(), objetoSelecionado.getPju_id())) {
				Messagens.msgSeverityInfor("CNPJ Já cadastrado!...");
				continuaGravando = false;
			} else if (pessoaJuridicaController.existeIe(objetoSelecionado.getPju_ie(), objetoSelecionado.getPju_id())) {
				Messagens.msgSeverityInfor("Inscrição Estadual Já cadastrada!...");
				continuaGravando = false;
			} 
				
			if (continuaGravando) {
				objetoSelecionado = pessoaJuridicaController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new PessoaJuridica();
				sucesso();
			}
		}
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getPju_id() != null && objetoSelecionado.getPju_id() > 0) {
			pessoaJuridicaController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new PessoaJuridica();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new PessoaJuridica();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	/**
	 * Ivocado pelo botão consultar
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

	@RequestMapping("**/findPessoaJuridica")
	public void findPessoaJuridica(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idPessoaJuridica") Long idPessoaJuridica) throws Exception {

		PessoaJuridica pessoaJuridica = pessoaJuridicaController.findUninqueByPropertyId(PessoaJuridica.class, idPessoaJuridica,
				"pju_id");
		if (pessoaJuridica != null) {
			httpServletResponse.getWriter().write(pessoaJuridica.getJson().toString());
		}

	}

	@Override
	protected InterfaceCrud<PessoaJuridica> getController() {
		return pessoaJuridicaController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
}
