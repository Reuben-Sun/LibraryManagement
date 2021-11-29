package com.reuben.library.dao;

import com.reuben.library.pojo.Book;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface BookDao {
//    @MapKey("name")
    List<Book> getBookList();
//    @MapKey("name")
    List<Book>  getBookByName(String name);
    Book getBookById(int id);
    //Map<String, Object> getBookList2();
//    @MapKey("name")
    List<Book> getBookNameList();
//    Map<String, Object> getPersonList();
    int addBook(String name, String author, String publisher, String version);
    int deleteBookById(Integer id);
    int updateBook(Integer id, String name, String author, String publisher, String version);
}
