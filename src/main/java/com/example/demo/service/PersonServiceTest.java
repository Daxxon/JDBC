package com.example.demo.service;

import com.example.demo.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {

  @Autowired
  PersonService personService;

  @Test
  public void testAddGet() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Person person1 = new Person();
    person1.setFirstName(firstName);
    person1.setLastName(lastName);
    personService.add(person1);

    List<Person> people = personService.get();

    Person person2 = findInList(people, firstName, lastName);
    Assert.assertNotNull(person2);

    Person person3 = personService.getById(person2.getId());
    Assert.assertNotNull(person3);
    Assert.assertEquals(firstName, person3.getFirstName());
    Assert.assertEquals(lastName, person3.getLastName());
  }

  @Test
  public void testUpdate() {
    Person person1 = createTestPerson();
    personService.add(person1);

    List<Person> people = personService.get();

    Person person2 = findInList(people, person1.getFirstName(), person1.getLastName());
    Assert.assertNotNull(person2);

    String updateFirstName = Long.toString(System.currentTimeMillis());
    String updateLastName = Long.toString(System.currentTimeMillis());

    person2.setFirstName(updateFirstName);
    person2.setLastName(updateLastName);
    personService.update(person2);

    people = personService.get();

    Person person3 = findInList(people, updateFirstName, updateLastName);
    Assert.assertNotNull(person3);
    Assert.assertEquals(person2.getId(), person3.getId());
  }

  @Test
  public void testDelete() {
    Person person1 = createTestPerson();
    personService.add(person1);

    List<Person> people = personService.get();

    Person person2 = findInList(people, person1.getFirstName(), person1.getLastName());
    Assert.assertNotNull(person2);

    personService.delete(person2.getId());

    people = personService.get();
    Person person3 = findInList(people, person1.getFirstName(), person1.getLastName());
    Assert.assertNull(person3);
  }

  private Person createTestPerson() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Person person = new Person();
    person.setFirstName(firstName);
    person.setLastName(lastName);
    return person;
  }


  private Person findInList(List<Person> people, String first, String last) {
    // Find the new person in the list
    for (Person person : people) {
      if (person.getFirstName().equals(first) && person.getLastName().equals(last)) {
        return person;
      }
    }
    return null;
  }
}
