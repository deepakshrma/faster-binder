## Faster Binder

A Java lib which bind POJO with a JSON/YML in easy way. API is build on top of Reflection, It will fails on load. Rather than giving null pointer exception at runtime.

### How to use

It's very simple to use. Just create a pojo class with @PropBinder, @FieldBinder annotation. Currently supporting JSON and YAML file.

Example: faster-binder/src/main/java/examples

```java

//CustomClass.java: POJO class
package examples;

import org.fasterbinder.xdeepakv.FasterBinder;
import org.fasterbinder.xdeepakv.annotations.FieldBinder;
import org.fasterbinder.xdeepakv.annotations.PropBinder;

@PropBinder(path = "./src/main/resources/test.json", type = PropBinder.PropBinderType.JSON)
public class CustomClass {
    @FieldBinder(key = "app.endpoint")
    public String endpoint;

    @FieldBinder(key = "app.intval")
    public int intval;

    //Do nothing with it
    public int intval2;

    @FieldBinder(key = "app.doubleval")
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
* CustomClass{endpoint='http://test.co,', intval=12, intval2=0, doubleval=1.3}
* endpoint:http://test.com
*/
```