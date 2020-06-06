package br.com.project.model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.hibernate.annotations.CollectionOfElements;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Index;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.primefaces.json.JSONObject;

import br.com.project.acessos.Permissao;
import br.com.project.annotation.IdentificaCampoPesquisa;
import br.com.project.enums.TipoCadastro;
import br.com.project.enums.TipoPessoa;

@SuppressWarnings("deprecation")
@Audited
@Entity
@Table(name = "entidade")
@SequenceGenerator(name = "entidade_seq", sequenceName = "entidade_seq", initialValue = 1, allocationSize = 1)
public class Entidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@IdentificaCampoPesquisa(descricaoCampo = "ID", campoConsulta = "ent_id")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entidade_seq")
	private Long ent_id;

	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private TipoPessoa ent_tipopessoa;
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private TipoCadastro ent_tipocadastro;

	@IdentificaCampoPesquisa(descricaoCampo = "Nome", campoConsulta = "ent_nome", principal = 1)
	@Column(length = 100, nullable = false)
	private String ent_nome;
	
	@Column(length = 20)
	private String ent_fone;

	@Column(length = 20)
	private String ent_celular;

	@Column(length = 80)
	private String ent_contato;

	@Column(length = 100)
	@Email(message = "E_mail inválido...!")
	private String ent_email;

	@Column(length = 250)
	private String ent_observacao;

	@Column(length = 20, nullable = true, unique = true)
	@Index(name = "xlogin")
	private String ent_login = null;

	@Column(length = 20, nullable = true)
	private String ent_senha;

	@Column(nullable = true)
	private Boolean ent_mudarsenha;

	@Column(nullable = false)
	private Boolean ent_inativo = false;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_datacadastro = new Date();
	
	@IdentificaCampoPesquisa(descricaoCampo = "Cep", campoConsulta = "registrocep.cep_codigo", principal = 2)
	@Basic
	@ManyToOne
	@JoinColumn(name = "registrocep", nullable = false)
	@ForeignKey(name = "registrocep_fk")
	private Cep registroCep = new Cep();

	@Column(nullable = false)
	private Integer ent_numero;
	
	@Column(length = 30, nullable = true)
	private String ent_complemento = null;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date ent_ultimoacesso;

	@CollectionOfElements
	@ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
	@JoinTable(name = "entidadeacesso", uniqueConstraints = { @UniqueConstraint(name = "unique_acesso_entidade_key", columnNames = {
			"ent_id", "esa_codigo" }) }, joinColumns = { @JoinColumn(name = "ent_id") })
	@Column(name = "esa_codigo", length = 30)
	private Set<String> acessos = new HashSet<String>();

	@Version
	@Column(name = "versionNum")
	private int versionNum;
	
	public Long getEnt_id() {
		return ent_id;
	}

	public void setEnt_id(Long ent_id) {
		this.ent_id = ent_id;
	}

	public TipoPessoa getEnt_tipopessoa() {
		return ent_tipopessoa;
	}

	public void setEnt_tipopessoa(TipoPessoa ent_tipopessoa) {
		this.ent_tipopessoa = ent_tipopessoa;
	}

	public TipoCadastro getEnt_tipocadastro() {
		return ent_tipocadastro;
	}

	public void setEnt_tipocadastro(TipoCadastro ent_tipocadastro) {
		this.ent_tipocadastro = ent_tipocadastro;
	}

	public String getEnt_nome() {
		return ent_nome;
	}

	public void setEnt_nome(String ent_nome) {
		this.ent_nome = ent_nome;
	}

	public String getEnt_fone() {
		return ent_fone;
	}

	public void setEnt_fone(String ent_fone) {
		this.ent_fone = ent_fone;
	}

	public String getEnt_celular() {
		return ent_celular;
	}

	public void setEnt_celular(String ent_celular) {
		this.ent_celular = ent_celular;
	}

	public String getEnt_contato() {
		return ent_contato;
	}

	public void setEnt_contato(String ent_contato) {
		this.ent_contato = ent_contato;
	}

	public String getEnt_email() {
		return ent_email;
	}

	public void setEnt_email(String ent_email) {
		this.ent_email = ent_email;
	}

	public String getEnt_observacao() {
		return ent_observacao;
	}

	public void setEnt_observacao(String ent_observacao) {
		this.ent_observacao = ent_observacao;
	}

	public String getEnt_login() {
		return ent_login;
	}

	public void setEnt_login(String ent_login) {
		this.ent_login = ent_login;
	}

	public String getEnt_senha() {
		return ent_senha;
	}

	public void setEnt_senha(String ent_senha) {
		this.ent_senha = ent_senha;
	}

	public Boolean getEnt_mudarsenha() {
		return ent_mudarsenha;
	}

	public void setEnt_mudarsenha(Boolean ent_mudarsenha) {
		this.ent_mudarsenha = ent_mudarsenha;
	}

	public Boolean getEnt_inativo() {
		return ent_inativo;
	}

	public void setEnt_inativo(Boolean ent_inativo) {
		this.ent_inativo = ent_inativo;
	}

	public Date getEnt_datacadastro() {
		return ent_datacadastro;
	}

	public void setEnt_datacadastro(Date ent_datacadastro) {
		this.ent_datacadastro = ent_datacadastro;
	}

	public Cep getRegistroCep() {
		return registroCep;
	}

	public void setRegistroCep(Cep registroCep) {
		this.registroCep = registroCep;
	}

	public Integer getEnt_numero() {
		return ent_numero;
	}

	public void setEnt_numero(Integer ent_numero) {
		this.ent_numero = ent_numero;
	}

	public String getEnt_complemento() {
		return ent_complemento;
	}

	public void setEnt_complemento(String ent_complemento) {
		this.ent_complemento = ent_complemento;
	}

	public Date getEnt_ultimoacesso() {
		return ent_ultimoacesso;
	}

	public void setEnt_ultimoacesso(Date ent_ultimoacesso) {
		this.ent_ultimoacesso = ent_ultimoacesso;
	}

	public int getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(int versionNum) {
		this.versionNum = versionNum;
	}

	public Set<String> getAcessos() {
		return acessos;
	}
	
	public List<String> getAcessosOrdenadas() {
		List<String> retorno = new ArrayList<String>();
		for (String acesso : acessos) {
			retorno.add(acesso);
		}
		Collections.sort(retorno);
		return retorno;
	}

	public Set<Permissao> getAcessosPermissao() {
		Set<Permissao> permissoes = new HashSet<Permissao>();
		for (String acesso : getAcessosOrdenadas()) {
			for (Permissao acess : Permissao.values()) {
				if (acesso.equalsIgnoreCase(acess.name())) {
					permissoes.add(acess);
				}
			}
		}
		return permissoes;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}

	public JSONObject getJson() {
		Map<Object, Object> map = new HashMap<Object, Object>();
		map.put("ent_id", ent_id);
		map.put("ent_login", ent_login);
		map.put("ent_nome", ent_nome);
		return new JSONObject(map);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ent_id == null) ? 0 : ent_id.hashCode());
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
		Entidade other = (Entidade) obj;
		if (ent_id == null) {
			if (other.ent_id != null)
				return false;
		} else if (!ent_id.equals(other.ent_id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entidade [ent_id=" + ent_id + ", ent_tipopessoa=" + ent_tipopessoa + ", ent_tipocadastro="
				+ ent_tipocadastro + ", ent_nome=" + ent_nome + ", ent_fone=" + ent_fone + ", ent_celular="
				+ ent_celular + ", ent_contato=" + ent_contato + ", ent_email=" + ent_email + ", ent_observacao="
				+ ent_observacao + ", ent_login=" + ent_login + ", ent_senha=" + ent_senha + ", ent_mudarsenha="
				+ ent_mudarsenha + ", ent_inativo=" + ent_inativo + ", ent_datacadastro=" + ent_datacadastro
				+ ", registroCep=" + registroCep + ", ent_numero=" + ent_numero + ", ent_complemento=" + ent_complemento
				+ ", ent_ultimoacesso=" + ent_ultimoacesso + ", acessos=" + acessos + ", versionNum=" + versionNum
				+ "]";
	}
}
