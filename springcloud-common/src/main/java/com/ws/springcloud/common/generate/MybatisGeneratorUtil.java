package com.ws.springcloud.common.generate;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @description
 *
 * @author 王松
 * @date 2017/11/22 14:52
 * @version 1.0
 */
public class MybatisGeneratorUtil {

    private static String packageName = "";
    /**
     * Service模板路径
     */
    private static String service_vm = "";
    /**
     * generatorConfig模板路径
     */
    private static String generatorConfig_vm = "";
    /**
     * ServiceImpl模板路径
     */
    private static String serviceImpl_vm = "";
    /**
     * 用户
     */
    private static String author = System.getProperty("user.name");
    /**
     * 模块路径
     */
    private static String modulePath = "";
    /**
     * 资源路径
     */
    private static String resourcesPath = "";
    /**
     * 代码工程路径
     */
    private static String projectPath = "";

    static {
        String tmp = MybatisGeneratorUtil.class.getResource("").getPath();
        String basePath = tmp.substring(0, tmp.indexOf("/target"));
        if (basePath.startsWith("/")) {
            basePath = basePath.substring(1);
        }
        try {
            basePath = java.net.URLDecoder.decode(basePath, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resourcesPath = basePath + "/src/main/resources";
        System.out.println("resourcesPath = " + resourcesPath);

        service_vm = resourcesPath + "/template/Service.vm";
        generatorConfig_vm = resourcesPath + "/template/generatorConfig.vm";
        serviceImpl_vm = resourcesPath + "/template/ServiceImpl.vm";
    }

    /**
     * 根据模板生成generatorConfig.xml文件
     *
     * @param jdbc_driver   驱动路径
     * @param jdbc_url      链接
     * @param jdbc_username 帐号
     * @param jdbc_password 密码
     * @param database      数据库
     */
    public static void generator(
            String author,
            String projectPath,
            String packageName,
            String tablePrefix,
            String jdbc_driver,
            String jdbc_url,
            String jdbc_username,
            String jdbc_password,
            String database,
            String... forceUpdateDao) throws Exception {
        if (StringUtils.isNotBlank(author)) {
            MybatisGeneratorUtil.author = author;
        }
        projectPath = java.net.URLDecoder.decode(projectPath, "utf-8");
        System.out.println("projectPath = " + projectPath);
        MybatisGeneratorUtil.projectPath = projectPath;
        MybatisGeneratorUtil.packageName = packageName;
        int endIndex = projectPath.indexOf("src/main");
        MybatisGeneratorUtil.modulePath = projectPath.substring(0, endIndex);
        System.out.println("modulePath = " + modulePath);

        List<String> forceUpdateDaoList = new ArrayList<>();
        if (forceUpdateDao != null && forceUpdateDao.length > 0) {
            forceUpdateDaoList.addAll(Arrays.asList(forceUpdateDao));
        }
        List<Map<String, Object>> tables = getTables(tablePrefix, jdbc_driver, jdbc_url, jdbc_username, jdbc_password, database, forceUpdateDaoList);
        if (tables == null || tables.isEmpty()) {
            System.out.println("不存在需要生成代码的新建表，直接结束");
            return;
        }

        String generatorConfig_xml = resourcesPath + "/generatorConfig.xml";
        generateConfigXml(generatorConfig_xml, jdbc_driver, jdbc_url, jdbc_username, jdbc_password, tables);
        generateMapper(generatorConfig_xml);
        generateService(tables);
        deleteFile(generatorConfig_xml);
    }

    private static List<Map<String, Object>> getTables(
            String tablePrefix,
            String jdbc_driver,
            String jdbc_url,
            String jdbc_username,
            String jdbc_password,
            String database,
            List<String> forceUpdateDaoList) {
        List<Map<String, Object>> tables = new ArrayList<>();

        // 查询定制前缀项目的所有表
        //mysql
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "'";
        //oracle
//        String sql = "select table_name from user_tables where 1=1";

        if (tablePrefix != null) {
            sql += " and table_name like '" + tablePrefix + "_%'";
        }
        //如果设置了强制覆盖，则需要先删除mapper，mapper.xml，model，example
        if (forceUpdateDaoList != null && forceUpdateDaoList.size() > 0) {
            for (String modelName : forceUpdateDaoList) {
                String mapperFile = projectPath + "/dao/mapper/" + modelName + "Mapper.java";
                String mapperXmlFile = projectPath + "/dao/mapper/" + modelName + "Mapper.xml";
                String modelFile = projectPath + "/dao/model/" + modelName + ".java";
                String modelExampleFile = projectPath + "/dao/model/" + modelName + "Example.java";

                deleteFile(mapperFile);
                deleteFile(mapperXmlFile);
                deleteFile(modelFile);
                deleteFile(modelExampleFile);
            }
        }
        List<String> tableNames = new JdbcRepository(jdbc_driver, jdbc_url, jdbc_username, jdbc_password)
                .selectTables(sql);
        for (String tableName : tableNames) {
            String modelName = lineToHump(tableName.replace(tablePrefix + "_", ""));
            String mapperFile = projectPath + "/dao/mapper/" + modelName + "Mapper.xml";
            if (new File(mapperFile).exists()) {
                if (!forceUpdateDaoList.contains(modelName)) {
                    System.out.println("已存在代码文件" + modelName + "，跳过");
                    continue;
                }
            }
            System.out.println(tableName);
            Map<String, Object> table = new HashMap<>();
            table.put("table_name", tableName);
            table.put("model_name", modelName);
            tables.add(table);
        }

        return tables;
    }

    private static void deleteFile(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return;
        }

        System.out.println("删除文件 " + fileName);
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static void generateConfigXml(String generatorConfig_xml,
                                          String jdbc_driver,
                                          String jdbc_url,
                                          String jdbc_username,
                                          String jdbc_password,
                                          List<Map<String, Object>> tables) throws Exception {
        System.out.println(String.format("\n========== 开始生成generatorConfig.xml文件，路径为%s ==========", generatorConfig_xml));
        VelocityContext context = new VelocityContext();
        context.put("tables", tables);
        context.put("generator_javaModelGenerator_targetPackage", packageName + ".dao.model");
        context.put("generator_sqlMapGenerator_targetPackage", packageName + ".dao.mapper");
        context.put("generator_javaClientGenerator_targetPackage", packageName + ".dao.mapper");
        context.put("targetProject", modulePath + "/src/main/java");
        context.put("author", author);
        context.put("generator_jdbc_driver", jdbc_driver);
        context.put("generator_jdbc_url", jdbc_url.replace("&", "&amp;"));
        context.put("generator_jdbc_username", jdbc_username);
        context.put("generator_jdbc_password", jdbc_password);
        VelocityUtil.generate(generatorConfig_vm, generatorConfig_xml, context);
        System.out.println("========== 结束生成generatorConfig.xml文件 ==========");
    }

    private static void generateMapper(String generatorConfig_xml) throws Exception {
        System.out.println("\n========== 开始生成mapper ==========");
        List<String> warnings = new ArrayList<>();
        File configFile = new File(generatorConfig_xml);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            System.out.println(warning);
        }
        System.out.println("========== 结束生成mapper ==========");
    }

    private static void generateService(List<Map<String, Object>> tables) throws Exception {
        String servicePath = projectPath + "/service";
        String serviceImplPath = servicePath + "/impl";
        new File(servicePath).mkdir();
        new File(serviceImplPath).mkdir();
        System.out.println(String.format("\n========== 开始生成Service，路径为%s ==========", servicePath));
        String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());

        for (int i = 0; i < tables.size(); i++) {
            String model = Objects.toString(tables.get(i).get("model_name"));
            String service = servicePath + "/" + model + "Service.java";
            String serviceImpl = serviceImplPath + "/" + model + "ServiceImpl.java";
            if (new File(service).exists()) {
                System.out.println(service + "已经存在，跳过");
                continue;
            }
            // 生成service
            File serviceFile = new File(service);
            if (!serviceFile.exists()) {
                VelocityContext serviceContext = new VelocityContext();
                serviceContext.put("package_name", packageName);
                serviceContext.put("model", model);
                serviceContext.put("ctime", ctime);
                serviceContext.put("author", author);
                VelocityUtil.generate(service_vm, service, serviceContext);
                System.out.println(service);
            }
            // 生成serviceImpl
            File serviceImplFile = new File(serviceImpl);
            if (!serviceImplFile.exists()) {
                VelocityContext serviceImplContext = new VelocityContext();
                serviceImplContext.put("package_name", packageName);
                serviceImplContext.put("model", model);
                serviceImplContext.put("mapper", toLowerCaseFirstOne(model));
                serviceImplContext.put("ctime", ctime);
                serviceImplContext.put("author", author);
                VelocityUtil.generate(serviceImpl_vm, serviceImpl, serviceImplContext);
                System.out.println(serviceImpl);
            }
        }
        System.out.println("========== 结束生成Service ==========");
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     *
     * @param str
     * @return
     */
    private static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 首字母小写
     *
     * @param s
     * @return
     */
    private static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

}
