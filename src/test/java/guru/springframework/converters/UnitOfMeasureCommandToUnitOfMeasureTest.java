package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final String DESCRIPTION = "description";
    private static final Long LONG_VALUE = new Long(1l);
    private UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void convertToNull() {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }

    @Test
    public void convertToEmptyObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void convert() {
        UnitOfMeasureCommand uniteOfMeasureComand = new UnitOfMeasureCommand();
        uniteOfMeasureComand.setId(LONG_VALUE);
        uniteOfMeasureComand.setDescription(DESCRIPTION);

        UnitOfMeasure unitOfMeasure = converter.convert(uniteOfMeasureComand);
        assertNotNull(unitOfMeasure);
        assertEquals(DESCRIPTION, unitOfMeasure.getDescription());
        assertEquals(LONG_VALUE, unitOfMeasure.getId());
    }
}