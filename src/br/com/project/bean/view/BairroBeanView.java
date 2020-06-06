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
import br.com.project.geral.controller.BairroController;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Logradouro;
import br.com.project.util.all.Messagens;

@Controller
@Scope(value = "session")
@ManagedBean(name = "bairroBeanView")
public class BairroBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	private CarregamentoLazyListForObject<Bairro> list = new CarregamentoLazyListForObject<Bairro>();
	private Bairro objetoSelecionado = new Bairro();
	private String url = "/cadastro/localidade/cad_bairro.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/localidade/find_bairro.jsf?faces-redirect=true";

	@Resource
	private BairroController bairroController;

	public void setBairroController(BairroController bairroController) {
		this.bairroController = bairroController;
	}

	public BairroController getBairroController() {
		return bairroController;
	}
	
	public List<Bairro> pesquisarBairroGenerico(String nome) throws Exception {
		List listBairro = bairroController.pesquisarPorNomeGenerico(nome);		
		return listBairro;
	}

	private String urlBairro = "/cadastro/localidade/cad_bairrorapido.jsf?faces-redirect=true";
	
	public void cadastraBairro() {
		objetoSelecionado = new Bairro();
		
		Map<String, Object> telaBairro = new HashMap<>();
		telaBairro.put("modal", true);
		telaBairro.put("resizable", false);
		telaBairro.put("contentHeight", 470);
		
		RequestContext.getCurrentInstance().openDialog(urlBairro, telaBairro, null);
	}

	public void salvarBairro() throws Exception {
		try {
			if(! objetoSelecionado.getBai_nome().isEmpty()) 
			{
				if( bairroController.existeBairro(objetoSelecionado.getBai_nome()))
				{ Messagens.msgSeverityInfor("Já existe bairro cadastrado com este nome ...!"); }
				else
				{
					objetoSelecionado = bairroController.merge(objetoSelecionado);
					Messagens.msgSeverityInfor("Registro incluído com sucesso ...!");
					bairroController.getListBairro();
					finalizarBairro();
				}				
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarBairro() {
		RequestContext.getCurrentInstance().closeDialog(objetoSelecionado);
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_bairro");
		super.setNomeRelatorioSaida("report_bairro");
		List<?> list = bairroController.finListOrderByProperty(Bairro.class, "bai_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	/**
	 * Ivocado pelo botão novo
	 */
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	/**
	 * Ivocado pelo botão salvar
	 */
	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = bairroController.merge(objetoSelecionado);
			list.getList().add(objetoSelecionado);
			objetoSelecionado = new Bairro();
			sucesso();
		}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}
	
	/**
	 * Ivocado pelo botão exlcluir
	 */
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getBai_id() != null
				&& objetoSelecionado.getBai_id() > 0) {
			bairroController.delete(objetoSelecionado);
			list.getList().remove(objetoSelecionado);
			objetoSelecionado = new Bairro();
			sucesso();
		}
	}

	/**
	 * Ivocado pelo botão consultar
	 */
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Bairro();
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

	@Override
	@RequestMapping({ "**/find_bairro" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Bairro();
	}

	@Override
	protected Class<?> getClassImplement() {
		return Bairro.class;
	}

	@Override
	protected InterfaceCrud<?> getController() {
		return bairroController;
	}

	public List<SelectItem> getBairros() {
		return bairroController.getListBairro();
	}
	
	public CarregamentoLazyListForObject<Bairro> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Bairro> list) {
		this.list = list;
	}

	public Bairro getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Bairro objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@RequestMapping("**/findBairro")
	public void findBairro(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codBairro") Long codBairro)
			throws Exception {
		Bairro bairro = bairroController.findUninqueByPropertyId(
				Bairro.class, codBairro, "bai_id");
		if (bairro != null) {
			httpServletResponse.getWriter().write(bairro.getJson().toString());
		}
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
	
	public Object onRowSelect(SelectEvent event) {
		objetoSelecionado = (Bairro) super.onRowSelect(event);
		return null;
	}
	
	public Object onRowUnselect(UnselectEvent event) {
		objetoSelecionado = (Bairro) super.onRowUnselect(event);
		return null;
	}

/**
 * 					CUSTOMIZAÇÕES EFETUADAS POR EDUARDO H. PAULA 
*/
		
//	public void atualizaBairro(AjaxBehaviorEvent event) {
//		Bairro bairroAux = (Bairro) ((HtmlSelectOneMenu) event.getSource()).getValue();
//		setObjetoSelecionado(bairroAux);
//	}	
}
