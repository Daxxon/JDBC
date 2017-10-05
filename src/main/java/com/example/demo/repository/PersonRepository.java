package com.example.demo.repository;

import com.example.demo.model.Person;

import java.util.List;

public interface PersonRepository {
  void add(Person person);
  Person getById(int id);
  List<Person> get();
  void update(Person person);
  void delete(int id);
}