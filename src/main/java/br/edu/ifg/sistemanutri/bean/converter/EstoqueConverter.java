package br.edu.ifg.sistemanutri.bean.converter;

import br.edu.ifg.sistemanutri.entity.Estoque;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Estoque.class)
public class EstoqueConverter implements Converter<Estoque>{
    
    @Override
    public Estoque getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Estoque estoque = 
                    (Estoque)uic.getAttributes().get("estoque_"+id);
            return estoque;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Estoque t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("estoque_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
    
}
