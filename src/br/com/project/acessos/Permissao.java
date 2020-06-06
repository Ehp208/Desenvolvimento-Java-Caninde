package br.com.project.acessos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum Permissao {
	// Tamanho maximo 30 caracteres //
	CEO("CEO","CEO-Responsável Pelo Projeto"), 
	ADMIN("ADMIN", "Administrador"),
	FIN("FIN", "Financeiro"),
	USER("USER","Usuário Padrão"), 	
	EMB("EMB","Embarcador"), 
	COR("COR","Corretor"), 
	AGE("AGE","Agenciador"), 
	FRE("FRE","Parceiro Frete"), 
	APPLET_FILE_LOCAL("APPLET_FILE_LOCAL", "Habilitar - Applet"),
		
	MENSAGEM_ACESSAR("MENSAGEM_ACESSAR", "Mensagem recebida - Acessar"),

	MENSAGENS_ENVIAR_ACESSAR("MENSAGENS_ENVIAR_ACESSAR", "Enviar mensagem - Acessar"),
	MENSAGENS_ENVIAR_NOVO("MENSAGENS_ENVIAR_NOVO", "Enviar mensagem - Novo"),
	MENSAGENS_ENVIAR_EDITAR("MENSAGENS_ENVIAR_EDITAR", "Enviar mensagem - Editar"),
	MENSAGENS_ENVIAR_EXCLUIR("MENSAGENS_ENVIAR_EXCLUIR", "Enviar mensagem - Excluir");
		
	
	private String valor = "";
	private String descricao = "";

	private Permissao(String name, String descricao) {
		this.valor = name;
		this.descricao = descricao;
	}

	private Permissao() {
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return getValor();
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static List<Permissao> getListPermissao() {
		List<Permissao> permissoes = new ArrayList<Permissao>();
		for (Permissao permissao : Permissao.values()) {
			permissoes.add(permissao);
		}
		Collections.sort(permissoes, new Comparator<Permissao>() {

			@Override
			public int compare(Permissao o1, Permissao o2) {
				return new Integer(o1.ordinal()).compareTo(new Integer(o2.ordinal()));
			}
		});
		return permissoes;
	}

}
