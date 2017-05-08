package com.xjxy.dao;

import com.xjxy.model.Solution;
import java.util.List;

public interface SolutionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Solution record);

    int insertSelective(Solution record);

    Solution selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Solution record);

    int updateByPrimaryKeyWithBLOBs(Solution record);

    int updateByPrimaryKey(Solution record);

    List<Solution> selectList(String sql);

    Object selectSolution(String sql);
}