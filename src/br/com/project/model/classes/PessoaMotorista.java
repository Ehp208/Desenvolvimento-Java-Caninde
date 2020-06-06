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
@Table(name = "pessoaMotorista")
@SequenceGenerator(name = "pessoaMotorista_seq", sequenceName = "pessoaMotorista_seq", initialValue = 1, allocationSize = 1)
public class PessoaMotorista implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "pmo_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoaMotorista_seq")
	private Long pmo_id;
	
	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date pmo_dataCadastro = new Date();
		
	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "entidade.ent_nome")
	@Basic
	@OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroEntidade", nullable = true)
	@ForeignKey(name = "registroEntidade_fk")
	private Entidade registroEntidade = new Entidade();

	@IdentificaCampoPesquisa(descricaoCampo = "Agenciador", campoConsulta = "agenciador.age_nome")
	@Basic
	@OneToOne(orphanRemoval = true, cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroAgenciador", nullable = true)
	@ForeignKey(name = "registroAgenciador_fk")
	private Agenciador registroAgenciador = new Agenciador();

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getPmo_id() {
		return pmo_id;
	}

	public void setPmo_id(Long pmo_id) {
		this.pmo_id = pmo_id;
	}

	public Date getPmo_dataCadastro() {
		return pmo_dataCadastro;
	}

	public void setPmo_dataCadastro(Date pmo_dataCadastro) {
		this.pmo_dataCadastro = pmo_dataCadastro;
	}

	public Entidade getRegistroEntidade() {
		return registroEntidade;
	}

	public void setRegistroEntidade(Entidade registroEntidade) {
		this.registroEntidade = registroEntidade;
	}

	public Agenciador getRegistroAgenciador() {
		return registroAgenciador;
	}

	public void setRegistroAgenciador(Agenciador registroAgenciador) {
		this.registroAgenciador = registroAgenciador;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("pmo_id", pmo_id);

		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pmo_id == null) ? 0 : pmo_id.hashCode());
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
		PessoaMotorista other = (PessoaMotorista) obj;
		if (pmo_id == null) {
			if (other.pmo_id != null)
				return false;
		} else if (!pmo_id.equals(other.pmo_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PessoaMotorista [pmo_id=" + pmo_id + ", pmo_dataCadastro=" + pmo_dataCadastro + ", registroEntidade="
				+ registroEntidade + ", registroAgenciador=" + registroAgenciador + ", versionNum=" + versionNum + "]";
	}
}
