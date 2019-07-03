package site.clzblog.mybatis.high.imitation.proxy;

import site.clzblog.mybatis.high.imitation.session.SqlSession;

import java.lang.reflect.Proxy;

public class MapperProxyFactory<T> {
    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(SqlSession sqlSession) {
        final MapperProxy<T> mapperProxy = new MapperProxy<T>(sqlSession);
        return newInstance(mapperProxy);
    }

    private T newInstance(MapperProxy<T> mapperProxy) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{ mapperInterface }, mapperProxy);
    }
}

