package com.itheima.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类型转换器  ： 字符串转换为日期格式
 */
public class StringToDateConverter implements Converter<String ,Date>{

    @Override
    public Date convert(String source) {
        System.out.println("source:" + source);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            return format.parse(source);
        } catch (ParseException e) {
            System.out.println("字符串转换为日期失败");
            e.printStackTrace();
        }
        return null;
    }
}
