package site.clzblog.mybatis.high.imitation.builder;

import site.clzblog.mybatis.high.imitation.session.Configuration;
import site.clzblog.mybatis.high.imitation.session.Datasource;
import site.clzblog.mybatis.high.imitation.utils.ClassUtil;
import site.clzblog.mybatis.high.imitation.utils.PropertiesUtil;

import java.util.List;

public class XmlConfigBuilder extends BaseBuilder {
    private PropertiesUtil propertiesUtil;

    public XmlConfigBuilder(String propertiesName) {
        this.configuration = new Configuration();
        this.propertiesUtil = new PropertiesUtil(propertiesName);
    }


    public Configuration parse() {
        setDatasource();
        setMappers();
        return this.configuration;
    }

    private void setMappers() {
        String mappers = propertiesUtil.readProperty("mappers");
        this.configuration.setMappers(mappers);
        List<Class<?>> classSet = ClassUtil.getClassSet(mappers);
        for (Class classInfo: classSet) {
            this.configuration.addMapper(classInfo);
        }
    }

    private void setDatasource() {
        String jdbcUrl = propertiesUtil.readProperty("jdbc-url");
        String username = propertiesUtil.readProperty("username");
        String password = propertiesUtil.readProperty("password");
        String driver = propertiesUtil.readProperty("driver");
        Datasource datasource = new Datasource(driver, jdbcUrl, username, password);
        this.configuration.setDatasource(datasource);
    }
}
