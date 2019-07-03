package site.clzblog.mybatis.high.imitation;

import site.clzblog.mybatis.high.imitation.entity.UserEntity;
import site.clzblog.mybatis.high.imitation.mapper.UserMapper;
import site.clzblog.mybatis.high.imitation.session.SqlSession;
import site.clzblog.mybatis.high.imitation.session.SqlSessionFactory;
import site.clzblog.mybatis.high.imitation.session.SqlSessionFactoryBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build("mybatis-config.properties");
        SqlSession sqlSession = factory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectUser(1L);
        System.out.println(userEntity.toString());
        UserEntity userEntity2 = userMapper.selectUser(1L);
        System.out.println(userEntity2.toString());
    }
}
