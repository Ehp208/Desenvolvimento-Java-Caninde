package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Cidade;

/**
 * Responsavel por efetuar a convers�o nos combos de campos
 * @author Eduardo H. Paula
 *
 */
@FacesConverter(forClass = Cidade.class, value = "cidadeConverter")
public class CidadeConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {
		if (codigo != null && !codigo.isEmpty()) {
				return (Cidade) HibernateUtil.getCurrentSession().get(Cidade.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Cidade")) {
			Cidade c = (Cidade) objeto;
			return c.getCid_id() != null && c.getCid_id() > 0 ? c.getCid_id().toString() : null;
		} else {
			return null;
		}
	}

}
