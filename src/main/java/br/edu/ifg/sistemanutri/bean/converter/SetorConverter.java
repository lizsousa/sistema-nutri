package br.edu.ifg.sistemanutri.bean.converter;

import br.edu.ifg.sistemanutri.entity.Setor;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Setor.class)
public class SetorConverter implements Converter<Setor>{
    
    @Override
    public Setor getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Setor setor = 
                    (Setor)uic.getAttributes().get("setor_"+id);
            return setor;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Setor t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("setor_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
    
}
