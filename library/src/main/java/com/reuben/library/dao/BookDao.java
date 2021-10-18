package com.reuben.library.dao;

import com.reuben.library.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> getBookList();
}
