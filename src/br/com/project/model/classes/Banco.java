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
@Table(name = "banco")
@SequenceGenerator(name = "banco_seq", sequenceName = "banco_seq", initialValue = 1, allocationSize = 1)
public class Banco implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *   Explicação  BeanManegedViewAbastract  public List<SelectItem> getListCampoPesquisa()
	 */
	@IdentificaCampoPesquisa(descricaoCampo = "Id", campoConsulta = "ban_id", principal = 2)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "banco_seq")
	private Long ban_id;

	@Column(length = 3, nullable = true, unique = true)
	private String ban_codigo;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "ban_nome", principal = 1)
	@Column(length = 100, nullable = false, unique = true)
	private String ban_nome;
	
	
	
	
	
	
	
	
	
/**
 * Campos de teste para complementar a pesquisa.	
 
	@IdentificaCampoPesquisa(descricaoCampo = "Data da baixa", campoConsulta = "ban_databaixa")
	@Temporal(TemporalType.DATE)
	private Date ban_databaixa;

	
	@IdentificaCampoPesquisa(descricaoCampo = "Valor R$", campoConsulta = "ban_valor")
	@Column(scale = 4, precision = 15)
	private BigDecimal ban_valor = BigDecimal.ZERO;
	
	
	public Date getBan_databaixa() {
		return ban_databaixa;
	}

	public void setBan_databaixa(Date ban_databaixa) {
		this.ban_databaixa = ban_databaixa;
	}

	public BigDecimal getBan_valor() {
		return ban_valor;
	}

	public void setBan_valor(BigDecimal ban_valor) {
		this.ban_valor = ban_valor;
	}
*/
	
/**
 * Final dos testes
 */
	

	
	
	
	
	
	
	
	
	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getBan_id() {
		return ban_id;
	}

	public void setBan_id(Long ban_id) {
		this.ban_id = ban_id;
	}


	public String getBan_codigo() {
		return ban_codigo;
	}

	public void setBan_codigo(String ban_codigo) {
		this.ban_codigo = ban_codigo;
	}

	public String getBan_nome() {
		return ban_nome;
	}

	public void setBan_nome(String ban_nome) {
		this.ban_nome = ban_nome;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}
		
	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ban_id", ban_id);
		map.put("ban_nome", ban_nome);
		return new JSONObject(map);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ban_id == null) ? 0 : ban_id.hashCode());
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
		Banco other = (Banco) obj;
		if (ban_id == null) {
			if (other.ban_id != null)
				return false;
		} else if (!ban_id.equals(other.ban_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Banco [ban_id=" + ban_id + ", ban_codigo=" + ban_codigo + ", ban_nome=" + ban_nome + "]";
	}

}
