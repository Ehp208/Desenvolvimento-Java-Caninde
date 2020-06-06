package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Estado;

/**
 * Responsavel por efetuar a conversão nos combos de campos de estado
 * @author Eduardo H. Paula
 *
 */
@FacesConverter(forClass = Estado.class, value = "estadoConverter")
public class EstadoConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
				return (Estado) HibernateUtil.getCurrentSession().get(
						Estado.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Estado")) {
			Estado e = (Estado) objeto;
			return e.getEst_id() != null && e.getEst_id() > 0 ? e.getEst_id().toString() : null;
		} else {
			return null;
		}
	}

}
