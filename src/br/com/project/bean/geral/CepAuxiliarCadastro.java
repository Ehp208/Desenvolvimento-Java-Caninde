package br.com.project.bean.geral;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import org.hibernate.annotations.ForeignKey;

import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.model.classes.Bairro;
import br.com.project.model.classes.Cidade;
import br.com.project.model.classes.Logradouro;

public class CepAuxiliarCadastro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long cepAux_id;
	private String cepAux_codigo;
	private Logradouro logAux_id = new Logradouro();
	private Bairro baiAux_id = new Bairro();
	private Cidade cidAux_id = new Cidade();
	private Boolean cepAux_unico;
	
	public Long getCepAux_id() {
		return cepAux_id;
	}
	public void setCepAux_id(Long cepAux_id) {
		this.cepAux_id = cepAux_id;
	}
	public String getCepAux_codigo() {
		return cepAux_codigo;
	}
	public void setCepAux_codigo(String cepAux_codigo) {
		this.cepAux_codigo = cepAux_codigo;
	}
	public Logradouro getLogAux_id() {
		return logAux_id;
	}
	public void setLogAux_id(Logradouro logAux_id) {
		this.logAux_id = logAux_id;
	}
	public Bairro getBaiAux_id() {
		return baiAux_id;
	}
	public void setBaiAux_id(Bairro baiAux_id) {
		this.baiAux_id = baiAux_id;
	}
	public Cidade getCidAux_id() {
		return cidAux_id;
	}
	public void setCidAux_id(Cidade cidAux_id) {
		this.cidAux_id = cidAux_id;
	}
	public Boolean getCepAux_unico() {
		return cepAux_unico;
	}
	public void setCepAux_unico(Boolean cepAux_unico) {
		this.cepAux_unico = cepAux_unico;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cepAux_id == null) ? 0 : cepAux_id.hashCode());
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
		CepAuxiliarCadastro other = (CepAuxiliarCadastro) obj;
		if (cepAux_id == null) {
			if (other.cepAux_id != null)
				return false;
		} else if (!cepAux_id.equals(other.cepAux_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CepAuxiliarCadastro [cepAux_id=" + cepAux_id + ", cepAux_codigo=" + cepAux_codigo + ", logAux_id="
				+ logAux_id + ", baiAux_id=" + baiAux_id + ", cidAux_id=" + cidAux_id + ", cepAux_unico=" + cepAux_unico
				+ "]";
	}
}
