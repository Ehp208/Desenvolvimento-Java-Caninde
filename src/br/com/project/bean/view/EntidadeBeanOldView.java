package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.BairroController;
import br.com.project.geral.controller.CidadeController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Entidade;
import br.com.project.util.all.Messagens;


public class EntidadeBeanOldView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	private Entidade objetoSelecionado = new Entidade();
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	private String associacaoText = "";
	private String associacaoTextDescricao = "";

	private String url = "/cadastro/cad_entidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_entidade.jsf?faces-redirect=true";

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	@Autowired
	private CidadeController cidadeController;

	@Autowired
	private BairroController bairroController;


	public String getUsuarioLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {

		String nomeReport = null;

		TipoCadastro tipoCadastro = getTipoEntidadeTemp();

		if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CLIENTE))
			nomeReport = "report_cliente";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			nomeReport = "report_fornecedor";

		super.setNomeRelatorioJasper(nomeReport);
		super.setNomeRelatorioSaida(nomeReport);
		List<?> list = entidadeController.findListByProperty(Entidade.class,
				"ent_tipo", tipoCadastro.name());
		super.setListDataBeanColletionReport(list);

		return super.getArquivoReport();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		if (!getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_USUARIO))
			return url;
		else
			return null;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			objetoSelecionado.setEnt_tipopessoa(TipoPessoa.TIPO_PESSOA_FISICA);
			list.clear();
			
				objetoSelecionado = entidadeController.merge(objetoSelecionado);

			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			objetoSelecionado.setEnt_tipopessoa(TipoPessoa.TIPO_PESSOA_FISICA);
			associacaoText = "";
			associacaoTextDescricao = "";
			sucesso();
		}
	}

	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	@RequestMapping({ "**/find_entidade", "**/find_cliente",
			"**/find_fornecedor", "**/find_vendedor" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
		objetoSelecionado.setEnt_tipopessoa(TipoPessoa.TIPO_PESSOA_FISICA);
		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
		associacaoTextDescricao = "";
		associacaoText = "";
	}

	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_id() != null
				&& objetoSelecionado.getEnt_id() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			objetoSelecionado.setEnt_tipopessoa(TipoPessoa.TIPO_PESSOA_FISICA);
			sucesso();
		}
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		if (! getTipoEntidadeTemp().equals(
				TipoCadastro.TIPO_CADASTRO_USUARIO))
		
			return urlFind;
		else
			return null;
	}

//	@RequestMapping("**/findEntidade")
	public void findEntidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findUninqueByPropertyId(
				Entidade.class, codEntidade, "ent_codigo",
				condicaoAndParaPesquisa());
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@RequestMapping("**/findUserDestino")
	public void findUserDestino(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codEntidade") Long codEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findUsuario(codEntidade);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		objetoSelecionado.setEnt_tipopessoa(TipoPessoa.TIPO_PESSOA_FISICA);
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.getValuePadraoJuridica()) {
			items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString()));
		}
		return items;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipo = '" + getTipoEntidadeTemp().name() + "' "
				+ consultarInativos();
	}

	public void updateSenha() throws Exception {
		Entidade entidadeLogada = contextoBean.getEntidadeLogada();
		if (!entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeLogada.getEnt_senha())) {
			addMsg("A senha atual não é válida");
			return;
		} else if (entidadeAtualizaSenhaBean.getSenhaAtual().equals(
				entidadeAtualizaSenhaBean.getNovaSenha())) {
			addMsg("A senha atual não pode ser igual a nova senha.");
			return;
		} else if (!entidadeAtualizaSenhaBean.getNovaSenha().equals(
				entidadeAtualizaSenhaBean.getConfirmaSenha())) {
			addMsg("A nova senha e a confimação não conferem.");
			return;
		} else {
			entidadeLogada.setEnt_senha(entidadeAtualizaSenhaBean
					.getNovaSenha());
			entidadeController.saveOrUpdate(entidadeLogada);
			entidadeLogada = entidadeController.findById(Entidade.class,
					entidadeLogada.getEnt_id());
			if (entidadeLogada.getEnt_senha().equals(
					entidadeAtualizaSenhaBean.getNovaSenha())) {
				sucesso();
			} else {
				addMsg("A nova senha não pode ser atualizada.");
				error();
			}
		}

		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	}

	public void setEntidadeAtualizaSenhaBean(
			EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean) {
		this.entidadeAtualizaSenhaBean = entidadeAtualizaSenhaBean;
	}

	public EntidadeAtualizaSenhaBean getEntidadeAtualizaSenhaBean() {
		return entidadeAtualizaSenhaBean;
	}


	public void setAssociacaoText(String associacaoText) {
		this.associacaoText = associacaoText;
	}

	public String getAssociacaoText() {
		return associacaoText;
	}
	
	public void setAssociacaoTextDescricao(String associacaoTextDescricao) {
		this.associacaoTextDescricao = associacaoTextDescricao;
	}
	
	public String getAssociacaoTextDescricao() {
		return associacaoTextDescricao;
	}
}
