package examples;

import org.fasteryaml.xdeepakv.FiledMapper;
import org.fasteryaml.xdeepakv.YAMLBinder;

/**
 * Created by dvishwakarma on 5/17/2017.
 */
public class CustomClass {
    @YAMLBinder(key = "app.endpoint")
    public String endpoint;

    @YAMLBinder(key = "app.somefield")
    public String somefield;

    @YAMLBinder(key = "app.test")
    public String test;

    public CustomClass(){
        FiledMapper.buildObject(this);
    }
    @Override
    public String toString() {
        return "CustomClass{" +
                "endpoint='" + endpoint + '\'' +
                ",somefield='" + somefield + '\'' +
                ",test='" + test + '\'' +
                '}';
    }
}
