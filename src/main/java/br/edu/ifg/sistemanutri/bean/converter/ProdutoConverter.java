package br.edu.ifg.sistemanutri.bean.converter;

import br.edu.ifg.sistemanutri.entity.Produto;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto>{

    @Override
    public Produto getAsObject(FacesContext fc, UIComponent uic, String id) {
        if(id != null && !"".equals(id)){
            Produto produto = 
                    (Produto)uic.getAttributes().get("produto_"+id);
            return produto;
            
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Produto t) {
        if(t != null && t.getId() != null){
            uic.getAttributes().put("produto_"+t.getId(), t);
            return t.getId().toString();
        }
        return "";
    }
    
}
