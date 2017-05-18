import org.fasterbinder.xdeepakv.FasterBinder;
import org.fasterbinder.xdeepakv.annotations.FieldBinder;
import org.fasterbinder.xdeepakv.annotations.PropBinder;

/**
 * Created by dvishwakarma on 5/17/2017.
 */
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
