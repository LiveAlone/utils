package org.yqj.boot.utils.xml.driver;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class PojoMapConverter implements Converter {
    public PojoMapConverter() {
        super();
    }

    public boolean canConvert(Class clazz) {
        String classname = clazz.getName();
        if (classname.indexOf("Map") >= 0
                || classname.indexOf("List") >= 0
                || classname.indexOf("Bean") >= 0)
            return true;
        else
            return false;
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        map2xml(value, writer, context);
    }

    protected void map2xml(Object value, HierarchicalStreamWriter writer,
                           MarshallingContext context) {
        boolean bMap = true;
        String classname = value.getClass().getName();

        bMap = (classname.indexOf("List") < 0);
        Map<String, Object> map;
        List<Object> list;
        String key;
        Object subvalue;
        if (bMap) {
            map = (Map<String, Object>) value;
            for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet()
                    .iterator(); iterator.hasNext();) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator
                        .next();
                key = (String) entry.getKey();
                subvalue = entry.getValue();
                writer.startNode(key);
                if (subvalue instanceof Map ) {
                    map2xml(subvalue, writer, context);
                } else {
                    writer.setValue(subvalue.toString());
                }
                writer.endNode();
            }

        } else {
            list = (List<Object>) value;
            for (Object subval : list) {
                subvalue = subval;
                writer.startNode("child");
                if (subvalue.getClass().getName().indexOf("String") >= 0) {
                    writer.setValue((String) subvalue);
                } else {
                    map2xml(subvalue, writer, context);
                }
                writer.endNode();
            }
        }
    }

    public Map<String, Object> unmarshal(HierarchicalStreamReader reader,
                                         UnmarshallingContext context) {
        Map<String, Object> map = (Map<String, Object>) populateMap(reader, context);
        return map;
    }

    protected Object populateMap(HierarchicalStreamReader reader,
                                 UnmarshallingContext context) {
        boolean bMap = true;
        Map<String, Object> map = Maps.newTreeMap();
        List<Object> list = Lists.newArrayList();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            String key = reader.getNodeName();
            Object value = null;
            if (reader.hasMoreChildren()) {
                value = populateMap(reader, context);
            } else {
                value = reader.getValue();
            }
            if (bMap) {
                if (map.containsKey(key)) {
                    // convert to list
                    bMap = false;
                    Iterator<Map.Entry<String, Object>> iter = map.entrySet()
                            .iterator();
                    while (iter.hasNext())
                        list.add(iter.next().getValue());
                    // insert into list
                    list.add(value);
                } else {
                    // insert into map
                    map.put(key, value);
                }
            } else {
                // insert into list
                list.add(value);
            }
            reader.moveUp();
        }
        if (bMap)
            return map;
        else
            return list;
    }

}
