package site.clzblog.mybatis.high.imitation.utils;

import java.io.InputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {

    private String propertiesName;

    public PropertiesUtil(String fileName) {
        this.propertiesName = fileName;
    }

    public String readProperty(String key) {
        String value = "";
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName);
            Properties p = new Properties();
            p.load(is);
            value = p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }


    private Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(propertiesName);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    public void writeProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            is = new FileInputStream(propertiesName);
//            is = PropertiesUtil.class.getClassLoader().getResourceAsStream(properiesName);
            p.load(is);
//            os = new FileOutputStream(PropertiesUtil.class.getClassLoader().getResource(properiesName).getFile());
            os = new FileOutputStream(propertiesName);
            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) is.close();
                if (null != os) os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
