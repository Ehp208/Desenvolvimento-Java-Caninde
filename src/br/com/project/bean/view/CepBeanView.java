package br.com.project.bean.view;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.bean.geral.BeanManagedViewAbstract;
import br.com.project.carregamento.lazy.CarregamentoLazyListForObject;
import br.com.project.geral.controller.BairroController;
import br.com.project.geral.controller.CepController;
import br.com.project.geral.controller.CidadeController;
import br.com.project.geral.controller.EstadoController;
import br.com.project.geral.controller.LogradouroController;
import br.com.project.geral.controller.PaisController;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cep;
import br.com.project.model.classes.CepJson;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Estado;
import br.com.project.model.classes.Logradouro;
import br.com.project.model.classes.Pais;
import br.com.project.util.all.Messagens;

@Controller
@ManagedBean(name = "cepBeanView")
@Scope("session")
public class CepBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private static Boolean UNICO_CEP = false;
	
	private CarregamentoLazyListForObject<Cep> list = new CarregamentoLazyListForObject<Cep>();
	
	private Cep objetoSelecionado = new Cep();
	
	private String url = "/cadastro/localidade/cad_cep.jsf?faces-redirect=true";
	private String urlFind = "/cadastro/localidade/find_cep.jsf?faces-redirect=true";

	@Resource
	private CepController cepController;
		
	/**
	 *   Carrego a lista de ceps
	 */
	public List<SelectItem> getCeps() throws Exception {
		return cepController.getListCep();
	}
			
	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_cep");
		super.setNomeRelatorioSaida("report_cep");
		List<?> list = cepController.finListOrderByProperty(Cep.class, "cep_id");
		super.setListDataBeanColletionReport(list); 
		return super.getArquivoReport();
	}

	@Override
	public void saveNotReturn() throws Exception {
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();
			objetoSelecionado = cepController.merge(objetoSelecionado);
			list.add(objetoSelecionado);
			objetoSelecionado = new Cep();
			sucesso();
		}
	}
	
	@Override
	public void saveEdit() throws Exception {
		saveNotReturn();
	}

	@Override
	public void excluir() throws Exception {
		if (objetoSelecionado.getCep_id() != null
				&& objetoSelecionado.getCep_id() > 0) {
			cepController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new Cep();
			sucesso();
		}
	}

	@Override
	public String editar() throws Exception {
		valorPesquisa = "";
		list.clear();
		return url;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		setarVariaveisNulas();
		return urlFind;
	}

	@Override
	public void consultaEntidade() throws Exception {
			objetoSelecionado = new Cep();
			list.clear();
			list.setTotalRegistroConsulta(super.totalRegistroConsulta(), super.getSqlLazyQuery());
	}

	@Override
	@RequestMapping({ "**/find_cep" })
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new Cep();
	}

	public void setCepController(
			CepController cepController) {
		this.cepController = cepController;
	}

	public CepController getCepController() {
		return cepController;
	}

	@Override
	protected Class<Cep> getClassImplement() {
		return Cep.class;
	}

	@Override
	protected InterfaceCrud<Cep> getController() {
		return cepController;
	}

	public CarregamentoLazyListForObject<Cep> getList() {
		return list;
	}

	public void setList(CarregamentoLazyListForObject<Cep> list) {
		this.list = list;
	}

	public Cep getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public void setObjetoSelecionado(Cep objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return consultarInativosBoolean() ? "" : " and entity.cep_id.cep_inativo is false ";
	}

