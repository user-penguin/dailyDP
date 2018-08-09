package reserve.control;

import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

public class TodoListApplicationTest {
    @Test
    public void Base64Test() {
        String source = "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6";
        System.out.println(new String(Base64.getDecoder().decode(source)));
    }

}