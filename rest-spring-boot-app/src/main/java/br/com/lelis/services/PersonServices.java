package br.com.lelis.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import br.com.lelis.data.vo.PersonVO;
import br.com.lelis.data.vo.v2.PersonVOV2;
import br.com.lelis.exceptions.ResourceNotFoundException;
import br.com.lelis.mapper.DozerMapper;
import br.com.lelis.mapper.custom.PersonMapper;
import br.com.lelis.model.Person;
import br.com.lelis.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonServices {


    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;
    public List<PersonVO> findAll() {

        logger.info("Finding all people!");

        // convert from Person to PersonVO using DozerMapper
        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id) {

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person) {

        logger.info("Creating one person!");

        // to save in the database we must convert from PersonVO to Person, then convert it again to PersonVO, keeping it safer
        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {

        logger.info("Creating one person with v2!");

        // to save in the database we must convert from PersonVO to Person, then convert it again to PersonVO, keeping it safer
        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo(repository.save(entity));

        return vo;
    }

    public PersonVO update(PersonVO person) {

        logger.info("Updating one person!");


        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;    }

    public void delete(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

}