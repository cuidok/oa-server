package com.github.cuidok.oa.server.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDeleteMapper {

    int deleteUserById(Integer id);
}
