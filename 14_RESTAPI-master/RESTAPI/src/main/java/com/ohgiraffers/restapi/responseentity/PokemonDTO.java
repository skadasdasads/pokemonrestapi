package com.ohgiraffers.restapi.responseentity;

import java.time.LocalDate;

public class PokemonDTO {

    private int no;
    private String name;
    private int type;
    private int property;

    public PokemonDTO() {

    }

    public PokemonDTO(int no, String name, int type, int property) {
        this.no = no;
        this.name = name;
        this.type = type;
        this.property = property;
    }

    public PokemonDTO(int i, String pokemon01, String test03, String 피카츄, LocalDate now) {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "PokemonDTO{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", property=" + property +
                '}';
    }
}