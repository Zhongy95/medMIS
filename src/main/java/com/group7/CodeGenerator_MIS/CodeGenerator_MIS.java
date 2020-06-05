package com.group7.CodeGenerator_MIS;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class CodeGenerator_MIS {

  /** 读取控制台内容 */
  public static String scanner(String tip) {
    Scanner scanner = new Scanner(System.in);
    StringBuilder help = new StringBuilder();
    help.append("请输入" + tip + "：");
    System.out.println(help.toString());
    if (scanner.hasNext()) {
      String ipt = scanner.next();
      if (StringUtils.isNotEmpty(ipt)) {
        return ipt;
      }
    }
    throw new MybatisPlusException("请输入正确的" + tip + "！");
  }

  public static void main(String[] args) {
    // 代码生成器
    AutoGenerator mpg = new AutoGenerator();

    // 全局配置
    GlobalConfig gc = new GlobalConfig();
    String projectPath = System.getProperty("user.dir");
    //        gc.setOutputDir(projectPath + "/src/main/java");
    gc.setOutputDir("D:\\MedMIS\\src\\main\\java");
    gc.setAuthor("lyh");
    gc.setOpen(true); // 当代码生成完毕后是否打开所在文件夹
    // gc.setSwagger2(true); 实体属性 Swagger2 注解
    gc.setServiceName("%sService");
    mpg.setGlobalConfig(gc);

    // 数据源配置
    DataSourceConfig dsc = new DataSourceConfig();
    dsc.setUrl(
        "jdbc:mysql://localhost:3306/medmis?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8");
    // dsc.setSchemaName("public");
    dsc.setDriverName("com.mysql.cj.jdbc.Driver");
    dsc.setUsername("root");
    dsc.setPassword("a8922001");
    mpg.setDataSource(dsc);

    // 包配置
    PackageConfig pc = new PackageConfig();
    pc.setModuleName(scanner("模块名"));
    pc.setParent("com.group7"); // controller entity service
    pc.setXml("mapper.xml");

    mpg.setPackageInfo(pc);

    //        // 自定义配置
    //        InjectionConfig cfg = new InjectionConfig() {
    //            @Override
    //            public void initMap() {
    //                // to do nothing
    //            }
    //        };
    //
    //        // 如果模板引擎是 freemarker
    //        String templatePath = "/templates/mapper.xml.ftl";
    //        // 如果模板引擎是 velocity
    //        // String templatePath = "/templates/mapper.xml.vm";

    /*
    cfg.setFileCreate(new IFileCreate() {
        @Override
        public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
            // 判断自定义文件夹是否需要创建
            checkDir("调用默认方法创建的目录，自定义目录用");
            if (fileType == FileType.MAPPER) {
                // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                return !new File(filePath).exists();
            }
            // 允许生成模板文件
            return true;
        }
    });
    */

    //        mpg.setCfg(cfg);
    //
    //        // 配置模板
    //        TemplateConfig templateConfig = new TemplateConfig();
    //
    //        // 配置自定义输出模板
    //        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
    //        // templateConfig.setEntity("templates/entity2.java");
    //        // templateConfig.setService();
    //        // templateConfig.setController();
    //
    //        templateConfig.setXml(null);
    //        mpg.setTemplate(templateConfig);

    // 策略配置
    StrategyConfig strategy = new StrategyConfig();
    // 设置字段和表名是否把下划线完成驼峰命名规则
    strategy.setNaming(NamingStrategy.underline_to_camel);
    strategy.setColumnNaming(NamingStrategy.underline_to_camel);
    // 生成的实体类继承的父类
    //        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
    strategy.setEntityLombokModel(true); // 是否启用lombok
    strategy.setRestControllerStyle(true); // 是否生成RestController
    // 公共父类
    //        strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
    // 写于父类中的公共字段
    //        strategy.setSuperEntityColumns("id");
    strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
    strategy.setControllerMappingHyphenStyle(true);
    strategy.setTablePrefix(pc.getModuleName() + "_");
    mpg.setStrategy(strategy);
    mpg.execute();
  }
}
