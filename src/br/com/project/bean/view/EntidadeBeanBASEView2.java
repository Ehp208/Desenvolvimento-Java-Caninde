package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.CepController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Cep;
import br.com.project.model.classes.Entidade;
import br.com.project.util.all.Messagens;


public class EntidadeBeanBASEView2 extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	private Entidade objetoSelecionado = new Entidade();
	
	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
	
	private String url = "/cadastro/cad_entidade.jsf?faces-redirect=true";
	private String url_find = "/cadastro/find_entidade.jsf?faces-redirect=true";
	
	@Autowired
	private EntidadeController entidadeController;

	@Autowired
	private ContextoBean contextoBean;


	
	
	
	
	
	
	
	
/*******************     Controle de Tela        ****************/     	
	public String opcao = null;
	
	public Boolean digitarPessoaFisica;
	public Boolean digitarPessoaJuridica;

	public Boolean digitarMotorista;

	public Boolean getDigitarPessoaFisica() {
		return digitarPessoaFisica;
	}

	public void setDigitarPessoaFisica(Boolean digitarPessoaFisica) {
		this.digitarPessoaFisica = digitarPessoaFisica;
	}

	public Boolean getDigitarPessoaJuridica() {
		return digitarPessoaJuridica;
	}

	public void setDigitarPessoaJuridica(Boolean digitarPessoaJuridica) {
		this.digitarPessoaJuridica = digitarPessoaJuridica;
	}
	
	public Boolean getDigitarMotorista() {
		return digitarMotorista;
	}

	public void setDigitarMotorista(Boolean digitarMotorista) {
		this.digitarMotorista = digitarMotorista;
	}

	public Cep cepDigitado = new Cep();
	
	public Cep getCepDigitado() {
		return cepDigitado;
	}

	public void setCepDigitado(Cep cepDigitado) {
		this.cepDigitado = cepDigitado;
	}

	@PostConstruct
	public void init() {
		/**
		 *   Carrega ao iniciar o modulo bean
		 */

		setDigitarPessoaFisica(true);
		setDigitarPessoaJuridica(false);
	
		setDigitarMotorista(false);
	}
/*********************      Final do controle de Tela   **************************/
			
	@Autowired
	private CepBeanView cepBeanView;


	@Autowired
	private CepController cepController;
	
	public void definePessoa(AjaxBehaviorEvent event) {		
		if (objetoSelecionado.getEnt_tipopessoa().toString() == "Fisica") {
			setDigitarPessoaFisica(true);
			setDigitarPessoaJuridica(false);			
		} else if (objetoSelecionado.getEnt_tipopessoa().toString() == "Juridica") {
			setDigitarPessoaFisica(false);
			setDigitarPessoaJuridica(true);								
		} 		
	}

	public void defineCadastro(AjaxBehaviorEvent event) {		
		setDigitarMotorista(false);

		if (objetoSelecionado.getEnt_tipocadastro().toString() == "Motorista") {
			setDigitarMotorista(true); 
		}		
	}
	
//	public List<Entidade> pesquisarEntidadeGenerico(String nome) throws Exception {
//		List listEntidade = entidadeController.pesquisarPorEntidadeGenerico(nome);		
//		return listEntidade;
//	}
		
