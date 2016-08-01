package org.yqj.boot.utils.xml;

import com.thoughtworks.xstream.XStream;
import org.yqj.boot.utils.xml.driver.CDATAXppDriver;
import org.yqj.boot.utils.xml.driver.PojoMapConverter;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by yaoqijun.
 * Date:2016-08-01
 * Email:yaoqijunmail@gmail.io
 * Descirbe: Xml 之间的转换的方式
 */
public class XmlUtils {

    private static XStream xStream;

    static {
//        xStream = new XStream(new CDATAXppDriver());
        xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.registerConverter(new PojoMapConverter());
        xStream.alias("xml", TreeMap.class);
    }

    /**
     * 转换成xml
     * @param t
     * @param <T>
     * @return
     */
    public static <T> String toXml(T t) {
        return xStream.toXML(t);
    }

    /**
     * 把xml转换成map
     * @param xml
     * @return
     */
    public static Map fromXML(String xml){
        return (Map)xStream.fromXML(xml);
    }

    /**
     * 将xml转换成任意class数据
     * @param xml xml内容
     * @param klass 类
     * @param <T> 返回类型
     * @return 对应的类
     */
    public static <T> T parse(String xml, Class<T> klass) {
        XStream xs = new XStream();
        xs.autodetectAnnotations(true);
        xs.ignoreUnknownElements();
        xs.processAnnotations(klass);
        return (T)xs.fromXML(xml);
    }
}
