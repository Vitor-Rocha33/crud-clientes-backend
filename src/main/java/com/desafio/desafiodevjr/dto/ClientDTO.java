package com.desafio.desafiodevjr.dto;

import com.desafio.desafiodevjr.domain.Client;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 3, max = 100, message = "O tamanho deve ser entre 3 e 100 caracteres")
    @Pattern(regexp = "/[A-Z][a-z]* [A-Z][a-z]*/")
    private String name;

    public ClientDTO() {

    }

    public ClientDTO(Client obj) {
        this.id = obj.getId();
        this.name = obj.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
