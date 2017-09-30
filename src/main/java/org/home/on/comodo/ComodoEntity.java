package org.home.on.comodo;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_comodo")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class ComodoEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Size(min = 4, max = 120)
    @Column(name = "nome", length = 120, nullable = false)
    private int nome;

    @NotNull
    @Column(name = "modulos_presentes", length = 120, nullable = false)
    private int modulos_presentes;

    public int getNome() {
        return nome;
    }

    public void setNome(int nome) {
        this.nome = nome;
    }

    public int getModulos_presentes() {
        return modulos_presentes;
    }

    public void setModulos_presentes(int modulos_presentes) {
        this.modulos_presentes = modulos_presentes;
    }

    public ComodoEntity() {
    }

    public ComodoEntity(int nome, int modulos_presentes) {
        this.nome = nome;
        this.modulos_presentes = modulos_presentes;
    }
}
