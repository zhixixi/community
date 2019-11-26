package com.huvenbbs.community.dao;

import com.huvenbbs.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by v20 Luna on 2019/11/26 1:00
 */
@Mapper
public interface UserMapper {

    /**
     * Interface 中的方法默认是有 public 的,所以 public 可以省略
     * @param user
     */
//    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{account_id},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User user);

}