//	public void pesquisarEntidadePorNome(AjaxBehaviorEvent event) throws Exception {
//		if(entidadeController.existeNome(objetoSelecionado.getEnt_nome())) {
//			Messagens.msgSeverityWarn("<< ATENÇÃO >> - Já existe registro com o mesmo nome...!");
//		}
//	}
	
	
	
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

		if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_USUARIO))
			nomeReport = "report_usuario";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CLIENTE))
			nomeReport = "report_cliente";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_FORNECEDOR))
			nomeReport = "report_fornecedor";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CORRETOR))
			nomeReport = "report_corretor";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_AGENCIADOR))
			nomeReport = "report_agenciador";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_EMBARCADOR))
			nomeReport = "report_embarcador";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_MOTORISTA))
			nomeReport = "report_motorista";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_PARCEIROFRETE))
			nomeReport = "report_parceirofrete";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_PRODUTORRURAL))
			nomeReport = "report_produtorrural";

		super.setNomeRelatorioJasper(nomeReport);
		super.setNomeRelatorioSaida(nomeReport);
		List<?> list = entidadeController.findListByProperty(Entidade.class,
				"ent_tipocadastro", tipoCadastro.name());
		super.setListDataBeanColletionReport(list);

		return super.getArquivoReport();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		opcao = "Novo";
 
		if (! getTipoEntidadeTemp().equals(TipoCadastro.TIPO_CADASTRO_USUARIO))
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
		if (objetoSelecionado.getEnt_tipocadastro().toString().isEmpty()) {
			defineCadastro(null);
		}
		
		if (validarCampoObrigatorio(objetoSelecionado)) {
			// Verificar se o CEP já existe
			cepDigitado.setCep_id(cepBeanView.getObjetoSelecionado().getCep_id());
			cepDigitado.setCep_codigo(cepBeanView.getObjetoSelecionado().getCep_codigo());
			cepDigitado.setRegistroLogradouro(cepBeanView.getObjetoSelecionado().getRegistroLogradouro());
			cepDigitado.setRegistroBairro(cepBeanView.getObjetoSelecionado().getRegistroBairro());
			cepDigitado.setRegistroEstado(cepBeanView.getObjetoSelecionado().getRegistroEstado());
			cepDigitado.setRegistroCidade(cepBeanView.getObjetoSelecionado().getRegistroCidade());
			cepDigitado.setCep_unico(cepBeanView.getObjetoSelecionado().getCep_unico());
			
			if (cepDigitado.getCep_id() == 0) {
				cepBeanView.setObjetoSelecionado(cepController.merge(cepDigitado));
			}

			
			
			
			
			
			
			
			
			objetoSelecionado.setEnt_tipocadastro(getTipoEntidadeTemp());
			list.clear();
							
			objetoSelecionado = entidadeController.merge(objetoSelecionado);		
			list.add(objetoSelecionado);
			objetoSelecionado = new Entidade();
			objetoSelecionado.setEnt_tipocadastro(getTipoEntidadeTemp());
			sucesso();
		}
	}	
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	@RequestMapping({ "**/find_entidade" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
		entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();
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
			objetoSelecionado.setEnt_tipocadastro(getTipoEntidadeTemp());
			sucesso();
		}
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		if (! getTipoEntidadeTemp().equals(TipoCadastro.TIPO_CADASTRO_USUARIO))
			return url_find;
		else
			return null;
	}

	@RequestMapping("**/entidadeFind")
	public void findEntidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idEntidade") Long idEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findUninqueByPropertyId(
				Entidade.class, idEntidade, "ent_id",
				condicaoAndParaPesquisa());
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@RequestMapping("**/findUserDestino")
	public void findUserDestino(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idEntidade") Long idEntidade)
			throws Exception {
		Entidade entidade = entidadeController.findUsuario(idEntidade);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}

	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		objetoSelecionado.setEnt_tipocadastro(getTipoEntidadeTemp());
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.getValuePadraoFisica()) {
			if (! contextoBean.possuiAcesso("EMB", "AGE") ||
			   (contextoBean.possuiAcesso("EMB", "AGE") &&	tipoPessoa.toString() == "Fisica"))
			{
				items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString()));
			}
		}
		return items;
	}
		
	public List<SelectItem> getListTipoCadastro() {
		Boolean continuaGravando = false;
		
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoCadastro tipoCadastro : TipoCadastro.getValuePadraoTipoCadastro()) {
			continuaGravando = true;
			if (tipoCadastro.toString() == "Usuario") {
				continuaGravando = false;
			} else if (contextoBean.possuiAcesso("EMB") && tipoCadastro.toString() != "Motorista") {
			    continuaGravando = false;
			} else if (contextoBean.possuiAcesso("AGE") && tipoCadastro.toString() != "Motorista") {
			    continuaGravando = false;
			} else if (contextoBean.possuiAcesso("COR") && tipoCadastro.toString() != "Produtor Rural") {
			    continuaGravando = false;
			}

			if (continuaGravando) {
				items.add(new SelectItem(tipoCadastro.name(), tipoCadastro.toString()));
			}
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
		return "and entity.ent_tipocadastro = '" + getTipoEntidadeTemp().name() + "' "
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
}
