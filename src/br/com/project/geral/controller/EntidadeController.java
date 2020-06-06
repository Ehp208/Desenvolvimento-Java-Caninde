package br.com.project.geral.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.enums.TipoCadastro;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.PessoaFisica;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements
		InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;

	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUninqueByProperty(Entidade.class, userLogado,
				"ent_login", " and entity.ent_inativo is false ");
	}

	public Date getUltimoAcessoEntidadeLogada(String login) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcessoUser(String login) {
		srvEntidade.updateUltimoAcessoUser(login);
	}

	public Entidade findUsuario(Long codUsuario) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codUsuario,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_USUARIO.name() + "' ");
	}

	public Entidade findCliente(Long codCliente) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codCliente,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_CLIENTE.name() + "' ");
	}

	public Entidade findFornecedor(Long codFornecedor) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codFornecedor,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_FORNECEDOR.name() + "' ");
	}

	public Entidade findProdutorRural(Long codProdutorRural) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codProdutorRural,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_PRODUTORRURAL.name() + "' ");
	}

	public Entidade findCorretor(Long codCorretor) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codCorretor,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_CORRETOR.name() + "' ");
	}

	public Entidade findEmbarcador(Long codEmbarcador) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codEmbarcador,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_EMBARCADOR.name() + "' ");
	}

	public Entidade findAgenciador(Long codAgenciador) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codAgenciador,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_AGENCIADOR.name() + "' ");
	}

	public Entidade findMotorista(Long codMotorista) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codMotorista,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_MOTORISTA.name() + "' ");
	}

	public Entidade findParceiroFrete(Long codParceiroFrete) throws Exception {
		return findUninqueByPropertyId(Entidade.class, codParceiroFrete,
				"ent_id", " and entity.ent_inativo is false and entity.ent_tipocadastro = '"
						+ TipoCadastro.TIPO_CADASTRO_PARCEIROFRETE.name() + "' ");
	}


	public boolean existeEntidade(String ent_login) {
		return srvEntidade.existeEntidade(ent_login);
	}
	
/**
 *			Rotinas incluídas para a customização do sistema 
*/

	public boolean findNomeUnico(String nomeUnico) throws Exception {		
		List<Entidade> list = (List<Entidade>) super.findListByQueryDinamica(
				"from Entidade where retira_acentos(upper(ent_nome)) = retira_acentos(upper('" + nomeUnico + "'))");
		if (list.size() == 0 ) {
			return false;
		} else {
			return true;
		}
	}	
	
}
