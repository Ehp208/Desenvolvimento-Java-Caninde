package br.com.project.bean.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.acessos.Permissao;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;
import br.com.project.geral.controller.CepController;
import br.com.project.geral.controller.EntidadeController;
import br.com.project.model.classes.Cep;
import br.com.project.model.classes.Entidade;

@Controller
@Scope(value = "session")
@ManagedBean(name = "usuarioBeanView")
public class UsuarioBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	private CarregamentoLazyListForObject<Entidade> list = new CarregamentoLazyListForObject<Entidade>();
	
	private Entidade objetoSelecionado = new Entidade();
	
	private String url = "/administrativo/cad_usuario.jsf?faces-redirect=true";
	private String urlFind = "/administrativo/find_usuario.jsf?faces-redirect=true";
	
	private String urlPermissao = "/administrativo/cad_permissao.jsf?faces-redirect=true";
	
	private List<Permissao> listSelecionado = new ArrayList<Permissao>();
	
	private DualListModel<Permissao> listMenu = new DualListModel<Permissao>();

	@Autowired
	private ContextoBean contextoBean;

	@Autowired
	private EntidadeController entidadeController;

	@Autowired
	private CepController cepController;
	
	private static final Boolean DEFAULT_CEP = true;
	private static final String CEP_AUX = "00001-000";
		
	Cep cepAux = new Cep();
	
	private TipoPessoa tipoPessoaAux = TipoPessoa.TIPO_PESSOA_FISICA; 
	
	private TipoCadastro tipoCadastroAux = TipoCadastro.TIPO_CADASTRO_USUARIO;
	
	public List<Cep> listCep = new ArrayList<Cep>();
	
	public List<Cep> pesquisarCep(String codigo, Boolean unico) throws Exception {
		listCep = cepController.pesquisarPorCodigo(codigo, unico); 
		return listCep;
	}
	
	@PostConstruct
	public void init() throws Exception {
/**
 *   Carrega ao iniciar o modulo bean
*/

		pesquisarCep(CEP_AUX, DEFAULT_CEP);
				
		if (! listCep.isEmpty()) {
			cepAux.setCep_id(listCep.get(0).getCep_id());
			cepAux.setCep_codigo(listCep.get(0).getCep_codigo());
			cepAux.setRegistroLogradouro(listCep.get(0).getRegistroLogradouro());
			cepAux.setRegistroBairro(listCep.get(0).getRegistroBairro());
			cepAux.setRegistroCidade(listCep.get(0).getRegistroCidade());
			cepAux.setRegistroEstado(listCep.get(0).getRegistroEstado());
			cepAux.setCep_unico(listCep.get(0).getCep_unico());
		} else {
			mostrarMsg("Erro tabela CEP");
		}
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_usuario");
		super.setNomeRelatorioSaida("report_usuario");
		List<?> list = entidadeController.findListByProperty(Entidade.class, "ent_tipocadastro", "TIPO_CADASTRO_USUARIO");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	public String getEntidadeLogadoSecurity() {
		return contextoBean.getAuthentication().getName();
	}
	
	public DualListModel<Permissao> getListMenu() {
		permissao();
		setListMenu(new DualListModel<Permissao>(Permissao.getListPermissao(),
		listSelecionado));
				
		for (Permissao acesso :  listSelecionado){
			if (listMenu.getSource().contains(acesso)){
				listMenu.getSource().remove(acesso); 
			}
		}
				
		return listMenu;
	}

	public List<Permissao> getListSelecionado() {
		return listSelecionado;
	}
 
	public void setListSelecionado(List<Permissao> listSelecionado) {
		this.listSelecionado = listSelecionado;
	}

	public void setListMenu(DualListModel<Permissao> listMenu) {
		this.listMenu = listMenu;
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	public Date getUltimoAcesso() throws Exception {
		return contextoBean.getEntidadeLogada().getEnt_ultimoacesso();
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	@Override
	@RequestMapping(value = { "**/find_usuario" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Entidade();
	}

	public CarregamentoLazyListForObject<Entidade> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Entidade> list) {
		this.list = list;
	}

	@Override
	protected Class<?> getClassImplement() {
		return Entidade.class;
	}
	
	public String permissao(){
		listSelecionado.clear();
		Iterator<Permissao> iterator = objetoSelecionado.getAcessosPermissao().iterator();
		while (iterator.hasNext()) {
			listSelecionado.add(iterator.next());
		} 
		
		Collections.sort(listSelecionado, new Comparator<Permissao>() { 

		@Override
		public int compare(Permissao o1, Permissao o2) {
			return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
		}
		});
	return urlPermissao;
	}
	
	public String savePermissoes() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado.getAcessos().clear();
			listSelecionado.clear();
				
			List<Permissao> permissoesConverter = getConvertPermissoes();
				
			for (Permissao permissao : permissoesConverter) {
				listSelecionado.add(permissao); 
				objetoSelecionado.getAcessos().add(permissao.name());
			}
			objetoSelecionado = entidadeController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			sucesso(); 
		}
		return urlPermissao;
	}

	private List<Permissao> getConvertPermissoes() {
		List<Permissao> retorno = new ArrayList<Permissao>();
		Object[] acessos = (Object[]) listMenu.getTarget().toArray();
		
		for (Object object : acessos) {
			for (Permissao ace : Permissao.values()){
				if (object.toString().equals(ace.name())){
					retorno.add(ace);
				}
			}
		}
		return retorno;
	}

	@Override
	public void saveNotReturn() throws Exception {
		objetoSelecionado.setEnt_tipocadastro(tipoCadastroAux);		
		objetoSelecionado.setEnt_tipopessoa(tipoPessoaAux);		
		objetoSelecionado.setEnt_numero(1);
		objetoSelecionado.setRegistroCep(cepAux);

		if (validarCampoObrigatorio(objetoSelecionado)) {
			if (entidadeController.existeEntidade(objetoSelecionado.getEnt_login())){
				addMsg("Já existe usuário com o mesmo login.");
				return;
			}
				
			gravaUsuario();
		}
	}

	private void gravaUsuario() throws Exception {
		list.clear();
		List<Permissao> permissoesConverter = getConvertPermissoes(); 
		for (Permissao permissao : permissoesConverter) { 
			objetoSelecionado.getAcessos().add(permissao.name());
		}
		if (!objetoSelecionado.getAcessos().contains("USER")){
			objetoSelecionado.getAcessos().add("USER");
		}
		
		objetoSelecionado = entidadeController.merge(objetoSelecionado);
		list.add(objetoSelecionado);
		objetoSelecionado = new Entidade();
		sucesso();
	}
	
	@Override
	public void saveEdit() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			gravaUsuario();
		}
	}

	public List<SelectItem> getListTipoPessoa() {
		List<SelectItem> items = new ArrayList<SelectItem>();
		for (TipoPessoa tipoPessoa : TipoPessoa.values()) {
			items.add(new SelectItem(tipoPessoa.name(), tipoPessoa.toString()));
		}
		return items;
	}


	
	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getEnt_id() != null
				&& objetoSelecionado.getEnt_id() > 0) {
			entidadeController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Entidade();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new Entidade();
		list.clear();
		list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	protected InterfaceCrud<Entidade> getController() {
		return entidadeController;
	}

	public Entidade getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Entidade objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "and entity.ent_tipocadastro = '" + TipoCadastro.TIPO_CADASTRO_USUARIO.name()+ "' " + consultarInativos();
	}
	
}
