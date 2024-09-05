package br.com.lelis.services;

import br.com.lelis.controllers.BookController;
import br.com.lelis.data.vo.BookVO;
import br.com.lelis.exceptions.RequiredObjectIsNullException;
import br.com.lelis.exceptions.ResourceNotFoundException;
import br.com.lelis.mapper.DozerMapper;
import br.com.lelis.mapper.custom.BookMapper;
import br.com.lelis.model.Book;
import br.com.lelis.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {
    private Logger logger = Logger.getLogger(BookServices.class.getName());

    @Autowired
    BookRepository repository;

    @Autowired
    BookMapper mapper;

    public List<BookVO> findAll(){
        logger.info("Finding all books");

        var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
        books
                .stream()
                .forEach(b -> b.add(linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()));

        return books;
    }

    public BookVO findById(Long id) {

        logger.info("Finding one book!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        var vo = DozerMapper.parseObject(entity, BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return vo;
    }

    public BookVO create(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one book!");

        // to save in the database we must convert from PersonVO to Person, then convert it again to PersonVO, keeping it safer
        var entity = DozerMapper.parseObject(book, Book.class);
        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());


        return vo;
    }

    public BookVO update(BookVO book) {
        if (book == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one book!");


        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(book.getLaunchDate());

        var vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
        vo.add(linkTo(methodOn(BookController.class).findById(vo.getKey())).withSelfRel());


        return vo;
    }

    public void delete(Long id) {

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        repository.delete(entity);
    }

}
