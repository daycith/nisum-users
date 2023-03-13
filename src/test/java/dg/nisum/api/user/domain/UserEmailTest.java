package dg.nisum.api.user.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserEmailTest {
    @Test
    void valid_email(){
        assertDoesNotThrow(() -> {
            new UserEmail("hello@dominio.cl");
        });
    }

    @Test
    void valid_email2(){
        assertDoesNotThrow(() -> {
            new UserEmail("hello@dominio.cl");
        });
    }

    @Test
    void valid_email3(){
        assertDoesNotThrow(() -> {
            new UserEmail("hello@dominio.cl");
        });
    }

    void valid_email5(){
        assertDoesNotThrow(() -> {
            new UserEmail("hello@Dominio.cl");
        });
    }
    @Test
    void valid_email4(){
        assertDoesNotThrow(() -> {
            new UserEmail("HELLO@DOMINIO.CL");
        });
    }

    @Test
    void invalid_email(){
        assertThrows(IllegalArgumentException.class,() -> {
            new UserEmail("hello@test.co");
        });
    }
}