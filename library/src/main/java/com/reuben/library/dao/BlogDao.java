package com.reuben.library.dao;

import com.reuben.library.pojo.Blog;
import com.reuben.library.pojo.Bowen;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface BlogDao {
    List<Blog> getBlogList();
    Bowen getBowen(int id);
}
