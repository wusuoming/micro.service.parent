package com.alibaba.dubbo.integration.swagger;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

/**
 * JSON操作类
 *
 * @author panda
 * @version 2016年3月22日 下午8:55:55
 */
public class JsonUtils {

    /**
     * 忽略对象中值为NULL或""的属性
     */
    public static final JsonUtils EXCLUDE_EMPTY = new JsonUtils(JsonInclude.Include.NON_EMPTY);

    /**
     * 忽略对象中值为默认值的属性
     */
    public static final JsonUtils EXCLUDE_DEFAULT = new JsonUtils(JsonInclude.Include.NON_DEFAULT);

    /**
     * 默认不排除任何属性
     */
    public static final JsonUtils DEFAULT = new JsonUtils();

    private ObjectMapper mapper;

    private JsonUtils() {
        mapper = new ObjectMapper();
        // ignore attributes exists in json string, but not in java object when
        // deserialization
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    private JsonUtils(JsonInclude.Include include) {
        mapper = new ObjectMapper();
        // set serialization feature
        mapper.setSerializationInclusion(include);
        // ignore attributes exists in json string, but not in java object when
        // deserialization
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }


    /**
     * java对象转json字符串
     *
     * @param target 待转换的对象
     * @return json字符串
     */
    public String toJson(Object target) {
        try {
            return mapper.writeValueAsString(target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * deserialize a json to target class object
     *
     * @param json   json string
     * @param target target class
     * @param <T>    the generic type
     * @return target object
     * @throws RuntimeException the exception for json
     */
    public <T> T fromJson(String json, Class<T> target) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        try {
            return mapper.readValue(json, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化
     *
     * @param javaType   JavaType
     * @param jsonString json string
     * @param <T>        the generic type
     * @return the javaType's object
     * @throws RuntimeException the exception for json
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * construct collection type
     *
     * @param collectionClass collection class, such as ArrayList, HashMap, ...
     * @param elementClasses  element class
     * @return JavaType
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    public <T> T xmlToBean(String xmlPath, Class<T> cls) throws IOException {
        XmlMapper xml = new XmlMapper();
        T obj = xml.readValue(new File(xmlPath), cls);
        return obj;
    }

    public <T> String beanToXml(T bean) throws JsonProcessingException {
        XmlMapper xml = new XmlMapper();
        String string = xml.writeValueAsString(bean);
        return string;
    }

    /**
     * 获取map中第一个数据值
     *
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map 数据源
     * @return 返回的值
     */
    public <K, V> V getFirstOrNull(Map<K, V> map) {
        V obj = null;
        for (Entry<K, V> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }
}