package br.com.project.model.classes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.envers.Audited;

import br.com.project.annotation.IdentificaCampoPesquisa;

@Audited
@Entity
@Table(name = "produto")
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", initialValue = 1, allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "Código", campoConsulta = "prd_id")
	@Id
	@Column(name = "prd_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	private Long prd_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Descrição", campoConsulta = "prd_descricao", principal = 1)
	@Column(nullable = false)
	private String prd_descricao;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Status", campoConsulta = "prd_estatus", principal = 2)
	@Column(nullable = false)
	private Boolean prd_estatus = false;

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getPrd_id() {
		return prd_id;
	}

	public void setPrd_id(Long prd_id) {
		this.prd_id = prd_id;
	}

	public String getPrd_descricao() {
		return prd_descricao;
	}

	public void setPrd_descricao(String prd_descricao) {
		this.prd_descricao = prd_descricao;
	}

	public Boolean getPrd_estatus() {
		return prd_estatus;
	}

	public void setPrd_estatus(Boolean prd_estatus) {
		this.prd_estatus = prd_estatus;
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
				+ ((prd_id == null) ? 0 : prd_id.hashCode());
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
		Produto other = (Produto) obj;
		if (prd_id == null) {
			if (other.prd_id != null)
				return false;
		} else if (!prd_id.equals(other.prd_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [prd_id=" + prd_id + ", prd_descricao=" + prd_descricao + ", prd_estatus=" + prd_estatus
				+ ", versionNum=" + versionNum + "]";
	}
}
