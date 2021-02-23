package com.ResolveIDC.Utils;

import org.apache.log4j.Logger;
import java.util.Collection;


public class Asserts{

    private static Logger log = Logger.getLogger("Asserts");

    public void toBeFail(String sMessage) {
        log.error(sMessage);
        Assert.fail(sMessage);
    }

    public void expectToBeFalse(boolean condition) {
        log.info("---------------------Expecting value to be FALSE, Received:-" + condition + "---------------------");
        org.testng.Assert.assertFalse(condition);
    }

    public void expectToBeFalse(boolean condition, String sMessage) {
        log.info("---------------------Expecting value to be FALSE, Received:-" + condition + "---------------------");
        org.testng.Assert.assertFalse(condition,sMessage);
    }

    public void expectToBeNotEqual(boolean actual, boolean expected, String sMessage) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertNotEquals(actual, expected, sMessage);
    }

    public void expectToBeEqual(Object actual, Object expected) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertEquals(actual, expected);
    }

    public void expectToBeTrue(boolean condition) {
        org.testng.Assert.assertTrue(condition);
    }

    public void expectToBeTrue(boolean condition, String sMessage) {
        log.info("---------------------Expecting value to be TRUE , Received:-" + condition + "---------------------");
        org.testng.Assert.assertTrue(condition, sMessage);
    }

    public void expectArrayToBeEqual(Object[] actual, Object[] expected, String message) {
        org.testng.Assert.assertEquals(actual, expected, message);
    }

    public void expectArrayToBeEqual(Object[] actual, Object[] expected) {
        org.testng.Assert.assertEquals(actual, expected);

    }

    public void expectListToBeEqual(Collection<?> actual, Collection<?> expected) {
        org.testng.Assert.assertEquals(actual, expected);

    }

    public void expectListToBeEqual(Collection<?> actual, Collection<?> expected, String message) {
        org.testng.Assert.assertEquals(actual, expected, message);
    }

    public void expectToBeEqual(Object actual, Object expected, String sMessage) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertEquals(actual, expected, sMessage);
    }

    public void expectListToBeEqualNoOrder(Collection<?> actual, Collection<?> expected, String message) {
        Object[] objActual = actual.toArray();
        Object[] objExpect = expected.toArray();
        org.testng.Assert.assertEqualsNoOrder(objActual, objExpect, message);
    }

    public void expectToBeNotEqual(Object actual, Object expected, String sMessage) {
        log.info("Comparing values--- Actual:- " + actual + ", Expected:- " + expected);
        org.testng.Assert.assertNotEquals(actual, expected, sMessage);
    }

}
