package org.home.on.residencia;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_residencia")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class ResidenciaEntity extends BaseEntity<Long> {

    private static final long serialVersionUID = 201602010251L;

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getQtdmorador() {
		return qtdmorador;
	}

	public void setQtdmorador(int qtdmorador) {
		this.qtdmorador = qtdmorador;
	}

	public ResidenciaEntity() {
		super();
	}

	public ResidenciaEntity(String estado, String cidade, String bairro, String rua, int numero, int qtdmorador) {
		super();
		this.estado = estado;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.qtdmorador = qtdmorador;
	}

	@NotNull
    @Column(name = "estado", length = 2, nullable = false)
    private String estado;

    @NotNull
    @Column(name = "cidade", length = 45, nullable = false)
    private String cidade;
    
    @NotNull
    @Column(name = "bairro", length = 50, nullable = false)
    private String bairro;

    @NotNull
    @Column(name = "rua", length = 80, nullable = false)
    private String rua;

    @NotNull
    @Column(name = "numero", length = 30, nullable = false)
    private int numero;

    @NotNull
    @Column(name = "qtdmorador", length = 120, nullable = false)
    private int qtdmorador;

}
