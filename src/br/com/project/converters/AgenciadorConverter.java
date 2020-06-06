package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Agenciador;

/**
 * Responsavel por efetuar a conversão nos combos de campos
 * @author Eduardo H. Paula
 *
 */
@FacesConverter(forClass = Agenciador.class, value = "agenciadorConverter")
public class AgenciadorConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
				return (Agenciador) HibernateUtil.getCurrentSession().get(Agenciador.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Agenciador")) {
			Agenciador l = (Agenciador) objeto;
			return l.getAge_id() != null && l.getAge_id() > 0 ? l.getAge_id().toString() : null;
		} else {
			return null;
		}
	}
	
}
