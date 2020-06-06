package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Bairro;

/**
 * Responsavel por efetuar a conversão nos combos de campos
 * @author Eduardo H. Paula
 *
 */
@FacesConverter(forClass = Bairro.class, value = "bairroConverter")
public class BairroConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
				return (Bairro) HibernateUtil.getCurrentSession().get(Bairro.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Bairro")) {
			Bairro b = (Bairro) objeto;
			return b.getBai_id() != null && b.getBai_id() > 0 ? b.getBai_id().toString() : null;
		} else {
			return null;
		}
	}
}
