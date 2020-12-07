package com.springframework.springbootpetclinic.converters;

import com.springframework.springbootpetclinic.commands.OwnersCommand;
import com.springframework.springbootpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnersCommandToOwnersTest {

    public static final Long ID = 1L;
    public static final String FIRST_NAME = "DannyTest";
    public static final String LAST_NAME = "OrtizTest";

    OwnersCommandToOwners converter;

    @BeforeEach
    void setUp() {
        converter = new OwnersCommandToOwners();
    }

    @Test
    void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new OwnersCommand()));
    }

    @Test
    void testConvert() {

        // given
        OwnersCommand command = new OwnersCommand();
        command.setId(ID);
        command.setFirstName(FIRST_NAME);
        command.setLastName(LAST_NAME);

        // when
        Owner convertedOwner = converter.convert(command);

        // then
        assertNotNull(convertedOwner);
        assertEquals(FIRST_NAME, convertedOwner.getFirstName());
        assertEquals(LAST_NAME, convertedOwner.getLastName());
        assertEquals(ID, convertedOwner.getId());
    }
}