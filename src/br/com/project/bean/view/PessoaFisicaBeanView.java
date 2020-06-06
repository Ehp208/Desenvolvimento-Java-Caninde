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
import br.com.project.geral.controller.PessoaFisicaController;
import br.com.project.model.classes.PessoaFisica;
import br.com.project.util.all.Messagens;

@Controller
@Scope("session")
@ManagedBean(name = "pessoaFisicaBeanView")
public class PessoaFisicaBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;

	private CarregamentoLazyListForObject<PessoaFisica> list = new CarregamentoLazyListForObject<PessoaFisica>();

	private PessoaFisica objetoSelecionado = new PessoaFisica();

	private String url = "/administrativo/cad_pessoaFisica.jsf?faces-redirect=true";
	private String urlFind = "/administrativo/find_pessoaFisica.jsf?faces-redirect=true";
	
	@Autowired
	private PessoaFisicaController pessoaFisicaController;

	public void setPessoaFisicaController(PessoaFisicaController pessoaFisicaController) {
		this.pessoaFisicaController = pessoaFisicaController;
	}

	public PessoaFisicaController getPessoaFisicaController() {
		return pessoaFisicaController;
	}
	
	public Boolean digitarDadosPessoaFisica;

	public Boolean getDigitarDadosPessoaFisica() {
		return digitarDadosPessoaFisica;
	}

	public void setDigitarDadosPessoaFisica(Boolean digitarDadosPessoaFisica) {
		this.digitarDadosPessoaFisica = digitarDadosPessoaFisica;
	}

	@PostConstruct
	public void init() {
		setDigitarDadosPessoaFisica(false);
	}

/**
	public boolean isCPF(String cpfDigitado) {		
		cpfDigitado = cpfDigitado.replaceAll("[^0-9]", "");
		
		// considera-se erro cpfDigitado's formados por uma sequencia de numeros iguais
		if (cpfDigitado.equals("00000000000") || cpfDigitado.equals("11111111111") || cpfDigitado.equals("22222222222") || cpfDigitado.equals("33333333333") || cpfDigitado.equals("44444444444") || cpfDigitado.equals("55555555555") || cpfDigitado.equals("66666666666") || cpfDigitado.equals("77777777777") || cpfDigitado.equals("88888888888") || cpfDigitado.equals("99999999999") || (cpfDigitado.length() != 11))
			return (false);

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do cpfDigitado em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0        
				// (48 eh a posicao de '0' na tabela ASCII)        
				num = (int) (cpfDigitado.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpfDigitado.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else
				dig11 = (char) (r + 48);

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == cpfDigitado.charAt(9)) && (dig11 == cpfDigitado.charAt(10)))
				return (true);
			else
				return (false);
		} catch (Exception erro) {
			return (false);
		}
	}
*/

	public Void pesquisaCpf(AjaxBehaviorEvent event) throws Exception {		
		String cpfPesquisa = objetoSelecionado.getPfi_cpf();
		setarVariaveisNulas();
	
		try {			
			List<PessoaFisica> listPesFisAux = pessoaFisicaController.findListByProperty(PessoaFisica.class, "pfi_cpf", cpfPesquisa);
			
			if (listPesFisAux.size() == 1) {			
				objetoSelecionado.setPfi_id(listPesFisAux.get(0).getPfi_id());
				objetoSelecionado.setPfi_rg(listPesFisAux.get(0).getPfi_rg());
				objetoSelecionado.setPfi_dataNascimento(listPesFisAux.get(0).getPfi_dataNascimento());
				objetoSelecionado.setPfi_foto(listPesFisAux.get(0).getPfi_foto());
				objetoSelecionado.setPfi_inativo(listPesFisAux.get(0).getPfi_inativo());
				setDigitarDadosPessoaFisica(true);
			} else {
				setDigitarDadosPessoaFisica(false);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@Override
	public StreamedContent getArquivoReport() throws Exception {
		super.setNomeRelatorioJasper("report_pessoaFisica");
		super.setNomeRelatorioSaida("report_pessoaFisica");
		List<?> list = pessoaFisicaController.finListOrderByProperty(PessoaFisica.class, "pessoaFisica.pfi_nome");
		super.setListDataBeanColletionReport(list);
		return super.getArquivoReport();
	}

	@Override
	protected Class<?> getClassImplement() {
		return PessoaFisica.class;
	}

	public void setObjetoSelecionado(PessoaFisica objetoSelecionado) {
		this.objetoSelecionado = objetoSelecionado;
	}

	@Override
	public String novo() throws Exception {
		setarVariaveisNulas();
		return url;
	}

	public PessoaFisica getObjetoSelecionado() {
		return objetoSelecionado;
	}

	public CarregamentoLazyListForObject<PessoaFisica> getList() {
		return list;
	}

	@Override
	@RequestMapping(value = { "**/find_pessoaFisica" }, method = RequestMethod.POST)
	public void setarVariaveisNulas() throws Exception {
		valorPesquisa = "";
		list.clear();
		objetoSelecionado = new PessoaFisica();
	}

	@Override
	public void saveNotReturn() throws Exception {
		Boolean continuaGravando = true;
		if (validarCampoObrigatorio(objetoSelecionado)) {
			list.clear();

			if (pessoaFisicaController.existeCpf(objetoSelecionado.getPfi_cpf(), objetoSelecionado.getPfi_id())) {
				Messagens.msgSeverityInfor("CPF Já cadastrado!...");
				continuaGravando = false;
			} else if (pessoaFisicaController.existeRg(objetoSelecionado.getPfi_rg(), objetoSelecionado.getPfi_id())) {
				Messagens.msgSeverityInfor("RG Já cadastrado!...");
				continuaGravando = false;
			} 
				
			if (continuaGravando) {
				objetoSelecionado = pessoaFisicaController.merge(objetoSelecionado);
				list.add(objetoSelecionado);
				objetoSelecionado = new PessoaFisica();
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
		if (objetoSelecionado.getPfi_id() != null && objetoSelecionado.getPfi_id() > 0) {
			pessoaFisicaController.delete(objetoSelecionado);
			list.remove(objetoSelecionado);
			objetoSelecionado = new PessoaFisica();
			sucesso();
		}
	}

	@Override
	public void consultaEntidade() throws Exception {
		objetoSelecionado = new PessoaFisica();
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

	@RequestMapping("**/findPessoaFisica")
	public void findPessoaFisica(HttpServletResponse httpServletResponse,
			@RequestParam(value = "idPessoaFisica") Long idPessoaFisica) throws Exception {

		PessoaFisica pessoaFisica = pessoaFisicaController.findUninqueByPropertyId(PessoaFisica.class, idPessoaFisica,
				"pfi_id");
		if (pessoaFisica != null) {
			httpServletResponse.getWriter().write(pessoaFisica.getJson().toString());
		}

	}

	@Override
	protected InterfaceCrud<PessoaFisica> getController() {
		return pessoaFisicaController;
	}

	@Override
	public String condicaoAndParaPesquisa() {
		return "";
	}
}
