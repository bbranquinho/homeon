package org.home.on.estado;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_estado")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class EstadoEntity extends BaseEntity<Long> {

    @NotNull
    @Column(name = "pais", length = 120, nullable = false)
    private String Pais;

    @NotNull
    @Column(name = "nome", length = 120, nullable = false)
    private String Nome;

    @NotNull
    @Column(name = "uf", length = 4, nullable = false)
    private String Uf ;

    public EstadoEntity() {
    }

    public EstadoEntity(String pais , String nome, String uf) {
        Pais  = pais;
        Nome  = nome;
        Uf = uf;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getUf() {
        return Uf;
    }

    public void setUf(String uf) {
        Uf = uf;
    }


}