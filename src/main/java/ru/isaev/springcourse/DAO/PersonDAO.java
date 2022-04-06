package ru.isaev.springcourse.DAO;

import org.springframework.stereotype.Component;
import ru.isaev.springcourse.Models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Person> people;
    private static int PEOPLE_COUNT;

    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "John"));
        people.add(new Person(++PEOPLE_COUNT, "Bob"));
        people.add(new Person(++PEOPLE_COUNT, "Smith"));
        people.add(new Person(++PEOPLE_COUNT, "Sam"));
    }

    public List<Person> index(){
        return people;
    }

    public Person show(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person){
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
    }

    public void delete(int id){
        /*Person personForRemove = show(id);
        people.remove(personForRemove);*/
        people.removeIf(x -> x.getId() == id);
    }
}
