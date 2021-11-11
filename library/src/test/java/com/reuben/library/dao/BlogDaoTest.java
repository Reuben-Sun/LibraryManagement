package com.reuben.library.dao;

import com.reuben.library.pojo.Bowen;
import com.reuben.library.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogDaoTest {

    @Test
    void getBowen() {

        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogDao mapper = sqlSession.getMapper(BlogDao.class);
        Bowen bowen = mapper.getBowen(1);
        assertTrue(bowen.getContent().startsWith("针对课堂上介绍的"));
        assertEquals("孙政", bowen.getBlog().getAuthor());
    }
}