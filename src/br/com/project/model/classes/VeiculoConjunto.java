package br.com.project.model.classes;

import java.io.Serializable;
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
@Table(name = "veiculoConjunto")
@SequenceGenerator(name = "veiculoConjunto_seq", sequenceName = "veiculoConjunto_seq", initialValue = 1, allocationSize = 1)
public class VeiculoConjunto implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "vcj_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "veiculoConjunto_seq")
	private Long vcj_id;

	@IdentificaCampoPesquisa(descricaoCampo = "Placa Cavalo", campoConsulta = "vei_placa", principal = 1)
	@Basic
	@ManyToOne(cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroVeiculo", nullable = true)
	@ForeignKey(name = "registroVeiculo_fk")
	private Veiculo registroVeiculo = new Veiculo();

	@IdentificaCampoPesquisa(descricaoCampo = "Placa 1 Carreta", campoConsulta = "vca_placa", principal = 2)
	@Basic
	@ManyToOne(cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroVeiculoCarreta1", nullable = true)
	@ForeignKey(name = "registroVeiculoCarreta1_fk")
	private VeiculoCarreta registroVeiculoCarreta1 = new VeiculoCarreta();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Placa 2 Carreta", campoConsulta = "vca_placa", principal = 3)
	@Basic
	@ManyToOne(cascade = {CascadeType.ALL})	
	@JoinColumn(name = "registroVeiculoCarreta2", nullable = false)
	@ForeignKey(name = "registroVeiculoCarreta2_fk")
	private VeiculoCarreta registroVeiculoCarreta2 = new VeiculoCarreta();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Status", campoConsulta = "vcj_inativo", principal = 4)
	@Column(nullable = false)
	private Boolean vca_inativo = false;

	@Version
	@Column(name = "versionNum")
	private int versionNum;

	public Long getVcj_id() {
		return vcj_id;
	}

	public void setVcj_id(Long vcj_id) {
		this.vcj_id = vcj_id;
	}

	public Veiculo getRegistroVeiculo() {
		return registroVeiculo;
	}

	public void setRegistroVeiculo(Veiculo registroVeiculo) {
		this.registroVeiculo = registroVeiculo;
	}

	public VeiculoCarreta getRegistroVeiculoCarreta1() {
		return registroVeiculoCarreta1;
	}

	public void setRegistroVeiculoCarreta1(VeiculoCarreta registroVeiculoCarreta1) {
		this.registroVeiculoCarreta1 = registroVeiculoCarreta1;
	}

	public VeiculoCarreta getRegistroVeiculoCarreta2() {
		return registroVeiculoCarreta2;
	}

	public void setRegistroVeiculoCarreta2(VeiculoCarreta registroVeiculoCarreta2) {
		this.registroVeiculoCarreta2 = registroVeiculoCarreta2;
	}

	public Boolean getVca_inativo() {
		return vca_inativo;
	}

	public void setVca_inativo(Boolean vca_inativo) {
		this.vca_inativo = vca_inativo;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("vcj_id", vcj_id);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((vcj_id == null) ? 0 : vcj_id.hashCode());
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
		VeiculoConjunto other = (VeiculoConjunto) obj;
		if (vcj_id == null) {
			if (other.vcj_id != null)
				return false;
		} else if (!vcj_id.equals(other.vcj_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VeiculoConjunto [vcj_id=" + vcj_id + ", registroVeiculo=" + registroVeiculo
				+ ", registroVeiculoCarreta1=" + registroVeiculoCarreta1 + ", registroVeiculoCarreta2="
				+ registroVeiculoCarreta2 + ", vca_inativo=" + vca_inativo + ", versionNum=" + versionNum + "]";
	}
}
