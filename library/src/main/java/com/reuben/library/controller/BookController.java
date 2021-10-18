package com.reuben.library.controller;


import com.reuben.library.dao.BookDao;
import com.reuben.library.pojo.Book;
import com.reuben.library.utils.MybatisUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BookController {

    //@Autowired
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

    //@Autowired
    @GetMapping("/bookList")
    public Map<Integer, Object> bookList(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = mapper.getBookList();
        Map<Integer, Object> map = bookList.stream().collect(Collectors.toMap(Book::getId, Book->Book));
        //关闭
        sqlSession.close();
        return map;
    }



}
