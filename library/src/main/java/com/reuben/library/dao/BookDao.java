package com.reuben.library.dao;

import com.reuben.library.pojo.Book;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface BookDao {
    List<Book> getBookList();
    List<Book> getBookById(int id);
    Map<String, Object> getBookList2();
    @MapKey("name")
    Map<String, Object> getBookNameList();
    Map<String, Object> getPersonList();
}
