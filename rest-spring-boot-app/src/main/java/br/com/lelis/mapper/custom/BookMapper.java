package br.com.lelis.mapper.custom;

import br.com.lelis.data.vo.BookVO;
import br.com.lelis.model.Book;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookMapper {

    public BookVO convertEntityToVo(Book book){
        BookVO vo = new BookVO();
        vo.setKey(book.getId());
        vo.setTitle(book.getTitle());
        vo.setAuthor(book.getAuthor());
        vo.setLaunchDate(new Date());
        vo.setPrice(book.getPrice());

        return vo;
    }

    public Book convertVoToEntity(BookVO book){
        Book entity = new Book();
        entity.setId(book.getKey());
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setLaunchDate(new Date());

        return entity;
    }
}
