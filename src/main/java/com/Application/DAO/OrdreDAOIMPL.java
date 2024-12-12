package com.Application.DAO;

import com.Application.model.Connecter;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class OrdreDAOIMPL implements OrdreDAO {
    Connecter conn = new Connecter();

    @Override
    public void ajouter_ordr(java.util.Date date, BigDecimal amount, int customerId) {
        // Entourer le nom de la table par des backticks
        String SQL = "INSERT INTO `order` (`date`, `amount`, `customer_id`) VALUES (?, ?, ?)";

        try (Connection connection = conn.connx();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            // Conversion explicite de java.util.Date en java.sql.Date
            stmt.setDate(1, new Date(date.getTime()));

            // Ajouter les autres paramètres
            stmt.setBigDecimal(2, amount);
            stmt.setInt(3, customerId);

            // Exécuter la requête
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Afficher l'erreur
            e.printStackTrace();
        }
    }
}
