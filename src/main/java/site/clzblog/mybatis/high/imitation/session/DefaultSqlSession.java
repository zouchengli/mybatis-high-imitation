package site.clzblog.mybatis.high.imitation.session;

import site.clzblog.mybatis.high.imitation.executor.BaseExecutor;
import site.clzblog.mybatis.high.imitation.proxy.MapperProxyFactory;
import site.clzblog.mybatis.high.imitation.statement.MappedStatement;

import java.util.List;

public class DefaultSqlSession implements SqlSession {
    private final Configuration configuration;

    private final BaseExecutor baseExecutor;

    DefaultSqlSession(Configuration configuration, BaseExecutor baseExecutor) {
        this.configuration = configuration;
        this.baseExecutor = baseExecutor;
    }

    public <T> T getMapper(Class<T> type) throws Exception {
        MapperProxyFactory<T> mapper = (MapperProxyFactory<T>) configuration.getMapper(type);
        if (mapper == null) throw new Exception("Type " + type + " is not know to the MapperRegistry.");
        return mapper.newInstance(this);
    }

    public <T> T selectOne(MappedStatement statement) throws Exception {
        List<T> list = this.selectList(statement);
        if (list.size() == 1) return list.get(0);
        else if (list.size() > 1) throw new Exception("Too many result");
        else return null;
    }

    public <E> List<E> selectList(MappedStatement statement) {
        statement.setConfiguration(configuration);
        return baseExecutor.query(statement);
    }
}
