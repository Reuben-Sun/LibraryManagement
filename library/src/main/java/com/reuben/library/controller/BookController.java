package com.reuben.library.controller;


import com.reuben.library.dao.BookDao;
import com.reuben.library.pojo.Book;
import com.reuben.library.pojo.Person;
import com.reuben.library.utils.MybatisUtils;
import com.sun.org.apache.xerces.internal.xs.StringList;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.LoggerFactory;
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
@Log4j
public class BookController {

    //@Autowired
    @GetMapping("/hello")
    public String sayHello(){
        return "hello world";
    }

    //@Autowired
    //获取书单
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

    //获取书单
    @GetMapping("/bookList2")
    public Map<String, Object> bookList2(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        Map<String, Object> map = mapper.getBookList2();
        sqlSession.close();
        return map;
    }
    //获取书名单
    @GetMapping("/bookNameList")
    public Map<String, Object> bookNameList(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        Map<String, Object> map = mapper.getBookNameList();
        sqlSession.close();
        return map;
    }

    //按ID查询
    @GetMapping("/bookListById")
    public Map<Integer, Object> bookListById(int id){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        List<Book> bookList = mapper.getBookById(id);
        Map<Integer, Object> map = bookList.stream().collect(Collectors.toMap(Book::getId, Book->Book));
        //关闭
        sqlSession.close();
        return map;
    }

}
