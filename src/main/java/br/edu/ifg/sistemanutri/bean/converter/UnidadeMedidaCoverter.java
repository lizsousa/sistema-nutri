package br.edu.ifg.sistemanutri.bean.converter;

import br.edu.ifg.sistemanutri.entity.UnidadeMedida;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter(forClass = UnidadeMedida.class)
public class UnidadeMedidaCoverter implements Converter<UnidadeMedida> {
    
    @Override
    public UnidadeMedida getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            UnidadeMedida unidadeMedida =  (UnidadeMedida)uic.getAttributes().get("unidadeMedida_"+id);
            return unidadeMedida;
            
        }
        return null;
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, UnidadeMedida t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("unidadeMedida_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
}
