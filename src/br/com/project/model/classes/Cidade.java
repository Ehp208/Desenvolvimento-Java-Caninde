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
import javax.persistence.Version;

import org.hibernate.envers.Audited;
import org.primefaces.json.JSONObject;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "cidade")
@SequenceGenerator(name = "cidade_seq", sequenceName = "cidade_seq", initialValue = 1, allocationSize = 1)
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "cid_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade_seq")
	private Long cid_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "cid_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String cid_nome;

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	

	public Long getCid_id() {
		return cid_id;
	}

	public void setCid_id(Long cid_id) {
		this.cid_id = cid_id;
	}

	public String getCid_nome() {
		return cid_nome;
	}

	public void setCid_nome(String cid_nome) {
		this.cid_nome = cid_nome;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("cid_id", cid_id);
		map.put("cid_nome", cid_nome);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cid_id == null) ? 0 : cid_id.hashCode());
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
		Cidade other = (Cidade) obj;
		if (cid_id == null) {
			if (other.cid_id != null)
				return false;
		} else if (!cid_id.equals(other.cid_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cidade [cid_id=" + cid_id + ", cid_nome=" + cid_nome + ", versionNum=" + versionNum + "]";
	}



}
