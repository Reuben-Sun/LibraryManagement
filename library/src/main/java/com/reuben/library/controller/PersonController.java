package com.reuben.library.controller;

import com.reuben.library.dao.BookDao;
import com.reuben.library.dao.PersonDao;
import com.reuben.library.pojo.Book;
import com.reuben.library.pojo.Person;
import com.reuben.library.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    @GetMapping("/personList")
    public Map<String, Object> personList(){
        //获取
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //执行
        PersonDao mapper = sqlSession.getMapper(PersonDao.class);

        Map<String, Object> map = mapper.getPersonList();
        sqlSession.close();
        return map;
    }
}
