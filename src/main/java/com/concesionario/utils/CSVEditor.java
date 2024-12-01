package com.concesionario.utils;

import com.concesionario.models.Coche;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVEditor {

    private static final String CSV_FILE = "src/main/resources/coches.csv";

    public static String exportToCSV(List<Coche> coches) {
        try (FileWriter writer = new FileWriter(CSV_FILE)) {
            writer.append("ID,Matrícula,Marca,Modelo,Color\n");

            for (Coche coche : coches) {
                writer.append(String.valueOf(coche.getId())).append(",")
                        .append(coche.getMatricula()).append(",")
                        .append(coche.getMarca()).append(",")
                        .append(coche.getModelo()).append(",")
                        .append(coche.getColor()).append("\n");
            }

            return "Datos correctamente: " + CSV_FILE;
        } catch (IOException e) {
            return "Error al exportar a CSV: " + e.getMessage();
        }
    }
}
