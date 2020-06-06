package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
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
import br.com.project.bean.geral.EntidadeAtualizaSenhaBean;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.AgenciadorController;
import br.com.project.geral.controller.CepController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.geral.controller.PessoaFisicaController;
import br.com.project.geral.controller.PessoaJuridicaController;
import br.com.project.geral.controller.PessoaMotoristaController;
import br.com.project.model.classes.Agenciador;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cep;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.Logradouro;
import br.com.project.model.classes.PessoaFisica;
import br.com.project.model.classes.PessoaJuridica;
import br.com.project.model.classes.PessoaMotorista;

@Controller
@Scope(value = "session")
@ManagedBean(name = "entidadeBeanView")
public class EntidadeBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	private Entidade objetoSelecionado = new Entidade();

	private EntidadeAtualizaSenhaBean entidadeAtualizaSenhaBean = new EntidadeAtualizaSenhaBean();

	private String url = "/cadastro/cad_entidade.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/find_entidade.jsf?faces-redirect=true";

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

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
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_PRODUTORRURAL))
			nomeReport = "report_produtorrural";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_CORRETOR))
			nomeReport = "report_corretor";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_EMBARCADOR))
			nomeReport = "report_embarcador";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_AGENCIADOR))
			nomeReport = "report_agendiador";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_MOTORISTA))
			nomeReport = "report_motorista";
		else if (tipoCadastro.equals(TipoCadastro.TIPO_CADASTRO_PARCEIROFRETE))
			nomeReport = "report_parceirofrete";
		
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
		
		setBotaoNovoAcionado(false);
		setDigitarPessoaFisica(true);
		setDigitarPessoaJuridica(false);	
		setDigitarMotorista(false);
		
		cepBeanView.setDigitarLogradouro(true);
		cepBeanView.setDigitarBairro(true);
		
		return url;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		setBotaoNovoAcionado(true);
		return url;
	}

	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			
