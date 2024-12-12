package com.Application.model;

import java.util.ArrayList;

public class Customer {
    private int id;         // Identifiant unique du client
    private String nom;     // Nom du client
    private String email;   // Adresse email du client
    private String phone;// Numéro de téléphone du client
    private ArrayList<Order> orders; // Liste des commandes associées
    // Constructeur par défaut
    public Customer() {
        this.orders = new ArrayList<>(); // Initialisation de la liste
    }
}
