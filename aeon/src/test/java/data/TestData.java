package data;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "userTestData")
    public static Object[][] userTestData() {
        return new Object[][] {
            {"aeon.merx@gmail.com", "HectorLavoe666"},
            {"aeon.merx@gmail.com", "password2"},
            // Agrega más conjuntos de datos según sea necesario
        };
    }
}

