package site.clzblog.mybatis.high.imitation.session;

import site.clzblog.mybatis.high.imitation.proxy.MapperProxyFactory;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();

    private Datasource datasource;
    private String mappers;


    public Datasource getDatasource() {
        return datasource;
    }

    public void setDatasource(Datasource datasource) {
        this.datasource = datasource;
    }

    public String getMappers() {
        return mappers;
    }

    public void setMappers(String mappers) {
        this.mappers = mappers;
    }

    public <T> void addMapper(Class<T> type) {
        knownMappers.put(type, new MapperProxyFactory<T>(type));
    }

    public <T> T getMapper(Class<T> type) {
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(type);
        return (T) mapperProxyFactory;
    }
}
