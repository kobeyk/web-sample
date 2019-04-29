package com.appleyk.core.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>日志帮助类</p>
 *
 * @author yukun24@126.com
 * @version V.1.0.1
 * @company 苏州中科蓝迪
 * @date created on 上午 11:57 2019-4-26
 */
public class LoggerHelper {

    private static Logger gxLogger = LoggerFactory.getLogger(LoggerHelper.class);

    public static void info(String message){
        gxLogger.info(message);
    }
    public static void debug(String message){
        gxLogger.debug(message);
    }

    public static void error(String message,Exception ex){
        gxLogger.error(message,ex);
    }
    public static void error(Integer errCode,String message){
        gxLogger.error("错误码："+errCode+"，错误消息："+message);
    }

    public static void error(Integer errCode,String message,Exception ex){
        gxLogger.error("错误码："+errCode+"，错误消息："+message+",异常信息："+ex.getMessage());
    }

}
