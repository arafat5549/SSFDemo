package com.xjxy.dao;

import com.xjxy.model.Link;
import java.util.List;

public interface LinkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Link record);

    int insertSelective(Link record);

    Link selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKeyWithBLOBs(Link record);

    int updateByPrimaryKey(Link record);

    List<Link> selectList(String sql);

    Object selectLink(String sql);
}