/**
 *			Rotina criadas para customização do sistema. 	
*/
	@Autowired
	private LogradouroBeanView logradouroBeanView;

	@Autowired
	private BairroBeanView bairroBeanView;
	
	@Autowired
	private CidadeBeanView cidadeBeanView;

	@Autowired
	private EstadoBeanView estadoBeanView;

	@Autowired
	private PaisBeanView paisBeanView;

	@Autowired
	private LogradouroController logradouroController;
	
	@Autowired
	private BairroController bairroController;
	
	@Autowired
	private EstadoController estadoController;
	
	@Autowired
	private CidadeController cidadeController;
		
	@Autowired
	private PaisController paisController;
	
	public List<Logradouro> listLogradouro = new ArrayList<Logradouro>();
	public List<Bairro> listBairro = new ArrayList<Bairro>();
	public List<Cidade> listCidade = new ArrayList<Cidade>();
	public List<Estado> listEstado = new ArrayList<Estado>();
	public List<Pais> listPais = new ArrayList<Pais>();
	public List<Cep> listCep = new ArrayList<Cep>();

	public String opcao = null;
	
	public Boolean digitarLogradouro;
	public Boolean digitarBairro;
	public Boolean digitarEstado;
	public Boolean digitarCidade;
	
	public Boolean getDigitarLogradouro() {
		return digitarLogradouro;
	}

	public void setDigitarLogradouro(Boolean digitarLogradouro) {
		this.digitarLogradouro = digitarLogradouro;
	}

	public Boolean getDigitarBairro() {
		return digitarBairro;
	}

	public void setDigitarBairro(Boolean digitarBairro) {
		this.digitarBairro = digitarBairro;
	}

	public Boolean getDigitarEstado() {
		return digitarEstado;
	}

	public void setDigitarEstado(Boolean digitarEstado) {
		this.digitarEstado = digitarEstado;
	}

	public Boolean getDigitarCidade() {
		return digitarCidade;
	}

	public void setDigitarCidade(Boolean digitarCidade) {
		this.digitarCidade = digitarCidade;
	}

	@PostConstruct
	public void init() {
		/**
		 *   Carrega ao iniciar o modulo bean
		 */
		objetoSelecionado.setCep_codigo("00001-000");
		objetoSelecionado.setCep_unico(UNICO_CEP);		
		
		setDigitarLogradouro(true);
		setDigitarBairro(true);
		setDigitarEstado(true);
		setDigitarCidade(true);		
	}
		
	public List<Logradouro> pesquisarLogradouroGenerico(String nome) throws Exception {
		List listLogradouroGenerico = logradouroController.pesquisarPorNomeGenerico(nome);	
		return listLogradouroGenerico;
	}

	public List<Bairro> pesquisarBairroGenerico(String nome) throws Exception {
		List listBairroGenerico = bairroController.pesquisarPorNomeGenerico(nome);	
		return listBairroGenerico;
	}
	
	public List<Logradouro> pesquisarLogradouro(String nome) throws Exception {
		listLogradouro = logradouroController.pesquisarPorNome(nome); 
		return listLogradouro;
	}

	public List<Bairro> pesquisarBairro(String nome) throws Exception {
		listBairro = bairroController.pesquisarPorNome(nome); 
		return listBairro;
	}
	
	public List<Cidade> pesquisarCidade(String nome) throws Exception {
		listCidade = cidadeController.pesquisarPorNome(nome); 
		return listCidade;
	}
			
	public List<Estado> pesquisarEstadoUf(String uf) throws Exception {
		listEstado = estadoController.pesquisarPorEstado(uf); 
		return listEstado;
	}

	public List<Pais> pesquisarPais(String nome) throws Exception {
		listPais = paisController.pesquisarPorNome(nome); 
		return listPais;
	}
	
	public List<Cep> pesquisarCep(String codigo, Boolean unico) throws Exception {
		listCep = cepController.pesquisarPorCodigo(codigo, unico); 
		return listCep;
	}
	
	public List<Cep> pesquisarCepUnico(String codigoCep, String logradouroNome, String bairroNome) throws Exception {
		listCep = cepController.pesquisarPorCepUnico(codigoCep, logradouroNome, bairroNome); 
		return listCep;
	}
	

