package org.fasteryaml.xdeepakv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.jayway.jsonpath.JsonPath;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by dvishwakarma on 5/17/2017.
 */
public class FiledMapper {
    public static final  <T> T buildObject(T cls){
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String  json = null;
        try {
            Object obj = mapper.readValue(new File("./src/main/resources/application.yml"), Object.class);

            ObjectMapper jsonWriter = new ObjectMapper();
            json = jsonWriter.writeValueAsString(obj);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            for(Field f: cls.getClass().getDeclaredFields()){
                f.setAccessible(true);
                String jsonExp = "$."+f.getAnnotation(YAMLBinder.class).key();
                String endpoint = JsonPath.read(json.toString(), jsonExp);
                Class currentClass = f.getType();
                System.out.println(currentClass.getSimpleName());
                f.set(cls,  endpoint);
                f.setAccessible(true);
            }
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        return cls;
    }
}
