package org.yqj.boot.utils;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe:
 */
public class BeanMapper {

    /**
     * 持有Dozer单例, 避免重复创建DozerMapper消耗资源.
     */
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    private BeanMapper(){

    }

    /**
     * 基于Dozer转换对象的类型.
     */
    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    /**
     * 基于Dozer转换Collection中对象的类型.
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        for (Object sourceObject : sourceList) {
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

    /**
     * 基于Dozer将对象A的值拷贝到对象B中.
     */
    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }

    /**
     * 基于反射,将JAVA对象转换成Map<String, Object>
     * @param obj 输入对象
     * @return   输出mapper
     * @throws IntrospectionException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @SuppressWarnings("all")
    public static Map<String, Object> convertObjectToMap(Object obj) throws IntrospectionException,
            InvocationTargetException, IllegalAccessException {
        Map<String, Object> objectAsMap = new HashMap();
        BeanInfo info = Introspector.getBeanInfo(obj.getClass());
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            Method reader = pd.getReadMethod();
            if (reader != null)
                if (!reader.isAccessible()) {
                    reader.setAccessible(Boolean.TRUE);
                }
            objectAsMap.put(pd.getName(),reader.invoke(obj));
        }

        return objectAsMap;
    }
}
