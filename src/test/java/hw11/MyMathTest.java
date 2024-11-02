package hw11;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MyMathTest {

    @Test
    public void testSum(){
        Assert.assertEquals(MyMath.sum(10,5),15);
        Assert.assertEquals(MyMath.sum(0,0),0);

    }
    @Test
    public void testSubtract(){
        Assert.assertEquals(MyMath.subtract(10,5),5);
        Assert.assertEquals(MyMath.subtract(0,0),0);

    }
    @Test
    public void testMultiply(){
        Assert.assertEquals(MyMath.multiply(10,5),50);
        Assert.assertEquals(MyMath.multiply(0,0),0);
        ;
    }
    @Test
    public void testDivide(){
        Assert.assertEquals(MyMath.divide(10,5),2);
        Assert.assertEquals(MyMath.divide(0,1),0);


    }
}