/**
 * 		Pesquisa na WebService se o CEP é válido.
 * @throws Exception 
 */	
	public void pesquisaCep(AjaxBehaviorEvent event) {		
		String cepAux = objetoSelecionado.getCep_codigo().replaceAll("[^0-9]", "");
				
		try {						
			Logradouro logradouroAux = new Logradouro();
			Bairro bairroAux = new Bairro();
			Pais paisAux = new Pais();
			Estado estadoAux = new Estado();					
			Cidade cidadeAux = new Cidade();

			listCep = cepController.pesquisarPorCodigo(objetoSelecionado.getCep_codigo(), true); 
			
			if (! listCep.isEmpty() && opcao == "Novo") {	
				Messagens.msgSeverityInfor("CEP Já cadastrado!...");
				return;	
			} else if (! listCep.isEmpty()) {	
				objetoSelecionado.setCep_id(listCep.get(0).getCep_id());
				objetoSelecionado.setCep_codigo(listCep.get(0).getCep_codigo());
				objetoSelecionado.setRegistroLogradouro(listCep.get(0).getRegistroLogradouro());
				objetoSelecionado.setRegistroBairro(listCep.get(0).getRegistroBairro());
				objetoSelecionado.setRegistroEstado(listCep.get(0).getRegistroEstado());
				objetoSelecionado.setRegistroCidade(listCep.get(0).getRegistroCidade());				
				objetoSelecionado.setCep_unico(listCep.get(0).getCep_unico());	
				
				if (objetoSelecionado.getCep_unico()) {
					UNICO_CEP = true;
				} else {
					UNICO_CEP = false;
				}
				
				logradouroBeanView.setObjetoSelecionado(listCep.get(0).getRegistroLogradouro());
				logradouroAux.setLog_id(logradouroBeanView.getObjetoSelecionado().getLog_id());
				logradouroAux.setLog_nome(logradouroBeanView.getObjetoSelecionado().getLog_nome());				
				bairroBeanView.setObjetoSelecionado(listCep.get(0).getRegistroBairro());
				bairroAux.setBai_id(bairroBeanView.getObjetoSelecionado().getBai_id());
				bairroAux.setBai_nome(bairroBeanView.getObjetoSelecionado().getBai_nome());				
				estadoBeanView.setObjetoSelecionado(listCep.get(0).getRegistroEstado());
				estadoAux.setEst_id(estadoBeanView.getObjetoSelecionado().getEst_id());
				estadoAux.setEst_nome(estadoBeanView.getObjetoSelecionado().getEst_nome());
				estadoAux.setEst_uf(estadoBeanView.getObjetoSelecionado().getEst_uf());
				estadoAux.setRegistroPais(estadoBeanView.getObjetoSelecionado().getRegistroPais());				
				cidadeBeanView.setObjetoSelecionado(listCep.get(0).getRegistroCidade());
				cidadeAux.setCid_id(cidadeBeanView.getObjetoSelecionado().getCid_id());
				cidadeAux.setCid_nome(cidadeBeanView.getObjetoSelecionado().getCid_nome());
			} else {			
				URL urlCep = new URL("https://viacep.com.br/ws/"+cepAux+"/json/");			
				URLConnection connection = urlCep.openConnection();
				InputStream is = connection.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				
				String cepString = "";
				StringBuilder jsonCep = new StringBuilder();

				while ((cepString = br.readLine()) != null) {						
					jsonCep.append(cepString);				
				}
				
				CepJson cepJson = new Gson().fromJson(jsonCep.toString(), CepJson.class);
				
				if (jsonCep.toString().isEmpty()) {
					Messagens.msgSeverityInfor("CEP não consta da base dos Correios...");
					return;
				} else {
					if(cepJson.getLogradouro().isEmpty()) {
						logradouroAux.setLog_nome("CEP UNICO");
						setDigitarLogradouro(false);
						UNICO_CEP = false;
					} else {			
						logradouroAux.setLog_nome(cepJson.getLogradouro());
						setDigitarLogradouro(true);
						UNICO_CEP = true;
					}
					
					pesquisarLogradouro(logradouroAux.getLog_nome());						
					if (! listLogradouro.isEmpty()) {
						logradouroAux.setLog_id(listLogradouro.get(0).getLog_id());
					} else {
						/**
						 *   Gravar o Logradouro
						*/				
						Logradouro log = new Logradouro();
						log = logradouroController.merge(logradouroAux);	
						logradouroAux = log;
						logradouroBeanView.setObjetoSelecionado(logradouroAux);
					}
									
					if(cepJson.getBairro().isEmpty()) {
						bairroAux.setBai_nome("CEP UNICO");
						setDigitarBairro(false);
					} else {			
						bairroAux.setBai_nome(cepJson.getBairro());
						setDigitarBairro(true);
					}
					
					pesquisarBairro(bairroAux.getBai_nome());					
					if (! listBairro.isEmpty()) {
						bairroAux.setBai_id(listBairro.get(0).getBai_id());
					} else {
						/**
						 *   Gravar o Bairro
						 */				
						Bairro bai = new Bairro();
						bai = bairroController.merge(bairroAux);
						bairroAux = bai;
						bairroBeanView.setObjetoSelecionado(bairroAux);
					}
					
					/** Verifico se o Pais já esta cadastrado na tabela PAIS */
					paisAux.setPai_nome("BRASIL");
					cepJson.setPais("BRASIL");
		
					pesquisarPais(cepJson.getPais()).contains(cepJson.getPais());					
					if (! listPais.isEmpty()) {
						paisAux.setPai_id(listPais.get(0).getPai_id());
						paisAux.setPai_sigla(listPais.get(0).getPai_sigla());
					} else {
						/**
						 *   Gravar o Paos
						 */				
						Pais pai = new Pais(); 	
						pai = paisController.merge(paisAux);
						paisAux = pai;
						paisBeanView.setObjetoSelecionado(paisAux);
					}
										
					/** Verifico se o Estado já esta cadastrado na tabela ESTADO */
					estadoAux.setEst_uf(cepJson.getUf());
					estadoAux.setRegistroPais(paisAux);
					
					pesquisarEstadoUf(cepJson.getUf()).contains(cepJson.getUf());				
					if (! listEstado.isEmpty()) {
						estadoAux.setEst_id(listEstado.get(0).getEst_id());
						estadoAux.setEst_nome(listEstado.get(0).getEst_nome());
					} else {
						Estado est = new Estado();
						estadoAux.setEst_nome("Estado Inválido");
						est = estadoController.merge(estadoAux);					
						estadoAux = est;
						estadoBeanView.setObjetoSelecionado(estadoAux);
					} 
									
					/** Verifico se o Cidade já esta cadastrado na tabela CIDADE */
					if(cepJson.getLocalidade().isEmpty()) {
						cidadeAux.setCid_nome("CEP UNICO");
						setDigitarCidade(false);		
					} else {
						cidadeAux.setCid_nome(cepJson.getLocalidade());
						setDigitarCidade(true);					
					}
	
					pesquisarCidade(cepJson.getLocalidade()).contains(cepJson.getLocalidade());								
					if (! listCidade.isEmpty()) {
						cidadeAux.setCid_id(listCidade.get(0).getCid_id());
					} else {
						/**
						 *   Gravar a Cidade
						 */				
						Cidade cid = new Cidade();
						cid = cidadeController.merge(cidadeAux);
						cidadeAux = cid;
						cidadeBeanView.setObjetoSelecionado(cidadeAux);
					}
					
					/** Atualizo os ComboBox */
					logradouroController.getListLogradouro();
					bairroController.getListBairro();					
				}
			}	
			
System.out.println("Situação Logradouro: " + getDigitarLogradouro());
System.out.println("Situação Bairro    : " + getDigitarBairro());
System.out.println("================================================");


			
			setDigitarLogradouro(UNICO_CEP);
			setDigitarBairro(UNICO_CEP);					
			
System.out.println("Situação Logradouro: " + getDigitarLogradouro());
System.out.println("Situação Bairro    : " + getDigitarBairro());
System.out.println("================================================");

			
			
			/** Carrego os campos Logradouro e Bairro */
			logradouroBeanView.setObjetoSelecionado(logradouroAux);
			bairroBeanView.setObjetoSelecionado(bairroAux);				

			/** Monto o objeto CEP para GRAVAÇÃO */
			objetoSelecionado.setRegistroLogradouro(logradouroAux);
			objetoSelecionado.setRegistroBairro(bairroAux);
			objetoSelecionado.setRegistroEstado(estadoAux);
			objetoSelecionado.setRegistroCidade(cidadeAux);				
			objetoSelecionado.setCep_unico(UNICO_CEP);									
		} catch (Exception e) {
			e.printStackTrace();
			mostrarMsg("Erro ao consultar o CEP");
		}
	}
}