package org.yqj.boot.utils.xml.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XStreamAlias("person")
public class Person {

    private Integer id;

    private String name;

    private Double count;

}
