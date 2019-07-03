package site.clzblog.mybatis.high.imitation.session;

import site.clzblog.mybatis.high.imitation.executor.SimpleExecutor;

class DefaultSqlSessionFactory implements SqlSessionFactory {
    private final Configuration configuration;

    DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public SqlSession openSession() {
        return new DefaultSqlSession(configuration, new SimpleExecutor());
    }
}
