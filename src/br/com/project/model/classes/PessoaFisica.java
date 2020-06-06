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
import org.hibernate.validator.constraints.br.CPF;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "pessoaFisica")
@SequenceGenerator(name = "pessoaFisica_seq", sequenceName = "pessoaFisica_seq", initialValue = 1, allocationSize = 1)
public class PessoaFisica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "pfi_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaFisica_seq")
	private Long pfi_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Cpf", campoConsulta = "pfi_cpf", principal = 1)
	@Column(length = 20, nullable = false, unique = true)
	@CPF(message = "CPF Inválido...!")
	private String pfi_cpf;

	@IdentificaCampoPesquisa(descricaoCampo = "Rg", campoConsulta = "pfi_rg", principal = 2)
	@Column(length = 20, nullable = true)
	private String pfi_rg;

	@Column(length = 100, nullable = true)
	private String pfi_foto;
	
	@Column(nullable = true)
	private Date pfi_dataNascimento = new Date();
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pfi_dataCadastro = new Date();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Inativo", campoConsulta = "pfi_inativo")
	@Column(nullable = false)
	private Boolean pfi_inativo = false;
	
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
	
	public Long getPfi_id() {
		return pfi_id;
	}

	public void setPfi_id(Long pfi_id) {
		this.pfi_id = pfi_id;
	}

	public String getPfi_cpf() {
		return pfi_cpf;
	}

	public void setPfi_cpf(String pfi_cpf) {
		this.pfi_cpf = pfi_cpf;
	}

	public String getPfi_rg() {
		return pfi_rg;
	}

	public void setPfi_rg(String pfi_rg) {
		this.pfi_rg = pfi_rg;
	}

	public String getPfi_foto() {
		return pfi_foto;
	}

	public void setPfi_foto(String pfi_foto) {
		this.pfi_foto = pfi_foto;
	}

	public Date getPfi_dataNascimento() {
		return pfi_dataNascimento;
	}

	public void setPfi_dataNascimento(Date pfi_dataNascimento) {
		this.pfi_dataNascimento = pfi_dataNascimento;
	}

	public Date getPfi_dataCadastro() {
		return pfi_dataCadastro;
	}

	public void setPfi_dataCadastro(Date pfi_dataCadastro) {
		this.pfi_dataCadastro = pfi_dataCadastro;
	}

	public Boolean getPfi_inativo() {
		return pfi_inativo;
	}

	public void setPfi_inativo(Boolean pfi_inativo) {
		this.pfi_inativo = pfi_inativo;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("pfi_id", pfi_id);
		map.put("pfi_cpf", pfi_cpf);
		map.put("pfi_inativo", pfi_inativo);
		map.put("pfi_foto", pfi_foto);

		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pfi_id == null) ? 0 : pfi_id.hashCode());
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
		PessoaFisica other = (PessoaFisica) obj;
		if (pfi_id == null) {
			if (other.pfi_id != null)
				return false;
		} else if (!pfi_id.equals(other.pfi_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PessoaFisica [pfi_id=" + pfi_id + ", pfi_cpf=" + pfi_cpf + ", pfi_rg=" + pfi_rg + ", pfi_foto="
				+ pfi_foto + ", pfi_dataNascimento=" + pfi_dataNascimento + ", pfi_dataCadastro=" + pfi_dataCadastro
				+ ", pfi_inativo=" + pfi_inativo + ", registroEntidade=" + registroEntidade + ", versionNum="
				+ versionNum + "]";
	}
}
