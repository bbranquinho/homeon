package org.home.on.arduino;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.home.on.utils.BaseEntity;

@Entity
@Table(name = "tb_agenda")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class Agenda extends BaseEntity<Long>{

	 private static final long serialVersionUID = 201602010251L;
	 
    @NotNull
    private Long id;

    @NotNull
    @Column(name = "hostname", length = 45, nullable = false)
    private String hostname;

    @NotNull
    @Column(name = "comando", length = 6, nullable = false)
    private String comando;

    @NotNull
    @Column(name = "data", nullable = false)
    private Date data;

    @NotNull
    @Column(name = "repetir", nullable = false)
    private boolean repetir;

    public Agenda(Long id, String hostname, String comando, Date data, boolean repetir) {
        this.id = id;
        this.hostname = hostname;
        this.comando = comando;
        this.data = data;
        this.repetir = repetir;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public boolean isRepetir() {
        return repetir;
    }

    public void setRepetir(boolean repetir) {
        this.repetir = repetir;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
