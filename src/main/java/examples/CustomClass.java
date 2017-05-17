package examples;

import org.fasterbinder.xdeepakv.FasterBinder;
import org.fasterbinder.xdeepakv.annotations.FieldBinder;
import org.fasterbinder.xdeepakv.annotations.PropBinder;

/**
 * Created by dvishwakarma on 5/17/2017.
 */
@PropBinder(path = "./src/main/resources/test.yml", type = PropBinder.PropBinderType.YML)
public class CustomClass {
    @FieldBinder(key = "app.endpoint")
    public String endpoint;

    @FieldBinder(key = "app.somefield")
    public String somefield;

    @FieldBinder(key = "app.test")
    public String test;

    public CustomClass(){
        FasterBinder.build(this);
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
