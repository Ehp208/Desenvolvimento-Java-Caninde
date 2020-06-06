package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.project.enums.TipoEstatus;

@Audited
@Entity
@Table(name = "agenciador")
@SequenceGenerator(name = "agenciador_seq", sequenceName = "agenciador_seq", initialValue = 1, allocationSize = 1)
public class Agenciador implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "age_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agenciador_seq")
	private Long age_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "age_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String age_nome;

	@Column(length = 100, nullable = false)
	private String age_contato;

	@Column(length = 100, nullable = false)
	private String age_email;

	@Column(length = 20)
	private String age_fone;
	
	@Column(length = 20)
	private String age_celular;

	@IdentificaCampoPesquisa(descricaoCampo = "Status", campoConsulta = "age_inativo", principal = 2)
	@Column(nullable = false)
	private Boolean age_inativo = false;
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;

	protected int getVersionNum() { 
		return versionNum;
	}

	public Long getAge_id() {
		return age_id;
	}

	public void setAge_id(Long age_id) {
		this.age_id = age_id;
	}

	public String getAge_nome() {
		return age_nome;
	}

	public void setAge_nome(String age_nome) {
		this.age_nome = age_nome;
	}

	public String getAge_contato() {
		return age_contato;
	}

	public void setAge_contato(String age_contato) {
		this.age_contato = age_contato;
	}

	public String getAge_email() {
		return age_email;
	}

	public void setAge_email(String age_email) {
		this.age_email = age_email;
	}

	public String getAge_fone() {
		return age_fone;
	}

	public void setAge_fone(String age_fone) {
		this.age_fone = age_fone;
	}

	public String getAge_celular() {
		return age_celular;
	}

	public void setAge_celular(String age_celular) {
		this.age_celular = age_celular;
	}

	public Boolean getAge_inativo() {
		return age_inativo;
	}

	public void setAge_inativo(Boolean age_inativo) {
		this.age_inativo = age_inativo;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("age_id", age_id);
		map.put("age_nome", age_nome);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((age_id == null) ? 0 : age_id.hashCode());
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
		Agenciador other = (Agenciador) obj;
		if (age_id == null) {
			if (other.age_id != null)
				return false;
		} else if (!age_id.equals(other.age_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Agenciador [age_id=" + age_id + ", age_nome=" + age_nome + ", age_contato=" + age_contato
				+ ", age_email=" + age_email + ", age_fone=" + age_fone + ", age_celular=" + age_celular
				+ ", age_inativo=" + age_inativo + ", versionNum=" + versionNum + "]";
	}

}
