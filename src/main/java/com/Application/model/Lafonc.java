package com.Application.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;

public class Lafonc {

    // Méthode pour enregistrer les ordres acceptés dans un fichier JSON
    public void enrgtreodreacc(Order orderacc) {
        try {

            // Fichier pour enregistrer les ordres acceptés
            File file = new File("C:\\Users\\lakbi\\Desktop\\data\\output.json");

            // Créer un JsonArray pour contenir les ordres
            JsonArray jsonArray = new JsonArray();

            // Convertir l'ordre en un JsonObject
            JsonObject orderObject = new JsonObject();
            orderObject.addProperty("id", orderacc.getId());
            orderObject.addProperty("date", orderacc.getDate().toString());
            orderObject.addProperty("amount", orderacc.getAmount().toString());
            orderObject.addProperty("customerId", orderacc.getCustomerId());

            // Ajouter l'objet Json au JsonArray
            jsonArray.add(orderObject);

            // Utiliser BufferedWriter pour écrire dans le fichier
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // Mode ajout
            writer.write(jsonArray.toString());
            writer.close();

            System.out.println("Fichier JSON créé : " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour enregistrer les ordres refusés dans un fichier JSON
    public void enrgtreodrerefus(Order ordrefus) {
        try {
            File file = new File("C:\\Users\\lakbi\\Desktop\\data\\error.json");
            // Créer un JsonArray pour contenir les ordres
            JsonArray jsonArray = new JsonArray();

            // Convertir l'ordre en un JsonObject
            JsonObject orderObject = new JsonObject();
            orderObject.addProperty("id", ordrefus.getId());
            orderObject.addProperty("date", ordrefus.getDate().toString());
            orderObject.addProperty("amount", ordrefus.getAmount().toString());
            orderObject.addProperty("customerId", ordrefus.getCustomerId());

            // Ajouter l'objet Json au JsonArray
            jsonArray.add(orderObject);

            // Utiliser BufferedWriter pour écrire dans le fichier
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true)); // Mode ajout
            writer.write(jsonArray.toString());
            writer.close();

            System.out.println("Fichier JSON créé : " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        }


}
