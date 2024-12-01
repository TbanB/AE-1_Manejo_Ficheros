package com.concesionario.dao;

import com.concesionario.models.Coche;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CocheDAO {
    private static CocheDAO instance;
    private List<Coche> coches;
    private static final String DATA_FILE = "src/main/resources/coches.dat";

    private CocheDAO() {
        this.coches = new ArrayList<>();
        getData();
    }

    public static synchronized CocheDAO getInstance() {
        if (instance == null) {
            instance = new CocheDAO();
        }
        return instance;
    }

    public boolean addCar(Coche coche) {
        if (coches.stream().anyMatch(c -> c.getId() == coche.getId() || c.getMatricula().equals(coche.getMatricula()))) {
            return false;
        }
        coches.add(coche);
        return true;
    }

    public boolean removeCarById(int id) {
        return coches.removeIf(coche -> coche.getId() == id);
    }

    public Coche getCarById(int id) {
        return coches.stream().filter(coche -> coche.getId() == id).findFirst().orElse(null);
    }

    public List<Coche> getAllCars() {
        return new ArrayList<>(coches);
    }

    private void getData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No se encontró el archivo de datos. Iniciando una lista vacía.");
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            coches = (List<Coche>) ois.readObject();
            System.out.println("Datos cargados correctamente desde " + DATA_FILE);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(coches);
            System.out.println("Datos guardados correctamente en " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
