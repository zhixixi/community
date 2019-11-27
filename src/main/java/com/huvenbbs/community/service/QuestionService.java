package com.huvenbbs.community.service;

import com.huvenbbs.community.dao.QuestionMapper;
import com.huvenbbs.community.pojo.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by v20 Luna on 2019/11/27
 */
@Service
public class QuestionService implements QuestionMapper {

    @Autowired
    private QuestionMapper questionMapper;

    @Override
    public void insert(Question question) {
        questionMapper.insert(question);
    }
}
