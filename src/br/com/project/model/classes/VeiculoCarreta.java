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
@Table(name = "veiculoCarreta")
@SequenceGenerator(name = "veiculoCarreta_seq", sequenceName = "veiculoCarreta_seq", initialValue = 1, allocationSize = 1)
public class VeiculoCarreta implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "vca_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculoCarreta_seq")
	private Long vca_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Placa", campoConsulta = "vca_placa", principal = 1)
	@Column(length = 10, nullable = false, unique = true)
	private String vca_placa;

	@IdentificaCampoPesquisa(descricaoCampo = "Status", campoConsulta = "vca_inativo", principal = 2)
	@Column(nullable = false)
	private Boolean vca_inativo = false;

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

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getVca_id() {
		return vca_id;
	}

	public void setVca_id(Long vca_id) {
		this.vca_id = vca_id;
	}

	public String getVca_placa() {
		return vca_placa;
	}

	public void setVca_placa(String vca_placa) {
		this.vca_placa = vca_placa;
	}

	public Boolean getVca_inativo() {
		return vca_inativo;
	}

	public void setVca_inativo(Boolean vca_inativo) {
		this.vca_inativo = vca_inativo;
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

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("vca_id", vca_id);
		map.put("vca_placa", vca_placa);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vca_id == null) ? 0 : vca_id.hashCode());
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
		VeiculoCarreta other = (VeiculoCarreta) obj;
		if (vca_id == null) {
			if (other.vca_id != null)
				return false;
		} else if (!vca_id.equals(other.vca_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VeiculoCarreta [vca_id=" + vca_id + ", vca_placa=" + vca_placa + ", vca_inativo=" + vca_inativo
				+ ", registroEstado=" + registroEstado + ", registroCidade=" + registroCidade + ", versionNum="
				+ versionNum + "]";
	}

}
