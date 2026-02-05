package database;
import objects.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    public void insertPet(Pet pet) {
        String sql = "INSERT INTO pet (name, type, age, gender, weight, currentCondition) VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            // Set parameters (? → actual values)
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getAge());
            statement.setString(4, String.valueOf(pet.getGender()));
            statement.setFloat(5, pet.getWeight());
            statement.setString(6, pet.getCurrentCondition());
            // Execute INSERT
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" pet inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Pet> getAllPets() {
        List<Pet> petList = new ArrayList<>();
        String sql = "SELECT * FROM pet ORDER BY id"; // Предполагаем, что таблица называется pets

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                // Извлекаем данные из каждой строки базы
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int age = resultSet.getInt("age");
                char gender = resultSet.getString("gender").charAt(0);
                float weight = resultSet.getFloat("weight");
                String condition = resultSet.getString("currentCondition");

                // Создаем объект Pet и добавляем в список
                Pet pet = new Pet(id, name, type, age, gender, weight, condition);
                petList.add(pet);
            }

            resultSet.close();
            statement.close();
            System.out.println("✅ Извлечено питомцев: " + petList.size());

        } catch (SQLException e) {
            System.out.println("❌ Ошибка получения списка питомцев!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return petList; // ВОЗВРАЩАЕМ список
    }

    public static boolean updatePet(Pet pet) {
        String sql = "UPDATE Pet SET name = ?, type = ?, " +
                "age = ?, gender = ?, weight = ?, currentCondition = ?" +
                "WHERE id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getAge());
            statement.setString(4, String.valueOf(pet.getGender()));
            statement.setFloat(5, pet.getWeight()); // WHERE condition
            statement.setString(5, pet.getCurrentCondition()); // WHERE condition
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated > 0) {
                System.out.println(" Pet updated: " + pet.getName());
                return true;
            }
        } catch (SQLException e) {
            System.out.println(" Update failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public static Pet getPetById(int id) {
        String sql = "SELECT * FROM pets WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Constructing the Pet object with all your specific fields
                    return new Pet(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("type"),
                            rs.getInt("age"),
                            rs.getString("gender").charAt(0), // Convert String to char
                            rs.getFloat("weight"),
                            rs.getString("currentCondition")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error finding pet: " + e.getMessage());
        }
        return null; // If no pet is found with that ID
    }

    public static boolean deletePet(int petId) {
        String sql = "DELETE FROM pet WHERE id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, petId);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            if (rowsDeleted > 0) {
                System.out.println(" Pet deleted (ID: " + petId + ")");
                return true;
            } else {
                System.out.println(" No pet found with ID: " + petId);
            }
        } catch (SQLException e) {
            System.out.println(" Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public List<Pet> searchByName(String name) {
        List<Pet> petList = new ArrayList<>();
        // ILIKE = case-insensitive
        // % = wildcard (matches any characters)
        String sql = "SELECT * FROM pet WHERE name ILIKE ? ORDER BY name";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return petList;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // Add wildcards!
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pet pet = extractPetFromResultSet(resultSet);
                if (pet != null) {
                    petList.add(pet);
                }
            }
            resultSet.close();
            statement.close();
            System.out.println(" Found " + petList.size() + " pet");
        } catch (SQLException e) {
            System.out.println(" Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return petList;
    }

    private Pet extractPetFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String type = resultSet.getString("type");
        int age = resultSet.getInt("age");
        char gender = resultSet.getString("gender").charAt(0);
        float weight = resultSet.getFloat("weight");
        String currentCondition = resultSet.getString("currentCondition");

        Pet pet = null;

        pet = new Pet(id, name, type, age, gender, weight, currentCondition);

        return pet;
    }
}