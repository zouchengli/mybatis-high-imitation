package site.clzblog.mybatis.high.imitation.mapper;

import site.clzblog.mybatis.high.imitation.annotation.Select;
import site.clzblog.mybatis.high.imitation.entity.UserEntity;

public interface UserMapper {
    @Select("select * from user where id = ?")
    UserEntity selectUser(Long userId);
}
