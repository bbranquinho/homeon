package org.home.on.user;

import org.home.on.permission.PermissionEntity;
import org.home.on.utils.BaseEntity;

import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_user")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class UserEntity extends BaseEntity<Long> {
    private static final long serialVersionUID = 1L;

    @Column(name = "nome", length = 120, nullable = false)
    private String name;
    @Column(name = "email", length = 255, nullable = false)
    private String email;
    @Column(name = "endereco", length = 120, nullable = true)
    private String endereco;
    @Column(name = "cidade", length = 120, nullable = true)
    private String cidade;
    @Column(name = "estado", length = 120, nullable = true)
    private String estado;
    @Column(name = "senha", length = 80, nullable = false)
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_user_permission", joinColumns = @JoinColumn(name = "user_id"),

    inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private List<PermissionEntity> permissions;


    public UserEntity() {
    }


    public UserEntity(String name, String email, String endereco, String cidade, String estado, String senha) {
        super();
        this.name = name;
        this.email = email;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<PermissionEntity> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<PermissionEntity> permissions) {
        this.permissions = permissions;
    }


}