System.out.println("Logradouro: " + logradouroBeanView.getObjetoSelecionado());
System.out.println("Bairro    : " + bairroBeanView.getObjetoSelecionado());
if (objetoSelecionado.getEnt_tipocadastro().toString() == "Motorista") {
	System.out.println("Agenciador: " + agenciadorBeanView.getObjetoSelecionado());
}
System.out.println("============================================================================");		
		
			try {
				

				logradouroDigitado.setLog_id(logradouroBeanView.getObjetoSelecionado().getLog_id());
				logradouroDigitado.setLog_nome(logradouroBeanView.getObjetoSelecionado().getLog_nome());		
				bairroDigitado.setBai_id(bairroBeanView.getObjetoSelecionado().getBai_id());
				bairroDigitado.setBai_nome(bairroBeanView.getObjetoSelecionado().getBai_nome());
				
				cepDigitado.setCep_codigo(cepBeanView.getObjetoSelecionado().getCep_codigo());
				cepDigitado.setRegistroLogradouro(logradouroDigitado);
				cepDigitado.setRegistroBairro(bairroDigitado);
				cepDigitado.setRegistroEstado(cepBeanView.getObjetoSelecionado().getRegistroEstado());
				cepDigitado.setRegistroCidade(cepBeanView.getObjetoSelecionado().getRegistroCidade());
				cepDigitado.setCep_unico(cepBeanView.getObjetoSelecionado().getCep_unico());
	
				cepBeanView.pesquisarCepUnico(cepBeanView.getObjetoSelecionado().getCep_codigo(), 
											cepBeanView.getObjetoSelecionado().getRegistroLogradouro().getLog_nome(), 
											cepBeanView.getObjetoSelecionado().getRegistroBairro().getBai_nome());
	
				if (cepBeanView.listCep.size() == 0) { 
					cepDigitado.setCep_id(null);						
					cepBeanView.setObjetoSelecionado(cepController.merge(cepDigitado));
					
					cepDigitado.setCep_id(cepBeanView.getObjetoSelecionado().getCep_id());				
				} else {
					cepDigitado.setCep_id(cepBeanView.listCep.get(0).getCep_id());
				}
	
				objetoSelecionado.setRegistroCep(cepDigitado);
				list.clear();							
				objetoSelecionado = entidadeController.merge(objetoSelecionado);		
				list.add(objetoSelecionado);
				
				if (objetoSelecionado.getEnt_tipopessoa().toString() == "Fisica") {
					pessoaFisicaDigitada.setPfi_id(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_id());
					pessoaFisicaDigitada.setPfi_cpf(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_cpf());
					pessoaFisicaDigitada.setPfi_rg(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_rg());
					pessoaFisicaDigitada.setPfi_foto(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_foto());
					pessoaFisicaDigitada.setPfi_dataNascimento(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_dataNascimento());
					pessoaFisicaDigitada.setPfi_inativo(pessoaFisicaBeanView.getObjetoSelecionado().getPfi_inativo());
					pessoaFisicaDigitada.setRegistroEntidade(getObjetoSelecionado());
	
					pessoaFisicaBeanView.setObjetoSelecionado(pessoaFisicaController.merge(pessoaFisicaDigitada)); 						
				} else if (objetoSelecionado.getEnt_tipopessoa().toString() == "Juridica") {
					pessoaJuridicaDigitada.setPju_id(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_id());
					pessoaJuridicaDigitada.setPju_cnpj(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_cnpj());
					pessoaJuridicaDigitada.setPju_ie(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_ie());
					pessoaJuridicaDigitada.setPju_foto(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_foto());
					pessoaJuridicaDigitada.setPju_dataFundacao(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_dataFundacao());
					pessoaJuridicaDigitada.setPju_inativo(pessoaJuridicaBeanView.getObjetoSelecionado().getPju_inativo());
					pessoaJuridicaDigitada.setRegistroEntidade(getObjetoSelecionado());
	
					pessoaJuridicaBeanView.setObjetoSelecionado(pessoaJuridicaController.merge(pessoaJuridicaDigitada)); 									
				}
				
				if (objetoSelecionado.getEnt_tipocadastro().toString() == "Motorista") {
					if (! getBotaoNovoAcionado()) {
						pesquisaMotoristaExiste(objetoSelecionado.getEnt_id());
						if (listPessoaMotorista.size() == 0) {
							pessoaMotoristaDigitado.setPmo_id(null);
						} else {
							pessoaMotoristaDigitado.setPmo_id(listPessoaMotorista.get(0).getPmo_id());						
						}
					}	
					
					agenciadorDigitado.setAge_id(agenciadorBeanView.getObjetoSelecionado().getAge_id());
					agenciadorDigitado.setAge_nome(agenciadorBeanView.getObjetoSelecionado().getAge_nome());
					agenciadorDigitado.setAge_contato(agenciadorBeanView.getObjetoSelecionado().getAge_contato());
					agenciadorDigitado.setAge_fone(agenciadorBeanView.getObjetoSelecionado().getAge_fone());
					agenciadorDigitado.setAge_celular(agenciadorBeanView.getObjetoSelecionado().getAge_celular());
					agenciadorDigitado.setAge_email(agenciadorBeanView.getObjetoSelecionado().getAge_email());
					agenciadorDigitado.setAge_inativo(agenciadorBeanView.getObjetoSelecionado().getAge_inativo());
					
					
					pessoaMotoristaDigitado.setRegistroAgenciador(agenciadorDigitado);	
					pessoaMotoristaDigitado.setRegistroEntidade(getObjetoSelecionado());
						
					pessoaMotoristaBeanView.setObjetoSelecionado(pessoaMotoristaController.merge(pessoaMotoristaDigitado)); 									
				}
										
				objetoSelecionado = new Entidade();
				sucesso();
				
				redirecionarFindEntidade();
				
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	@RequestMapping({ "**/find_entidade"})
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
		pessoaFisicaBeanView.setObjetoSelecionado(new PessoaFisica());
		pessoaJuridicaBeanView.setObjetoSelecionado(new PessoaJuridica());
		cepBeanView.setObjetoSelecionado(new Cep());
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
		return urlFind;
	}

	@RequestMapping("**/findEntidade")
	public void findEntidade(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codId") Long codId)
			throws Exception {
		Entidade entidade = entidadeController.findUninqueByPropertyId(
				Entidade.class, codId, "ent_id",
				condicaoAndParaPesquisa());
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}
	}

	@RequestMapping("**/findUserDestino")
	public void findUserDestino(HttpServletResponse httpServletResponse,
			@RequestParam(value = "codId") Long codId)
			throws Exception {
		Entidade entidade = entidadeController.findUsuario(codId);
		if (entidade != null) {
			httpServletResponse.getWriter()
					.write(entidade.getJson().toString());
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
//		objetoSelecionado.setEnt_tipocadastro(getTipoEntidadeTemp());
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(),
				super.getSqlLazyQuery());
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.getValuePadraoFisica()) {
			if (! contextoBean.possuiAcesso("EMB", "AGE") ||
			   (contextoBean.possuiAcesso("EMB", "AGE") &&	tipoPessoa.toString() == "Fisica"))
			{ items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString())); }
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
		String sqlEntidade = null;
		if (contextoBean.possuiAcesso("EMB", "AGE")) {
			sqlEntidade = "and entity.ent_tipocadastro = 'TIPO_CADASTRO_MOTORISTA";
		} else if (contextoBean.possuiAcesso("COR")) {
			sqlEntidade = "and entity.ent_tipocadastro = 'TIPO_CADASTRO_PRODUTORRURAL";
		} else if (contextoBean.possuiAcesso("FIN", "FRE")) {
			sqlEntidade = "and entity.ent_tipocadastro = 'NÂO_PODE_PESQUISAR";
		} else {
			sqlEntidade = "and entity.ent_tipocadastro <> 'TIPO_CADASTRO_USUARIO";
		}
		
		return  sqlEntidade + "' " + consultarInativos();
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
	
