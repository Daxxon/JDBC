package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PersonRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String INSERT_SQL = "INSERT INTO person (firstName, lastName) VALUES (?,?)";
  @Override
  public void add(Person person) {
    jdbcTemplate.update(INSERT_SQL, person.getFirstName(), person.getLastName());
  }

  private final String SELECT_BY_ID_SQL = "SELECT * FROM person WHERE id = ?";
  @Override
  public Person getById(int id) {
    return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new PersonRepositoryImpl.PersonMapper(), id);
  }

  private final String SELECT_SQL = "SELECT * FROM person";
  @Override
  public List<Person> get() {
    return jdbcTemplate.query(SELECT_SQL, new PersonRepositoryImpl.PersonMapper());
  }

  private final String UPDATE_SQL = "UPDATE person SET firstName=?, lastName=? where id=?";
  @Override
  public void update(Person person) {
    jdbcTemplate.update(UPDATE_SQL, person.getFirstName(), person.getLastName(), person.getId());
  }

  private final String DELETE_SQL = "DELETE FROM person WHERE id=?";
  @Override
  public void delete(int id) {
    jdbcTemplate.update(DELETE_SQL, id);
  }

  @Override
  public void add(List<Person> people) {
    for (Person person : people) {
      personRepository.add(person);
    }
  }

    // Functional programming alternative:
    //people.stream().forEach(e -> personRepository.add(e));

  // other methods left out for brevity
}