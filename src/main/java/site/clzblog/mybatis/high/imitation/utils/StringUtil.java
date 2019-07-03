package site.clzblog.mybatis.high.imitation.utils;

import org.apache.commons.lang3.StringUtils;

public final class StringUtil {

    public static final String SEPARATOR = String.valueOf((char) 29);

    private static boolean isEmpty(String str) {
        if (str != null)  str = str.trim();
        return StringUtils.isEmpty(str);
    }

    static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static String[] splitString(String str, String separator) {
        return StringUtils.splitByWholeSeparator(str, separator);
    }

}