/********************      FIM DAS ROTINAS STANDBY        ******************/	
	
	
/**
 * 			MUDANÇAS NA EVOLUÇÃO DO SISTEMA
 * 		
 * 			AUTOR: Eduardo H. Paula
 */

	
/**
 * 			Relacionamento com outras entidades	
*/
	
	@Autowired
	private CepBeanView cepBeanView;
	
	@Autowired
	private LogradouroBeanView logradouroBeanView;

	@Autowired
	private BairroBeanView bairroBeanView;

	@Autowired
	private PessoaFisicaBeanView pessoaFisicaBeanView;

	@Autowired
	private PessoaJuridicaBeanView pessoaJuridicaBeanView;

	@Autowired
	private PessoaMotoristaBeanView pessoaMotoristaBeanView;
	
	@Autowired
	private AgenciadorBeanView agenciadorBeanView;

	@Autowired
	private CepController cepController;
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;

	@Autowired
	private PessoaJuridicaController pessoaJuridicaController;
	
	@Autowired
	private PessoaMotoristaController pessoaMotoristaController;
	
	@Autowired
	private AgenciadorController agenciadorController;

/**
 * 			Criação de Variáveis	
 */
	
	public Boolean digitarPessoaFisica;
	public Boolean digitarPessoaJuridica;
	public Boolean digitarMotorista;
	public Boolean botaoNovoAcionado = false;
	
	public Cep cepDigitado = new Cep();
	public Logradouro logradouroDigitado = new Logradouro();
	public Bairro bairroDigitado = new Bairro();
	public PessoaFisica pessoaFisicaDigitada = new PessoaFisica();
	public PessoaJuridica pessoaJuridicaDigitada = new PessoaJuridica();
	public PessoaMotorista pessoaMotoristaDigitado = new PessoaMotorista();
	public Agenciador agenciadorDigitado = new Agenciador();
	
	private TipoCadastro tipoCadastroAux = TipoCadastro.TIPO_CADASTRO_PRODUTORRURAL;
	public List<PessoaMotorista> listPessoaMotorista = new ArrayList<PessoaMotorista>();
		
/**
 * 			Gets e Sets	
 * 
*/
	
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

	public Cep getCepDigitado() {
		return cepDigitado;
	}

	public void setCepDigitado(Cep cepDigitado) {
		this.cepDigitado = cepDigitado;
	}
	
	public PessoaFisica getPessoaFisicaDigitada() {
		return pessoaFisicaDigitada;
	}

	public void setPessoaFisicaDigitada(PessoaFisica pessoaFisicaDigitada) {
		this.pessoaFisicaDigitada = pessoaFisicaDigitada;
	}

	public PessoaJuridica getPessoaJuridicaDigitada() {
		return pessoaJuridicaDigitada;
	}

	public void setPessoaJuridicaDigitada(PessoaJuridica pessoaJuridicaDigitada) {
		this.pessoaJuridicaDigitada = pessoaJuridicaDigitada;
	}

	public PessoaMotorista getPessoaMotoristaDigitado() {
		return pessoaMotoristaDigitado;
	}

	public void setPessoaMotoristaDigitado(PessoaMotorista pessoaMotoristaDigitado) {
		this.pessoaMotoristaDigitado = pessoaMotoristaDigitado;
	}

	public Agenciador getAgenciadorDigitado() {
		return agenciadorDigitado;
	}

	public void setAgenciadorDigitado(Agenciador agenciadorDigitado) {
		this.agenciadorDigitado = agenciadorDigitado;
	}

	public Boolean getBotaoNovoAcionado() {
		return botaoNovoAcionado;
	}

	public void setBotaoNovoAcionado(Boolean botaoNovoAcionado) {
		this.botaoNovoAcionado = botaoNovoAcionado;
	}

	
/**
 * 			Funções Ajax
 */
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
	
	public void existeNomeIdentico(AjaxBehaviorEvent event) throws Exception {
		if (entidadeController.findNomeUnico(objetoSelecionado.getEnt_nome())) {
			addMsg("ATENÇÃO -> Já existe nome idêntico cadastrado....!");
			return;
		} 
	}
	
	/** Todo Motorista só pode ser PESSOA FISICA  */
	public List<PessoaMotorista> pesquisaMotoristaExiste(Long idEntidadeMotorista) throws Exception {
		listPessoaMotorista = pessoaMotoristaController.pesquisarMotoristaExiste(idEntidadeMotorista);		
		return listPessoaMotorista;
	}
}
