package com.github.cuidok.oa.server.user;

import com.github.cuidok.oa.server.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserQueryMapper {

    User selectUserById(Integer id);

    User selectUserByUsername(String username);
}
