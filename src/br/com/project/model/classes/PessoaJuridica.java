package br.com.project.model.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.br.*;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "pessoaJuridica")
@SequenceGenerator(name = "pessoaJuridica_seq", sequenceName = "pessoaJuridica_seq", initialValue = 1, allocationSize = 1)
public class PessoaJuridica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "pju_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaJuridica_seq")
	private Long pju_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Cnpj", campoConsulta = "pju_cnpj", principal = 1)
	@Column(length = 20, nullable = false, unique = true)
	@CNPJ(message = "CNPJ Inválio...!")
	private String pju_cnpj;

	@IdentificaCampoPesquisa(descricaoCampo = "Inscrição Estadual", campoConsulta = "pju_ie", principal = 2)
	@Column(length = 30, nullable = true)
	private String pju_ie;

	@Column(length = 100, nullable = true)
	private String pju_foto;
	
	@Column(nullable = true)
	private Date pju_dataFundacao = new Date();
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pju_dataCadastro = new Date();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Inativo", campoConsulta = "pju_inativo")
	@Column(nullable = false)
	
	private Boolean pju_inativo = false;
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "entidade.ent_nome")
	@Basic
	@OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroEntidade", nullable = true)
	@ForeignKey(name = "registroEntidade_fk")
	private Entidade registroEntidade = new Entidade();
	
	public Entidade getRegistroEntidade() {
		return registroEntidade;
	}

	public void setRegistroEntidade(Entidade registroEntidade) {
		this.registroEntidade = registroEntidade;
	}

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getPju_id() {
		return pju_id;
	}

	public void setPju_id(Long pju_id) {
		this.pju_id = pju_id;
	}

	public String getPju_cnpj() {
		return pju_cnpj;
	}

	public void setPju_cnpj(String pju_cnpj) {
		this.pju_cnpj = pju_cnpj;
	}

	public String getPju_ie() {
		return pju_ie;
	}

	public void setPju_ie(String pju_ie) {
		this.pju_ie = pju_ie;
	}

	public String getPju_foto() {
		return pju_foto;
	}

	public void setPju_foto(String pju_foto) {
		this.pju_foto = pju_foto;
	}

	public Date getPju_dataFundacao() {
		return pju_dataFundacao;
	}

	public void setPju_dataFundacao(Date pju_dataFundacao) {
		this.pju_dataFundacao = pju_dataFundacao;
	}

	public Date getPju_dataCadastro() {
		return pju_dataCadastro;
	}

	public void setPju_dataCadastro(Date pju_dataCadastro) {
		this.pju_dataCadastro = pju_dataCadastro;
	}

	public Boolean getPju_inativo() {
		return pju_inativo;
	}

	public void setPju_inativo(Boolean pju_inativo) {
		this.pju_inativo = pju_inativo;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("pju_id", pju_id);
		map.put("pju_cnpj", pju_cnpj);
		map.put("pju_inativo", pju_inativo);
		map.put("pju_foto", pju_foto);

		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pju_id == null) ? 0 : pju_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaJuridica other = (PessoaJuridica) obj;
		if (pju_id == null) {
			if (other.pju_id != null)
				return false;
		} else if (!pju_id.equals(other.pju_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PessoaJuridica [pju_id=" + pju_id + ", pju_cnpj=" + pju_cnpj + ", pju_ie=" + pju_ie + ", pju_foto="
				+ pju_foto + ", pju_dataFundacao=" + pju_dataFundacao + ", pju_dataCadastro=" + pju_dataCadastro
				+ ", pju_inativo=" + pju_inativo + ", registroEntidade=" + registroEntidade + ", versionNum="
				+ versionNum + "]";
	}
}
