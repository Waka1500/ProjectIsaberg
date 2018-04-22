package se.ju.waka1500student.projectisaberg;

import android.util.Patterns;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegexTest {
    @Test
    public void regexNameCheck() throws Exception {
        String pattern = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";
        String name1 = "Karl Wall";
        String name2 = "Carolina Carlbom";
        String name3 = "Fail Me @@@ bananas ";
        String name4 = "Luke Skywalker 23";

        assertTrue(name1.matches(pattern));
        assertTrue(name2.matches(pattern));
        assertFalse(name3.matches(pattern));
        assertFalse(name4.matches(pattern));
    }

    @Test
    public void regexCreditCardNumberCheck() throws Exception {
        String pattern = "^[+]?[0-9]{16}$";
        String no1 = "12345PopcornTV££";
        String no2 = "1234123412341234";
        String no3 = "4572216545843980";
        String no4 = "457221654584398";
        String no5 = "123412341234 1234";

        assertFalse(no1.matches(pattern));
        assertFalse(no4.matches(pattern));
        assertFalse(no5.matches(pattern));
        assertTrue(no2.matches(pattern));
        assertTrue(no3.matches(pattern));
    }

    @Test
    public void regexCvcNumberCheck() throws Exception {
        String pattern = "[+]?[0-9]{3}$";
        String no1 = "1234";
        String no2 = "43H";
        String no3 = "ttt";
        String no4 = "123";
        String no5 = "647";

        assertFalse(no1.matches(pattern));
        assertFalse(no2.matches(pattern));
        assertFalse(no3.matches(pattern));
        assertTrue(no4.matches(pattern));
        assertTrue(no5.matches(pattern));
    }

    @Test
    public void regexZipCodeCheck() throws Exception {
        String pattern = "^[+]?[0-9]{5}$";
        String no1 = "1234";
        String no2 = "43H";
        String no3 = "ttt";
        String no4 = "55423";
        String no5 = "76523";

        assertFalse(no1.matches(pattern));
        assertFalse(no2.matches(pattern));
        assertFalse(no3.matches(pattern));
        assertTrue(no4.matches(pattern));
        assertTrue(no5.matches(pattern));
    }

    @Test
    public void regexPhoneNumberCheck() throws Exception {
        String pattern = "^[+]?[0-9]{10,13}$";
        String no1 = "070628573 ";
        String no2 = "060745 542313";
        String no3 = "070625hej231";
        String no4 = "0706202020";
        String no5 = "0706298761324";

        assertFalse(no1.matches(pattern));
        assertFalse(no2.matches(pattern));
        assertFalse(no3.matches(pattern));
        assertTrue(no4.matches(pattern));
        assertTrue(no5.matches(pattern));
    }
}
