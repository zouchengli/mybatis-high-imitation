package site.clzblog.mybatis.high.imitation.executor;

import site.clzblog.mybatis.high.imitation.session.Configuration;
import site.clzblog.mybatis.high.imitation.statement.MappedStatement;
import site.clzblog.mybatis.high.imitation.utils.MyBatisJDBCUtil;

import java.util.List;

public class SimpleExecutor extends BaseExecutor {
    @Override
    public <E> List<E> doQuery(MappedStatement statement) {
        Configuration configuration = statement.getConfiguration();
        MyBatisJDBCUtil jdbcUtil = new MyBatisJDBCUtil(configuration.getDatasource());
        System.out.println("Query from database");
        return (List<E>) jdbcUtil.queryListExecute(statement.getSqlValue(), statement.getArgs(), statement.getReturnType());
    }
}
