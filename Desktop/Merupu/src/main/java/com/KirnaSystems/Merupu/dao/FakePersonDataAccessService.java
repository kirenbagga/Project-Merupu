package com.KirnaSystems.Merupu.dao;

import com.KirnaSystems.Merupu.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{

    private static List<Person> db=new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        db.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPeople() {
        return db;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return db.stream().filter(person -> person.getId().equals(person))
                .findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> personMayBe=selectPersonById(id);
        if(personMayBe.isEmpty()){
            return 0;
        }
        db.remove(personMayBe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p->{int indexOfPersonToDelete =db.indexOf(person);
                if(indexOfPersonToDelete>=0){
                db.set(indexOfPersonToDelete, person);
                return 1;}
                return  0;}).orElse(0);
    }


}
