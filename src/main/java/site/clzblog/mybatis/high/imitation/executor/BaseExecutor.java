package site.clzblog.mybatis.high.imitation.executor;

import site.clzblog.mybatis.high.imitation.statement.MappedStatement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseExecutor {
    private Map<Object, Object> cache = new HashMap<>();

    public <E> List<E> query(MappedStatement statement) {
        String cacheKey = statement.getCacheKey();
        List<E> cacheValue = (List<E>) this.cache.get(cacheKey);
        if (cacheValue != null) return cacheValue;
        return queryFromDatabase(statement);
    }

    private <E> List<E> queryFromDatabase(MappedStatement statement) {
        List<E> list = doQuery(statement);
        if (list != null) cache.put(statement.getCacheKey(), list);
        return list;
    }

    public abstract <E> List<E> doQuery(MappedStatement statement);
}
