package site.clzblog.mybatis.high.imitation.session;

import site.clzblog.mybatis.high.imitation.builder.XmlConfigBuilder;

public class SqlSessionFactoryBuilder {
    public SqlSessionFactory build(String propertiesName) {
        XmlConfigBuilder builder = new XmlConfigBuilder(propertiesName);
        return build(builder.parse());
    }

    private SqlSessionFactory build(Configuration configuration) {
        return new DefaultSqlSessionFactory(configuration);
    }
}
