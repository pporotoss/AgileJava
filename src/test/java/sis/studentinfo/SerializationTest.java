package sis.studentinfo;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SerializationTest {

    @Test
    public void loadtoNewVersionTest() throws IOException, ClassNotFoundException {
        CourseCatalog catalog = new CourseCatalog();
        catalog.load("CourseCatalogTest.testAdd.txt");
        assertEquals(2, catalog.getSessions().size());
    }
}
