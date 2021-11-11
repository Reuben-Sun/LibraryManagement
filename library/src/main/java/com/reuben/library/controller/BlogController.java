package com.reuben.library.controller;

import com.reuben.library.dao.BlogDao;
import com.reuben.library.dao.BookDao;
import com.reuben.library.pojo.Blog;
import com.reuben.library.pojo.Book;
import com.reuben.library.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BlogController {
    @GetMapping("/blogTest")
    public String blogTest(){
        return "welcome blog";
    }

    @GetMapping("/blogList")
    public Map<Integer, Object> blogList(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        BlogDao mapper = sqlSession.getMapper(BlogDao.class);
        List<Blog> blogList = mapper.getBlogList();
        Map<Integer, Object> map = blogList.stream().collect(Collectors.toMap(Blog::getId, Blog->Blog));
        //关闭
        sqlSession.close();
        return map;
    }
}
