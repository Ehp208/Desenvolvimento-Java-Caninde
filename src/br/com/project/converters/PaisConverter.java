package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Pais;

/**
 * Responsavel por efetuar a conversão nos combos de campos de pais
 * @author Eduardo H. Paula
 *
 */
@FacesConverter(forClass = Pais.class, value = "paisConverter")
public class PaisConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L; 

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
			return (Pais) HibernateUtil.getCurrentSession().get(Pais.class,
					new Long(codigo));
		} else {
			return null; 
		}
	}
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Pais")) {
			Pais c = (Pais) objeto;
			return c.getPai_id() != null && c.getPai_id() > 0 ? c.getPai_id().toString() : null;
		} else {
			return null;
		}
	}

}
