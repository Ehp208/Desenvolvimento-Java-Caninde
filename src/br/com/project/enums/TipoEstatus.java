package br.com.project.enums;

public enum TipoEstatus {

	TIPO_ESTATUS_ATIVO("Ativo"),
	TIPO_ESTATUS_INATIVO("Inativo");

	private String tipo = "";

	private TipoEstatus(String tipo) {
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
	public static TipoEstatus[] getValuePadraoTipoSituacao() {
		return new TipoEstatus[] { 
				TIPO_ESTATUS_ATIVO,
				TIPO_ESTATUS_INATIVO };
	}
}
