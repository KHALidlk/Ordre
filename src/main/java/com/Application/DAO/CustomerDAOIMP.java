package com.Application.DAO;
import com.Application.model.Connecter;
import com.Application.model.Customer;
import java.lang.ref.PhantomReference;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class CustomerDAOIMP implements CustomerDAO {
    Connecter conn=new Connecter();
    boolean existe=false;
    public boolean sellect_Customer(int id) {
        String SQL = "SELECT id FROM customer WHERE id = ?";
        try (Connection connection = conn.connx();
             PreparedStatement stmt = connection.prepareStatement(SQL)) {

            // Définir le paramètre dans la requête
            stmt.setInt(1, id);

            // Exécuter la requête après avoir défini les paramètres
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    existe = true; // Si un résultat existe, le client est trouvé
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

}
