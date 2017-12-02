package org.home.on.arduino;

import java.util.Date;

public class Agenda {

    private Long id;

    private String hostname;

    private String comando;

    private Date data;

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
