package com.xjxy.dao;

import com.xjxy.model.MessageBoard;
import java.util.List;

public interface MessageBoardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageBoard record);

    int insertSelective(MessageBoard record);

    MessageBoard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessageBoard record);

    int updateByPrimaryKeyWithBLOBs(MessageBoard record);

    int updateByPrimaryKey(MessageBoard record);

    List<MessageBoard> selectList(String sql);

    Object selectMessageBoard(String sql);
}