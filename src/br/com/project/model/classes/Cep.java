package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "cep")
@SequenceGenerator(name = "cep_seq", sequenceName = "cep_seq", initialValue = 1, allocationSize = 1)
public class Cep implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "cep_id")
	@Id
	@Column(name = "cep_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cep_seq")
	private Long cep_id;

	/** No caso de CEP único para uma cidade, podermos te-lo mais de uma vez na tabela */
	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "cep_codigo", principal = 1)
	@Column(length = 9, nullable = false)
	private String cep_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Logradouro", campoConsulta = "registroLogradouro.log_nome", principal = 5)
	@Basic
	@ManyToOne
	@JoinColumn(name = "registroLogradouro", nullable = false)
	@ForeignKey(name = "registroLogradouro_fk")
	private Logradouro registroLogradouro = new Logradouro();

	@IdentificaCampoPesquisa(descricaoCampo = "Bairro", campoConsulta = "registroBairro.bai_nome", principal = 4)
	@Basic
	@ManyToOne
	@JoinColumn(name = "registroBairro", nullable = false)
	@ForeignKey(name = "registroBairro_fk")
	private Bairro registroBairro = new Bairro();

	@IdentificaCampoPesquisa(descricaoCampo = "Estado", campoConsulta = "registroEstado.est_uf", principal = 3)
	@Basic
	@ManyToOne
	@JoinColumn(name = "registroEstado", nullable = false)
	@ForeignKey(name = "registroEstado_fk")
	private Estado registroEstado = new Estado();

	@IdentificaCampoPesquisa(descricaoCampo = "Cidade", campoConsulta = "registroCidade.cid_nome", principal = 2)
	@Basic
	@ManyToOne
	@JoinColumn(name = "registroCidade", nullable = false)
	@ForeignKey(name = "registroCidade_fk")
	private Cidade registroCidade = new Cidade();

	@Column(nullable = false)
	private Boolean cep_unico;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getCep_id() {
		return cep_id;
	}

	public void setCep_id(Long cep_id) {
		this.cep_id = cep_id;
	}

	public String getCep_codigo() {
		return cep_codigo;
	}

	public void setCep_codigo(String cep_codigo) {
		this.cep_codigo = cep_codigo;
	}

	public Logradouro getRegistroLogradouro() {
		return registroLogradouro;
	}

	public void setRegistroLogradouro(Logradouro registroLogradouro) {
		this.registroLogradouro = registroLogradouro;
	}

	public Bairro getRegistroBairro() {
		return registroBairro;
	}

	public void setRegistroBairro(Bairro registroBairro) {
		this.registroBairro = registroBairro;
	}

	public Estado getRegistroEstado() {
		return registroEstado;
	}

	public void setRegistroEstado(Estado registroEstado) {
		this.registroEstado = registroEstado;
	}

	public Cidade getRegistroCidade() {
		return registroCidade;
	}

	public void setRegistroCidade(Cidade registroCidade) {
		this.registroCidade = registroCidade;
	}

	public Boolean getCep_unico() {
		return cep_unico;
	}

	public void setCep_unico(Boolean cep_unico) {
		this.cep_unico = cep_unico;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cep_id == null) ? 0 : cep_id.hashCode());
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
		Cep other = (Cep) obj;
		if (cep_id == null) {
			if (other.cep_id != null)
				return false;
		} else if (!cep_id.equals(other.cep_id))
			return false;
		return true;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("cep_id", cep_id);
		map.put("cep_codigo", cep_codigo);
		return new JSONObject(map);
	}

	@Override
	public String toString() {
		return "Cep [cep_id=" + cep_id + ", cep_codigo=" + cep_codigo + ", registroLogradouro=" + registroLogradouro
				+ ", registroBairro=" + registroBairro + ", registroEstado=" + registroEstado + ", registroCidade="
				+ registroCidade + ", cep_unico=" + cep_unico + ", versionNum=" + versionNum + "]";
	}
}