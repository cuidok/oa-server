package com.github.cuidok.oa.server.user;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDeleteMapper {

    int deleteUserById(Integer id);
}
