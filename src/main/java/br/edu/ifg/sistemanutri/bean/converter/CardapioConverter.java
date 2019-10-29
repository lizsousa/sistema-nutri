package br.edu.ifg.sistemanutri.bean.converter;

import br.edu.ifg.sistemanutri.entity.Cardapio;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;
import javax.faces.convert.Converter;

@FacesConverter(forClass = Cardapio.class)
public class CardapioConverter implements Converter<Cardapio> {
    
    @Override
    public Cardapio getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Cardapio cardapio =  (Cardapio)uic.getAttributes().get("cardapio_"+id);
            return cardapio;
            
        }
        return null;
    }
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Cardapio t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("cardapio_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
}
