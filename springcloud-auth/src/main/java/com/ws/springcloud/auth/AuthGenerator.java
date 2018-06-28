package com.ws.springcloud.auth;

import com.ws.springcloud.common.generate.MybatisGeneratorUtil;

/**
 * @description 
 * 
 * @author 王松
 * @date 2017/11/21 11:17
 * @version 1.0
 */
public class AuthGenerator {
    private static String DATABASE = "springcloud_auth";
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/" + DATABASE + "?useUnicode=true&characterEncoding=utf-8";
    private static String JDBC_USERNAME = "root";
    private static String JDBC_PASSWORD = "ws123456";
    private static String PACKAGE_NAME = "com.ws.springcloud.auth";

    /**
     * 自动生成Dao层和Service层代码
     * @param args
     */
    public static void main(String[] args) throws Exception {
        String projectPath = getProjectPath(PACKAGE_NAME);
        MybatisGeneratorUtil.generator(
                "王松",
                projectPath,
                PACKAGE_NAME,
                "auth",
                JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD, DATABASE
        );
    }
    private static String getProjectPath(String packageName) {
        String tmp = AuthGenerator.class.getResource("").getPath();
        String basePath = tmp.substring(0, tmp.indexOf("/target"));
        String projectPath = basePath + "/src/main/java/" + packageName.replaceAll("\\.", "/");
        if (projectPath.startsWith("/")) {
            projectPath = projectPath.substring(1);
        }
        return projectPath;
    }

}
