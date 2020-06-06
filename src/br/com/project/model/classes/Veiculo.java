package br.com.project.model.classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import br.com.project.enums.TipoEstatus;

@Audited
@Entity
@Table(name = "veiculo")
@SequenceGenerator(name = "veiculo_seq", sequenceName = "veiculo_seq", initialValue = 1, allocationSize = 1)
public class Veiculo implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "vei_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculo_seq")
	private Long vei_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Placa", campoConsulta = "vei_placa", principal = 1)
	@Column(length = 10, nullable = false, unique = true)
	private String vei_placa;
	
	@IdentificaCampoPesquisa(descricaoCampo = "Status", campoConsulta = "vei_inativo", principal = 4)
	@Column(nullable = false)
	private Boolean vei_inativo = false;
		
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

	public Long getVei_id() {
		return vei_id;
	}

	public void setVei_id(Long vei_id) {
		this.vei_id = vei_id;
	}

	public String getVei_placa() {
		return vei_placa;
	}

	public void setVei_placa(String vei_placa) {
		this.vei_placa = vei_placa;
	}

	public Boolean getVei_inativo() {
		return vei_inativo;
	}

	public void setVei_inativo(Boolean vei_inativo) {
		this.vei_inativo = vei_inativo;
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
		map.put("vei_id", vei_id);
		map.put("vei_placa", vei_placa);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vei_id == null) ? 0 : vei_id.hashCode());
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
		Veiculo other = (Veiculo) obj;
		if (vei_id == null) {
			if (other.vei_id != null)
				return false;
		} else if (!vei_id.equals(other.vei_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Veiculo [vei_id=" + vei_id + ", vei_placa=" + vei_placa + ", vei_inativo=" + vei_inativo
				+ ", registroEstado=" + registroEstado + ", registroCidade=" + registroCidade + ", versionNum="
				+ versionNum + "]";
	}

}
