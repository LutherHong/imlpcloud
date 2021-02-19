package zzh.inspur.testlog;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLog4j {
    //  1. 基于类的名称获取日志对象
    static Logger logger = Logger.getLogger(TestLog4j.class);
    public static void main(String[] args) throws InterruptedException {
        // 2. ①进行默认配置, 设置日志输出级别
//        BasicConfigurator.configure();
//        logger.setLevel(Level.DEBUG);
        // 2. ②采用log4j配置文件文件配置
        PropertyConfigurator.configure("D:\\IdeaWorkPlace\\imlpcloud\\My\\src\\main\\resources\\log4j.properties");
        for (int i = 0; i < 5; i++) {
            logger.trace("跟踪信息");
            logger.debug("调试信息");
            logger.info("输出信息");
            Thread.sleep(1000);
            logger.warn("警告信息");
            logger.error("错误信息");
            logger.fatal("致命信息");
        }
    }
}
