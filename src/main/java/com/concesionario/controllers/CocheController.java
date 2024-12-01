package com.concesionario.controllers;

import com.concesionario.dao.CocheDAO;
import com.concesionario.models.Coche;
import com.concesionario.utils.CSVEditor;

import java.util.List;

public class CocheController {
    private final CocheDAO cocheDAO;

    public CocheController() {
        this.cocheDAO = CocheDAO.getInstance();
    }

    public String addCar(int id, String matricula, String marca, String modelo, String color) {
        if (id <= 0) {
            return "Error: El ID debe ser un número positivo.";
        }
        if (matricula == null || matricula.isEmpty()) {
            return "Error: La matrícula no puede estar vacía.";
        }
        if (marca == null || marca.isEmpty()) {
            return "Error: La marca no puede estar vacía.";
        }
        if (modelo == null || modelo.isEmpty()) {
            return "Error: El modelo no puede estar vacío.";
        }
        if (color == null || color.isEmpty()) {
            return "Error: El color no puede estar vacío.";
        }

        Coche coche = new Coche(id, matricula, marca, modelo, color);
        if (cocheDAO.addCar(coche)) {
            return "Coche añadido correctamente.";
        } else {
            return "Error: Ya existe un coche con el mismo ID o matrícula.";
        }
    }

    public String removeCarById(int id) {
        if (id <= 0) {
            return "Error: El ID debe ser un número positivo.";
        }
        if (cocheDAO.removeCarById(id)) {
            return "Coche eliminado correctamente.";
        } else {
            return "Error: No se encontró ningún coche con el ID especificado.";
        }
    }

    public String getCarById(int id) {
        if (id <= 0) {
            return "Error: El ID debe ser un número positivo.";
        }
        Coche coche = cocheDAO.getCarById(id);
        if (coche != null) {
            return coche.toString();
        } else {
            return "Error: No se encontró ningún coche con el ID especificado.";
        }
    }

    public String getAllCars() {
        List<Coche> coches = cocheDAO.getAllCars();
        if (coches.isEmpty()) {
            return "No hay coches registrados.";
        }

        StringBuilder result = new StringBuilder("Lista de coches:\n");
        for (Coche coche : coches) {
            result.append(coche).append("\n");
        }
        return result.toString();
    }

    public String saveData() {
        try {
            cocheDAO.saveData();
            return "Datos guardados correctamente.";
        } catch (Exception e) {
            return "Error al guardar los datos: " + e.getMessage();
        }
    }

    public String exportCarsToCSV() {
        List<Coche> coches = cocheDAO.getAllCars();
        if (coches.isEmpty()) {
            return "No hay coches registrados para exportar.";
        }
        return CSVEditor.exportToCSV(coches);
    }
}
