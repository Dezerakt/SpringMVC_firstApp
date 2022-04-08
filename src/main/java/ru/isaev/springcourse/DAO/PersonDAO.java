package ru.isaev.springcourse.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.isaev.springcourse.Models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index(){
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        /*try {
            Statement statement = connection.createStatement();
            String SQL = "INSERT INTO person VALUES (" + 1 + ", '" +
                    person.getName() + "'" + ", '" +
                    person.getAge() + "'" + ", '" +
                    person.getEmail() + "'" + ")";
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

       jdbcTemplate.update("INSERT INTO person VALUES (1, ?, ?, ?)",
               person.getName(),
               person.getAge(),
               person.getEmail());
    }

    public void update(int id, Person person){
        /*try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET name=?, age=?, email=? WHERE id=?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=? WHERE id=?",
                person.getName(),
                person.getAge(),
                person.getEmail(),
                id);

    }

    public void delete(int id){
       /* try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        jdbcTemplate.update("DELETE FROM person WHERE id=?",
                id);

    }
}
