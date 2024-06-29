package br.com.lelis.mapper.custom;

import br.com.lelis.data.vo.v2.PersonVOV2;
import br.com.lelis.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonVOV2 convertEntityToVo(Person person){
        PersonVOV2 vo = new PersonVOV2();
        vo.setId(person.getId());
        vo.setFirstName(person.getFirstName());
        vo.setLastName(person.getFirstName());
        vo.setBirthDay(new Date());
        vo.setAddress(person.getAddress());
        vo.setGender(person.getGender());

        return vo;
    }

    public Person convertVoToEntity(PersonVOV2 person){
        Person entity = new Person();
        entity.setId(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getFirstName());
        // didnt add birthDate into de database, so it wont work
        // entity.setBirthDay(new Date());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return entity;
    }
}
