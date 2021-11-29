package com.reuben.library.dao;

import com.reuben.library.pojo.Book;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface BookDao {
    @MapKey("name")
    Map<String, Object> getBookList();
    Map<String, Object> getBookById(int id);
    Map<String, Object> getBookList2();
    @MapKey("name")
    Map<String, Object> getBookNameList();
    Map<String, Object> getPersonList();
    int addBook(Integer id, String name, String author, String publisher, String version);
}
