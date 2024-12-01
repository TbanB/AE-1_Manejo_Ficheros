package com.concesionario.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coche {
    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
}
