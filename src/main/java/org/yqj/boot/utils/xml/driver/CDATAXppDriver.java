package org.yqj.boot.utils.xml.driver;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;
import java.lang.reflect.Field;

/**
 * Created by yaoqijun.
 * Date:2016-08-01
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class CDATAXppDriver extends XppDriver {
    protected static String PREFIX_CDATA = "<![CDATA[";
    protected static String SUFFIX_CDATA = "]]>";

    /**
     * Method description
     *
     *
     * @param out
     *
     * @return
     */
    @Override
    public HierarchicalStreamWriter createWriter(Writer out) {
        return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("_-", "_")) {
            boolean cdata = false;
            Class<?> targetClass = null;
            @Override
            public void startNode(String name, Class clazz) {
                super.startNode(name, clazz);
                if (!name.equals("xml") && !name.equals("direct_trade_create_req") && !name.equals("auth_and_execute_req") && !name.equals("notify")) {
                    cdata = needCDATA(targetClass, name);
                } else {
                    targetClass = clazz;
                }
            }
            @Override
            protected void writeText(QuickWriter writer, String text) {
                if (cdata) {
                    writer.write(cDATA(text));
                } else {
                    writer.write(text);
                }
            }
        };
    }

    /**
     * 检测属性名称是否有@XStreamAlias 递归检测，一直到父类是Object为止
     * @param targetClass
     * @param fieldAlias
     * @return
     */
    private static boolean needCDATA(Class<?> targetClass, String fieldAlias) {
        boolean cdata = false;

        // first, scan self
        cdata = existsCDATA(targetClass, fieldAlias);
        if (cdata) {
            return cdata;
        }

        // if cdata is false, scan supperClass until java.lang.Object
        Class<?> superClass = targetClass.getSuperclass();
        while (!superClass.equals(Object.class))
        {
            cdata = existsCDATA(superClass, fieldAlias);
            if (cdata) {
                return cdata;
            }

            superClass = superClass.getClass().getSuperclass();
        }

        return false;
    }

    /**
     * 扫描类，查看对应属性是否包含XStreamAlias 注解
     * @param clazz
     * @param fieldAlias 属性名称
     * @return
     */
    private static boolean existsCDATA(Class<?> clazz, String fieldAlias) {

        // scan fields
        Field[] fields = clazz.getDeclaredFields();
        if(fields == null){
            return false;
        }

        for (Field field : fields)
        {

            // exists XStreamCDATA
            if (field.getAnnotation(XStreamCDATA.class) != null) {
                XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);

                // exists XStreamAlias
                if (null != xStreamAlias) {
                    if (fieldAlias.equals(xStreamAlias.value())) {    // matched
                        return true;
                    }
                } else {                                              // not exists XStreamAlias
                    if (fieldAlias.equals(field.getName())) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    /**
     * 给文本前后添加<![CDATA[]]>
     * @param text
     * @return
     */
    private static String cDATA(String text) {
        return PREFIX_CDATA + text + SUFFIX_CDATA;
    }
}
