package com.example.mybatisplusgenerator;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class AutoGenerator {
    public static void main(String[] args) {

        //1、配置數據源
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Taipei", "root", "password")
                //2、全局配置
                .globalConfig(builder -> {
                    builder.author("Kurt") // 設置作者名
                            .outputDir(System.getProperty("user.dir") + "/src/main/java")   //設置輸出路徑：項目的 java 目錄下
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //註釋日期
                            .dateType(DateType.ONLY_DATE)   //定義生成的實體類中日期的類型 TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            .fileOverride()   //覆蓋之前的文件
                            .enableSwagger()   //開啟 swagger 模式
                            .disableOpenDir();   //禁止打開輸出目錄，默認打開
                })
                //3、包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.mybatisplusgenerator") // 設置父包名
//                            .moduleName("")   //設置模塊包名
                            .entity("entity")   //entity 實體類包名
                            .service("service") //Service 包名
                            .serviceImpl("serviceImpl") // ***ServiceImpl 包名
                            .mapper("mapper")   //Mapper 包名
                            .xml("mapper")  //Mapper XML 包名
                            .controller("controller") //Controller 包名
                            .other("utils") //自定義文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper"));    //配置 mapper.xml 路徑信息：項目的 resources 目錄下
                })
                //配置自定義模板路徑
                .templateConfig(builder -> {
                    builder.entity("templates/entity.java")
                            .service("templates/service.java")
                            .serviceImpl("templates/serviceImpl.java")
                            .mapper("templates/mapper.java")
                            .mapperXml("templates/mapper.xml")
                            .controller("templates/controller.java");
                })

                //4、策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("user","goods") // 設置需要生成的數據表名  (這裡是你DB的table表)
                            .addTablePrefix("t_", "c_") // 設置過濾表前綴

                            //4.1、Mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //設置父類
                            .formatMapperFileName("%sMapper")   //格式化 mapper 文件名稱
                            .enableMapperAnnotation()       //開啟 @Mapper 註解
                            .formatXmlFileName("%sMapper") // Xml 文件名称
                            .enableBaseResultMap() // 启动resultMap
                            .enableBaseColumnList() // 启用 columnList (通用查询结果列)

                            //4.2、service 策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名稱，%s進行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 實現類文件名稱，%s進行匹配表名，如 UserServiceImpl

                            //4.3、實體類策略配置
                            .entityBuilder()
                            .enableLombok() //開啟 Lombok
                            .disableSerialVersionUID()  //不實現 Serializable 接口，不生產 SerialVersionUID
                            .logicDeleteColumnName("deleted")   //邏輯刪除字段名
                            .naming(NamingStrategy.underline_to_camel)  //數據庫表映射到實體的命名策略：下劃線轉駝峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //數據庫表字段映射到實體的命名策略：下劃線轉駝峰命
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )   //添加表字段填充，"create_time"字段自動填充為插入時間，"modify_time"字段自動填充為插入修改時間
                            .enableTableFieldAnnotation()       // 開啟生成實體時生成字段註解

                            //4.4、Controller策略配置
                            .controllerBuilder()
                            .formatFileName("%sController") //格式化 Controller 類文件名稱，%s進行匹配表名，如 UserController
                            .enableRestStyle();  //開啟生成 @RestController 控制器
                })
                //5、模板
                .templateEngine(new VelocityTemplateEngine())
//                .templateEngine(new FreemarkerTemplateEngine())



                /*
                    .templateEngine(new FreemarkerTemplateEngine())
                    .templateEngine(new BeetlTemplateEngine())
                */

                //6、執行
                .execute();
    }
}
