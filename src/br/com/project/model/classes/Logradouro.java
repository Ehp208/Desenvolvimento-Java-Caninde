package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "logradouro")
@SequenceGenerator(name = "logradouro_seq", sequenceName = "logradouro_seq", initialValue = 1, allocationSize = 1)
public class Logradouro implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "log_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "logradouro_seq")
	private Long log_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "log_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String log_nome;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
//	@Transient
//	@Column(name = "nomeLogradouro")
//	private String nomeLogradouro;
	
	public Long getLog_id() {
		return log_id;
	}

	public void setLog_id(Long log_id) {
		this.log_id = log_id;
	}

	public String getLog_nome() {
		return log_nome;
	}

	public void setLog_nome(String log_nome) {
		this.log_nome = log_nome;
	}
	
	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("log_id", log_id);
		map.put("log_nome", log_nome);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((log_id == null) ? 0 : log_id.hashCode());
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
		Logradouro other = (Logradouro) obj;
		if (log_id == null) {
			if (other.log_id != null)
				return false;
		} else if (!log_id.equals(other.log_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Logradouro [log_id=" + log_id + ", log_nome=" + log_nome + ", versionNum=" + versionNum + "]";
	}

}
