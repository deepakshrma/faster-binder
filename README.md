## Faster Binder

A Java lib which binds POJO/Config class with a JSON/YML properties in an easy way. API is built on top of Reflection. Means, It will throw exceptions on load class load rather than be throwing null pointer exception at runtime.

## Dependencies
* jackson-databind-2.2.3
* jackson-dataformat-yaml-2.3.0
* json-path-2.2.0

### How to use
It's very simple to use. Just create a POJO class with **@PropBinder, @FieldBinder** annotation.
1. **@PropBinder:** Annotation is to tell compiler that given class can be use to bind properties. It accept 2 parameter.
    a). **path**, Absolute path of the JSON/YAML file.
    b). **type**, To tell what kind file binder needs to be done. Currently API supports only JSON and YAML file.
2. **@FieldBinder** Annotation is to tell comple that given fields will be bind to given properties file. It accept one parameter
    **key**, Here key tells key value needs to be bind. It supports json-path expressions.
* With fat jar, Import **faster-binder\dist\faster-binder-1.0.2.jar**
* Without fat jar, Import **faster-binder\dist\faster-binder-basic-1.0.2.jar** (with above mentioned dependency jars)

More on JSON-PATH: https://github.com/json-path/JsonPath


### Example: faster-binder/examples
```java

//CustomClass.java: POJO class
import org.fasterbinder.xdeepakv.FasterBinder;
import org.fasterbinder.xdeepakv.annotations.FieldBinder;
import org.fasterbinder.xdeepakv.annotations.PropBinder;

/**
 * Created by dvishwakarma on 5/17/2017.
 */

//Resource Path Name and type
@PropBinder(path = "./configs/test.json", type = PropBinder.PropBinderType.JSON)
public class CustomClass {
    @FieldBinder(key = "$.app.endpoint")
    public String endpoint;

    @FieldBinder(key = "$.app.intval")
    public int intval;

    //Do nothing with it
    public int intval2;

    @FieldBinder(key = "$.app.doubleval")
    public Double doubleval;

    public CustomClass() {
        FasterBinder.build(this);
    }

    @Override
    public String toString() {
        return "CustomClass{" +
                "endpoint='" + endpoint + '\'' +
                ", intval=" + intval +
                ", intval2=" + intval2 +
                ", doubleval=" + doubleval +
                '}';
    }
}
```

```java
//Test Class:
package examples;
public class Demo {
    public static void main(String[] args) {
        CustomClass c = new CustomClass();
        System.out.println(c);
        System.out.println("endpoint:"+ c.endpoint);
    }
}

/**
* Output:
* CustomClass{endpoint='http://test.com', intval=12, intval2=0, doubleval=1.3}
* endpoint:http://test.com
*/
```

### How to compile and run given example

```bash
cd examples
javac -cp "../dist/faster-binder-1.0.3.jar" CustomClass.java
javac -cp "../dist/faster-binder-1.0.3.jar:." Demo.java
java -cp "../dist/faster-binder-1.0.3.jar:." Demo
#######################
#########OR############
#######################
$ sh TestExample.sh
```
