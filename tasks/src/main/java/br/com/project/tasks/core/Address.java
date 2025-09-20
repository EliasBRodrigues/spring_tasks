package br.com.project.tasks.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Address {
    @JsonProperty("cep")
    private String zipCode;

    @JsonProperty("logradouro")
    private String stret;

    @JsonProperty("complemento")
    private String complement;

    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localildade")
    private String city;

    @JsonProperty("estado")
    private String state;
}
