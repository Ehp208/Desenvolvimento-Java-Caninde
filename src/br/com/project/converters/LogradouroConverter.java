package br.com.project.converters;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.project.model.classes.Logradouro;

@FacesConverter(forClass = Logradouro.class, value = "logradouroConverter")
public class LogradouroConverter implements Converter, Serializable {
	
	private static final long serialVersionUID = 1L;

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String codigo) {

		
		
System.out.println("FacesContext: " + arg0);		
System.out.println("UIComponent : " + arg1);		
System.out.println("String      : " + codigo);	
System.out.println("Name      : " + codigo.getClass().getName());
System.out.println("TypeName  : " + codigo.getClass().getTypeName());
System.out.println("SimpleName: " + codigo.getClass().getSimpleName());

System.out.println("============================================");		


		if (codigo != null && !codigo.isEmpty()) {
			return (Logradouro) HibernateUtil.getCurrentSession().get(Logradouro.class, new Long(codigo));
		}
		return codigo;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		if (objeto != null && objeto.getClass().getTypeName().equals("br.com.project.model.classes.Logradouro")) {
			Logradouro l = (Logradouro) objeto;
			return l.getLog_id() != null && l.getLog_id() > 0 ? l.getLog_id().toString() : null;
		} else {
			return null;
		}
	}	
}
