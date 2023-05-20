package it.polimi.ingsw.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the static methods of the {@link ClientController} which checks the server info.
 */
class ClientControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void isValidAddress() {
        assertTrue(ClientController.isValidAddress("192.100.2.7"));
        assertFalse(ClientController.isValidAddress("192.100.2.7.8"));
    }

    @Test
    void isValidPort() {
        assertTrue(ClientController.isValidPort("1234"));
        assertFalse(ClientController.isValidPort("123456"));
    }
}