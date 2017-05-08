package com.xjxy.dao;

import com.xjxy.model.Announce;
import java.util.List;

public interface AnnounceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Announce record);

    int insertSelective(Announce record);

    Announce selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Announce record);

    int updateByPrimaryKeyWithBLOBs(Announce record);

    int updateByPrimaryKey(Announce record);

    List<Announce> selectList(String sql);

    Object selectAnnounce(String sql);
}