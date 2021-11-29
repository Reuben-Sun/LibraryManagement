package com.reuben.library.controller;


import com.reuben.library.dao.BookDao;
import com.reuben.library.pojo.Book;
import com.reuben.library.utils.MybatisUtils;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Log4j
public class BookController {

    //@Autowired
    @GetMapping("/hello")
    public String sayHello() {
        return "hello world";
    }

    //@Autowired
    //获取完整书单
    @GetMapping("/bookList")
    public List<Book> bookList() {
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        List<Book> result = mapper.getBookList();
        sqlSession.close();
        return result;
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
//    @GetMapping("/bookNameList")
//    public Map<String, Object> bookNameList(){
//        //获取
//        SqlSession sqlSession = MybatisUtils.getSqlSession();
//        //执行
//        BookDao mapper = sqlSession.getMapper(BookDao.class);
//
//        Map<String, Object> map = mapper.getBookNameList();
//        sqlSession.close();
//        return map;
//    }

    //按ID查询
    @GetMapping("/bookListById")
    public Book bookListById(int id) {
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        Book result = mapper.getBookById(id);
        sqlSession.close();
        return result;
    }

    //按名字模糊查询
    @GetMapping("/bookListByName")
    public List<Book> bookListByName(String name) {
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);
        List<Book> map = mapper.getBookByName(name);
        sqlSession.close();
        return map;
    }

    //插入一本书
    @GetMapping("/newBook")
    public int addNewBook(String name, String author, String publisher, String version) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        int x = mapper.addBook( name, author, publisher, version);
//        int x = mapper.addBook(11, "hhh", "lll", "ooo", "qqq");
        sqlSession.commit();
        sqlSession.close();
        return x;
    }

    //删除一本书
    @GetMapping("/deleteBook")
    public int deleteBookById(Integer id) {
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
    public int updateBookById(Integer id, String name, String author, String publisher, String version) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BookDao mapper = sqlSession.getMapper(BookDao.class);

        int x = mapper.updateBook(id, name, author, publisher, version);
        sqlSession.commit();
        sqlSession.close();
        return x;
    }
}
