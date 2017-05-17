package org.fasterbinder.xdeepakv;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.lang.reflect.Field;

import org.fasterbinder.xdeepakv.annotations.FieldBinder;
import org.fasterbinder.xdeepakv.annotations.PropBinder;
/**
 * Created by dvishwakarma on 5/17/2017.
 */
public class FasterBinder {
    private static ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
    private static ObjectMapper jsonMapper = new ObjectMapper(new JsonFactory());
    public static final  <T> T build(T that){
        Class clz = that.getClass();
        try{
            PropBinder propBinder = (PropBinder) clz.getAnnotation(PropBinder.class);
            if(null == propBinder){
                throw new RuntimeException("Invalid PropBinder class, missing PropBinder annotation on constructor");
            }
            if(null == propBinder.path() || "".equals(propBinder.path())){
                throw new RuntimeException("Invalid path PropBinder annotation or missing");
            }
            File propBinderFile = new File(propBinder.path());
            if(!propBinderFile.exists()){
                throw new RuntimeException("Missing propBinderFile at "+ propBinderFile.getAbsolutePath());
            }
            ObjectMapper currentMapper = jsonMapper;
            Object obj = getObjectMapper(propBinder.type()).readValue(propBinderFile, Object.class);
            ObjectMapper jsonWriter = new ObjectMapper();
            String json = jsonWriter.writeValueAsString(obj);
            for(Field f: clz.getDeclaredFields()){
                f.setAccessible(true);
                String endpoint = JsonPath.read(json.toString(), f.getAnnotation(FieldBinder.class).key());
                f.set(that, endpoint);
                f.setAccessible(true);
            }
        }catch (IllegalAccessException e){
            System.err.println("PropBinder: Error while parsing PropBinder class/field "+ e.toString());
        }catch (Exception e) {
            System.err.println("PropBinder: Error while parsing PropBinder class/field "+ e.toString());
        }
        return that;
    }
    private static ObjectMapper getObjectMapper(PropBinder.PropBinderType type){
        switch (type){
            case YML: return yamlMapper;
        }
        return jsonMapper;
    }
}
