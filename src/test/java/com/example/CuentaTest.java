package com.example;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Executable;
import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.exception.DineroInsuficienteException;

public class CuentaTest {
    @Test
    void testNombreCuenta() {
        Cuenta cuenta = new Cuenta("Fran", new BigDecimal(1000.222));

        String nombreEsperado = "FRAN";
        String nombreReal = cuenta.getPersona();
        Assertions.assertEquals(nombreEsperado, nombreReal);

        Assertions.assertTrue(nombreReal.equals(nombreReal));
    }

    @Test
    void testSaldoCuenta() {
        Cuenta cuenta = new Cuenta("Fran", new BigDecimal(1000.222));
        assertEquals(1000.222, cuenta.getSaldo().doubleValue());
        assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
        assertTrue(cuenta.getSaldo().compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testReferenciaCuenta() {
        Cuenta cuenta = new Cuenta("Fran", new BigDecimal(1000.222));
        Cuenta cuenta2 = new Cuenta("Fran", new BigDecimal(1000.222));

        // assertNotEquals(cuenta, cuenta2);
        assertEquals(cuenta, cuenta2);
    }

    @Test
    void testDebitoCuenta() {
        Cuenta cuenta = new Cuenta("Fran", new BigDecimal(1000.222));
        cuenta.debito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(900, cuenta.getSaldo().intValue());
    }

    @Test
    void testCreditoCuenta() {
        Cuenta cuenta = new Cuenta("Fran", new BigDecimal(1000.222));
        cuenta.credito(new BigDecimal(100));
        assertNotNull(cuenta.getSaldo());
        assertEquals(1100, cuenta.getSaldo().intValue());
    }

    @Test
    void DineroInsuficienteExceptionCuenta() {
        Cuenta cuenta =  new Cuenta("Fran",  new BigDecimal(1000.222));
     
        RuntimeException exception = assertThrows(DineroInsuficienteException.class,() ->{
            cuenta.debito(new BigDecimal(1500));
        });
        String actual = exception.getMessage();
        String esperado = "Dinero Insuficiente";
        assertEquals(esperado, actual);
    }

}
