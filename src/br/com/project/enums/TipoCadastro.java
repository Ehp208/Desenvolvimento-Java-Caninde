package br.com.project.enums;

public enum TipoCadastro {

	TIPO_CADASTRO_USUARIO("Usuario"),
	TIPO_CADASTRO_CLIENTE("Cliente"),
	TIPO_CADASTRO_FORNECEDOR("Fornecedor"),
	TIPO_CADASTRO_PRODUTORRURAL("Produtor Rural"),
	TIPO_CADASTRO_CORRETOR("Corretor"),
	TIPO_CADASTRO_EMBARCADOR("Embarcador"),
	TIPO_CADASTRO_AGENCIADOR("Agenciador"),
	TIPO_CADASTRO_MOTORISTA("Motorista"),
	TIPO_CADASTRO_PARCEIROFRETE("Parceiro Frete");
	
	private String tipo = "";

	private TipoCadastro(String tipo) {
		this.tipo = tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}
	
	public String getName() {
		return this.name();
	}
	
	public static TipoCadastro[] getValuePadraoTipoCadastro() {
		return new TipoCadastro[] { 
				TIPO_CADASTRO_PRODUTORRURAL,
				TIPO_CADASTRO_USUARIO,
				TIPO_CADASTRO_CLIENTE,
				TIPO_CADASTRO_FORNECEDOR,
				TIPO_CADASTRO_CORRETOR,
				TIPO_CADASTRO_EMBARCADOR,
				TIPO_CADASTRO_AGENCIADOR,
				TIPO_CADASTRO_PARCEIROFRETE,
				TIPO_CADASTRO_MOTORISTA };
	}
}
