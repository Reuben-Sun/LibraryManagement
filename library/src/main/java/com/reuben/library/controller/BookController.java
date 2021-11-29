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
    //获取完整书单
    @GetMapping("/bookList")
    public Map<String, Object> bookList(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        Map<String, Object> map = mapper.getBookList();
        sqlSession.close();
        return map;
    }

    //获取书单
//    @GetMapping("/bookList2")
//    public Map<String, Object> bookList2(){
//        //获取
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        //执行
//        BookDao mapper = sqlSession.getMapper(BookDao.class);
//
//        Map<String, Object> map = mapper.getBookList2();
//        sqlSession.close();
//        return map;
//    }
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
    public Map<String, Object> bookListById(int id){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        Map<String, Object> map = mapper.getBookById(id);
        sqlSession.close();
        return map;
    }

    //按名字模糊查询
    @GetMapping("/bookListByName")
    public Map<String, Object> bookListByName(String name){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        Map<String, Object> map = mapper.getBookByName(name);
        sqlSession.close();
        return map;
    }

    //插入一本书
    @GetMapping("/newBook")
    public int addNewBook(Integer id, String name, String author, String publisher, String version){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        int x = mapper.addBook(id, name, author, publisher, version);
//        int x = mapper.addBook(11, "hhh", "lll", "ooo", "qqq");
        sqlSession.commit();
        sqlSession.close();
        return x;
    }

    //删除一本书
    @GetMapping("/deleteBook")
    public int deleteBookById(Integer id){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        int x = mapper.deleteBookById(id);
        sqlSession.commit();
        sqlSession.close();
        return x;
    }

    //更新一本书
    @GetMapping("/updateBook")
    public int updateBookById(Integer id, String name, String author, String publisher, String version){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        int x = mapper.updateBook(id, name, author, publisher, version);
        sqlSession.commit();
        sqlSession.close();
        return x;
    }
}
