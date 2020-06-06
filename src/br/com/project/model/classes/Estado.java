package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "estado")
@SequenceGenerator(name = "estado_seq", sequenceName = "estado_seq", initialValue = 1, allocationSize = 1)
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "est_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado_seq")
	private Long est_id;

	@Column(length = 10, nullable = true)
	private String est_uf;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "est_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String est_nome;

	@IdentificaCampoPesquisa(descricaoCampo = "Pais", campoConsulta = "registroPais.pai_nome")
	@Basic
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "registroPais", nullable = false)
	@ForeignKey(name = "registroPais_fk")
	private Pais registroPais = new Pais();
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	protected int getVersionNum() { 
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Long getEst_id() {
		return est_id;
	}

	public void setEst_id(Long est_id) {
		this.est_id = est_id;
	}

	public String getEst_uf() {
		return est_uf;
	}

	public void setEst_uf(String est_uf) {
		this.est_uf = est_uf;
	}

	public String getEst_nome() {
		return est_nome;
	}

	public void setEst_nome(String est_nome) {
		this.est_nome = est_nome;
	}

	public Pais getRegistroPais() {
		return registroPais;
	}

	public void setRegistroPais(Pais registroPais) {
		this.registroPais = registroPais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((est_id == null) ? 0 : est_id.hashCode());
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
		Estado other = (Estado) obj;
		if (est_id == null) {
			if (other.est_id != null)
				return false;
		} else if (!est_id.equals(other.est_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Estado [est_id=" + est_id + ", est_uf=" + est_uf + ", est_nome=" + est_nome + ", registroPais="
				+ registroPais + ", versionNum=" + versionNum + "]";
	}
}