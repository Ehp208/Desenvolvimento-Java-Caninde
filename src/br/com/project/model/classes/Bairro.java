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
@Table(name = "bairro")
@SequenceGenerator(name = "bairro_seq", sequenceName = "bairro_seq", initialValue = 1, allocationSize = 1)
public class Bairro implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "bai_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bairro_seq")
	private Long bai_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "bai_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String bai_nome;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getBai_id() {
		return bai_id;
	}

	public void setBai_id(Long bai_id) {
		this.bai_id = bai_id;
	}

	public String getBai_nome() {
		return bai_nome;
	}

	public void setBai_nome(String bai_nome) {
		this.bai_nome = bai_nome;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("bai_id", bai_id);
		map.put("bai_nome", bai_nome);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bai_id == null) ? 0 : bai_id.hashCode());
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
		Bairro other = (Bairro) obj;
		if (bai_id == null) {
			if (other.bai_id != null)
				return false;
		} else if (!bai_id.equals(other.bai_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bairro [bai_id=" + bai_id + ", bai_nome=" + bai_nome + ", versionNum="
				+ versionNum + "]";
	}

}
