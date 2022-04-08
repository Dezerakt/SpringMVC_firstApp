package ru.isaev.springcourse.DAO;

import org.springframework.stereotype.Component;
import ru.isaev.springcourse.Models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:postgresql://localhost:5432/SpringDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection connection;

    static{
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index(){
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while(resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id){
        //return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
        return null;
    }

    public void save(Person person){
        /*person.setId(++PEOPLE_COUNT);
        people.add(person);*/

        try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES (" + 1 + ", '" +
                    person.getName() + "'" + ", '" +
                    person.getAge() + "'" + ", '" +
                    person.getEmail() + "'" + ")";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Person person){
        /*Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());*/
    }

    public void delete(int id){
        /*Person personForRemove = show(id);
        people.remove(personForRemove);*/
        //people.removeIf(x -> x.getId() == id);
    }
}
