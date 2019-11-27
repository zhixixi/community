package com.huvenbbs.community.dao;

import com.huvenbbs.community.pojo.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by v20 Luna on 2019/11/27
 */
@Mapper
public interface QuestionMapper {

    void insert(Question question);
}
