package org.home.on.residencia;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_residencia")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class ResidenciaEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Size(min = 4, max = 120)
    @Column(name = "qtd_morador", length = 120, nullable = false)
    private int qtd_morador;



    public ResidenciaEntity() {
    }

    public ResidenciaEntity(int qtd_morador) {
        this.qtd_morador = qtd_morador;
    }

    public int getQtd_morador() {
        return qtd_morador;
    }

    public void setQtd_morador(int qtd_morador) {
        this.qtd_morador = qtd_morador;
    }
}
