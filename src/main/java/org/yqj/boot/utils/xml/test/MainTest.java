package org.yqj.boot.utils.xml.test;

import org.yqj.boot.utils.xml.XmlUtils;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class MainTest {
    public static void main(String[] args) {
        System.out.println(XmlUtils.toXml(new Person(1, "yao", 1.1)));
    }
}
