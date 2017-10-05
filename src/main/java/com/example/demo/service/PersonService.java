package com.example.demo.service;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonService {
  void add(Person person);
  Person getById(int id);
  List<Person> get();
  void update(Person person);
  void delete(int id);
  void add(List<Person> people);
}
