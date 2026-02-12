package database;

import objects.Person;
import objects.Owner;
import objects.Veterinarian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonDAO - Enhanced CRUD operations
 * - CREATE (INSERT) ✓
 * - READ (SELECT) ✓
 * - UPDATE ✓
 * - DELETE ✓
 * - SEARCH by Name & Age ✓
 */
public class PersonDAO {

    // ========================================
    // CREATE - INSERT OPERATIONS
    // ========================================

    /**
     * INSERT Owner into database
     */
    public boolean insertOwner(Owner owner) {
        String sql = "INSERT INTO people (first_name, last_name, phone, age, person_type, pet_name, specialization, experience_years, available) " +
                "VALUES (?, ?, ?, ?, 'OWNER', ?, NULL, NULL, NULL)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getPhone());
            statement.setInt(4, owner.getAge()); // Предполагая наличие getAge() в Person
            statement.setString(5, owner.getPetName());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Owner inserted: " + owner.getFullName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Owner failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    /**
     * INSERT Veterinarian into database
     */
    public boolean insertVeterinarian(Veterinarian vet) {
        String sql = "INSERT INTO people (first_name, last_name, phone, age, person_type, pet_name, specialization, experience_years, available) " +
                "VALUES (?, ?, ?, ?, 'VETERINARIAN', NULL, ?, ?, ?)";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getFirstName());
            statement.setString(2, vet.getLastName());
            statement.setString(3, vet.getPhone());
            statement.setInt(4, vet.getAge());
            statement.setString(5, vet.getSpecialization());
            statement.setInt(6, vet.getExperienceYears());
            statement.setBoolean(7, vet.isAvailable());

            int rowsInserted = statement.executeUpdate();
            statement.close();

            if (rowsInserted > 0) {
                System.out.println("✅ Veterinarian inserted: " + vet.getFullName());
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ Insert Veterinarian failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // ========================================
    // READ - SELECT OPERATIONS
    // ========================================

    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM people ORDER BY person_id";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return personList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = extractPersonFromResultSet(resultSet);
                if (person != null) personList.add(person);
            }

            resultSet.close();
            statement.close();
            System.out.println("✅ Retrieved " + personList.size() + " people from database");

        } catch (SQLException e) {
            System.out.println("❌ Select all people failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return personList;
    }

    public Person getPersonById(int personId)
    {
        String sql = "SELECT * FROM people WHERE person_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return null;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Person person = extractPersonFromResultSet(resultSet);
                statement.close();
                return person;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return null;
    }

    // ========================================
    // UPDATE OPERATIONS
    // ========================================

    public boolean updateOwner(Owner owner) {
        String sql = "UPDATE people SET first_name = ?, last_name = ?, phone = ?, age = ?, pet_name = ? " +
                "WHERE person_id = ? AND person_type = 'OWNER'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, owner.getFirstName());
            statement.setString(2, owner.getLastName());
            statement.setString(3, owner.getPhone());
            statement.setInt(4, owner.getAge());
            statement.setString(5, owner.getPetName());
            statement.setInt(6, owner.getPersonId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Owner updated: " + owner.getFullName());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public boolean updateVeterinarian(Veterinarian vet) {
        String sql = "UPDATE people SET first_name = ?, last_name = ?, phone = ?, age = ?, specialization = ?, experience_years = ?, available = ? " +
                "WHERE person_id = ? AND person_type = 'VETERINARIAN'";

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, vet.getFirstName());
            statement.setString(2, vet.getLastName());
            statement.setString(3, vet.getPhone());
            statement.setInt(4, vet.getAge());
            statement.setString(5, vet.getSpecialization());
            statement.setInt(6, vet.getExperienceYears());
            statement.setBoolean(7, vet.isAvailable());
            statement.setInt(8, vet.getPersonId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Veterinarian updated: " + vet.getFullName());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    public void displayAllPeople() {
        // Получаем список всех людей из БД (используя ранее созданный метод)
        List<Person> personList = getAllPeople();

        System.out.println("\n========================================");
        System.out.println("   ВСЕ ЛЮДИ ИЗ БАЗЫ ДАННЫХ");
        System.out.println("========================================");

        if (personList.isEmpty()) {
            System.out.println("В базе данных нет записей.");
        } else {
            for (int i = 0; i < personList.size(); i++) {
                Person p = personList.get(i);

                // Нумерация
                System.out.print((i + 1) + ". ");

                // Определяем "Роль" для вывода в скобках, как в примере
                String role = "";
                if (p instanceof Owner) {
                    role = "OWNER";
                } else if (p instanceof Veterinarian) {
                    role = "VET";
                }

                System.out.print("[" + role + "] ");

                // Выводим информацию через toString()
                System.out.println(p.toString());
            }
        }

        System.out.println("========================================\n");
    }

    // ========================================
    // DELETE OPERATION
    // ========================================

    public boolean deletePerson(int personId) {
        String sql = "DELETE FROM people WHERE person_id = ?";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, personId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("✅ Person deleted (ID: " + personId + ")");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return false;
    }

    // ========================================
    // SEARCH METHODS
    // ========================================

    public List<Person> searchByName(String name) {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE first_name ILIKE ? OR last_name ILIKE ?";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%");
            statement.setString(2, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(extractPersonFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
        return list;
    }

    public List<Person> searchByAgeRange(int minAge, int maxAge) {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE age BETWEEN ? AND ? ORDER BY age ASC";
        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return personList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            statement.setInt(2, maxAge);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = extractPersonFromResultSet(resultSet);
                if (person != null) {
                    personList.add(person);
                }
            }

            resultSet.close();
            statement.close();

            System.out.println("✅ Найдено " + personList.size() + " чел. в возрасте от " + minAge + " до " + maxAge);

        } catch (SQLException e) {
            System.out.println("❌ Ошибка при поиске по возрасту!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return personList;
    }

    public List<Person> searchByMinAge(int minAge) throws SQLException {
        List<Person> personList = new ArrayList<>();
        String sql = "SELECT * FROM people WHERE age >= ?";
        Connection connection = DatabaseConnection.getConnection();
        if(connection == null) return personList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, minAge);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Person person = extractPersonFromResultSet(resultSet);
                if (person != null) {
                    personList.add(person);
                }
            }
        } catch (SQLException e) {
            System.out.println("❌ Ошибка при поиске по минимальному возрасту!");
            e.printStackTrace();
        }
        return personList;
    }

    // ========================================
    // HELPER METHOD
    // ========================================

    private Person extractPersonFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("person_id");
        String fName = resultSet.getString("first_name");
        String lName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        int age = resultSet.getInt("age");
        String type = resultSet.getString("person_type");

        if ("OWNER".equals(type)) {
            String petName = resultSet.getString("pet_name");
            return new Owner(id, fName, lName, phone, age, petName);
        } else if ("VETERINARIAN".equals(type)) {
            String spec = resultSet.getString("specialization");
            int exp = resultSet.getInt("experience_years");
            boolean avail = resultSet.getBoolean("available");
            return new Veterinarian(id, fName, lName, phone, age, spec, exp, avail);
        }
        return null;
    }

    // ========================================
    // DISPLAY & POLYMORPHISM
    // ========================================

    public void demonstratePolymorphism() {
        List<Person> people = getAllPeople();
        System.out.println("\n--- POLYMORPHISM IN ACTION ---");
        for (Person p : people) {
            p.work(); // Вызовет либо метод Owner, либо Veterinarian
        }
    }
}