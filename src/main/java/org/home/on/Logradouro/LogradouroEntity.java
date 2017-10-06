package org.home.on.Logradouro;

import org.home.on.utils.BaseEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_logradouro")
@AttributeOverride(name = "id", column = @Column(name = "pk_id"))
public class LogradouroEntity extends BaseEntity<Long> {

    @NotNull
    @Column(name = "cep", length = 120, nullable = false)
    private String Cep;

    @NotNull
    @Column(name = "", length = 120, nullable = false)
    private String Nome;

    @NotNull
    @Column(name = "uf", length = 4, nullable = false)
    private String Uf ;





}