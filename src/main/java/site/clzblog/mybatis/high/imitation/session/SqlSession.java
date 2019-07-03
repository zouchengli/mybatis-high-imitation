package site.clzblog.mybatis.high.imitation.session;

import site.clzblog.mybatis.high.imitation.statement.MappedStatement;

import java.util.List;

public interface SqlSession {
    <T> T getMapper(Class<T> type) throws Exception;

    <T> T selectOne(MappedStatement statement) throws Exception;

    <E> List<E> selectList(MappedStatement statement);
}
