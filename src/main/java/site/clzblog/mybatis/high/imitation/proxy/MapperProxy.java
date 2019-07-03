package site.clzblog.mybatis.high.imitation.proxy;

import site.clzblog.mybatis.high.imitation.annotation.Select;
import site.clzblog.mybatis.high.imitation.session.SqlSession;
import site.clzblog.mybatis.high.imitation.statement.MappedStatement;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler {
    private SqlSession sqlSession;

    MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Annotation annotation = method.getAnnotation(Select.class);
        if (null == annotation) throw new Exception("The method not found @Select annotation");
        MappedStatement statement = new MappedStatement(annotation, method.getReturnType(), args);
        return sqlSession.selectOne(statement);
    }
}
