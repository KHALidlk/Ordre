package com.Application;

import com.Application.DAO.CustomerDAOIMP;
import com.Application.DAO.OrdreDAOIMPL;
import com.Application.model.Lafonc;
import com.Application.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ParseJson {

    Lafonc wrt = new Lafonc();
    CustomerDAOIMP customerDAOIMP = new CustomerDAOIMP();
    OrdreDAOIMPL ordreDAOIMPL = new OrdreDAOIMPL();
    List<Order> orders = new ArrayList<>();

    ObjectMapper objectMapper = new ObjectMapper();

    // Méthode pour lire les ordres depuis le fichier JSON
    public List<Order> getordre() {
        objectMapper.registerModule(new JavaTimeModule());

        // Utiliser BufferedReader pour lire le fichier JSON
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\lakbi\\Desktop\\data\\input.json"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
            return null;
        }

        try {
            // Définir le type pour une liste d'objets Order
            CollectionType listType = objectMapper.getTypeFactory()
                    .constructCollectionType(List.class, Order.class);

            // Convertir le contenu du fichier JSON en liste d'objets Order
            orders = objectMapper.readValue(content.toString(), listType);
        } catch (Exception e) {
            System.err.println("Erreur lors de la conversion du fichier JSON : " + e.getMessage());
        }
        return orders;
    }

    // Méthode pour traiter les ordres et les ajouter ou refuser selon la logique
    public void ordreAG() {
        if (orders == null || orders.isEmpty()) {
            orders = getordre(); // Charger les ordres si non initialisés
            if (orders == null || orders.isEmpty()) {
                System.out.println("La liste des ordres est vide ou nulle.");
                return;
            }
        }

        for (Order ordre : orders) {
            LocalDateTime localDateTime = ordre.getDate();
            Date sqlDate = Date.valueOf(localDateTime.toLocalDate());

            BigDecimal amount = ordre.getAmount();
            int customerId = ordre.getCustomerId();
            if (customerId == -1) {
                break;  // Enregistrer l'ordre refusé
            }
            boolean exist = customerDAOIMP.sellect_Customer(customerId);
            if (exist==false) {
                wrt.enrgtreodrerefus(ordre);
            }else if (exist==true) {
                wrt.enrgtreodreacc(ordre); // Enregistrer l'ordre accepté
                ordreDAOIMPL.ajouter_ordr(sqlDate, amount, customerId);
            }
        }
        System.out.println("Traitement des ordres terminé.");
    }

    // Méthode pour exécuter le processus toutes les heures
    public void startScheduledTask() {
        // Créer un ScheduledExecutorService avec un seul thread
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // Planifier la tâche pour qu'elle s'exécute toutes les heures (3600 secondes)
        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("Démarrage du traitement des ordres...");
                ordreAG(); // Appel à la méthode pour traiter les ordres
            } catch (Exception e) {
                System.err.println("Erreur lors du traitement des ordres : " + e.getMessage());
            }
        }, 0, 1, TimeUnit.HOURS); // 0 = délai avant la première exécution, 1 = intervalle entre les exécutions, TimeUnit.HOURS = période d'une heure
    }


}
