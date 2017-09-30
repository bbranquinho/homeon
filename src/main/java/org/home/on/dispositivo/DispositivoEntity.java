package org.home.on.dispositivo;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_dispositivo")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class DispositivoEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;


    @NotNull
    @Size(min = 4, max = 120)
    @Column(name = "funcao", length = 120, nullable = false)
    private int funcao;

    @NotNull
    @Column(name = "estado", length = 120, nullable = false)
    private int estado;

    public DispositivoEntity() {
    }

    public DispositivoEntity(int funcao, int estado) {
        this.funcao = funcao;
        this.estado = estado;
    }

    public int getFuncao() {
        return funcao;
    }

    public void setFuncao(int funcao) {
        this.funcao = funcao;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
}
