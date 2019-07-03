package site.clzblog.mybatis.high.imitation.statement;

import site.clzblog.mybatis.high.imitation.annotation.Select;
import site.clzblog.mybatis.high.imitation.session.Configuration;

import java.lang.annotation.Annotation;
import java.util.Arrays;

public class MappedStatement {
    private Configuration configuration;
    private String sqlValue;
    private Annotation sqlType;
    private Class<?> returnType;
    private Object[] args;

    public MappedStatement(String sqlValue, Annotation sqlType, Class<?> returnType, Object[] args) {
        this.sqlValue = sqlValue;
        this.sqlType = sqlType;
        this.returnType = returnType;
        this.args = args;
    }

    public MappedStatement(Annotation sqlType, Class<?> returnType, Object[] args) {
        setSqlValue(sqlType);
        this.sqlType = sqlType;
        this.returnType = returnType;
        this.args = args;
    }

    private void setSqlValue(Annotation sqlType) {
        if (sqlType instanceof Select) {
            Select select = (Select) sqlType;
            this.sqlValue = select.value();
        }
    }

    public String getSqlValue() {
        return sqlValue;
    }

    public void setSqlValue(String sqlValue) {
        this.sqlValue = sqlValue;
    }

    public Annotation getSqlType() {
        return sqlType;
    }

    public void setSqlType(Annotation sqlType) {
        this.sqlType = sqlType;
    }

    public Class<?> getReturnType() {
        return returnType;
    }

    public void setReturnType(Class<?> returnType) {
        this.returnType = returnType;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getCacheKey() {
        return sqlValue + (args == null ? "" : Arrays.toString(args)) + returnType;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
