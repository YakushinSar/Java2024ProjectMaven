package lesson12_1;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SidTest {

    @Test
    public void testHowMutchLoveYou() {
        assertEquals(Sid.howMutchLoveYou(1),"I love you");
        assertEquals(Sid.howMutchLoveYou(2),"a little");
        assertEquals(Sid.howMutchLoveYou(6),"not at all");

    }
}