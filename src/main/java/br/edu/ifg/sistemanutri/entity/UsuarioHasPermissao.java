package br.edu.ifg.sistemanutri.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario_has_permissao")
public class UsuarioHasPermissao implements Serializable {

    @EmbeddedId
    private UsuarioHasPermissaoPk id;

    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "permissao_id")
    private Permissao permissao;
    @ManyToOne
    @JoinColumn(updatable = false, insertable = false, name = "usuario_id")
    private Usuario usuario;
    
    public UsuarioHasPermissaoPk getId() {
        return id;
    }

    public void setId(UsuarioHasPermissaoPk id) {
        this.id = id;
    }
    
    public Permissao getPermissao() {
        return permissao;
    }

    public void setPermissao(Permissao permissao) {
        this.permissao = permissao;
        if(id == null){
            id =  new UsuarioHasPermissaoPk();
        }
        id.setPermissaoId(permissao.getId());
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        if(id == null){
            id =  new UsuarioHasPermissaoPk();
        }
        id.setUsuarioId(usuario.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioHasPermissao other = (UsuarioHasPermissao) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
