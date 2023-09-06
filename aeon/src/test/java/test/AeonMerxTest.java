package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import mapObjects.MapPageObjects;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;
import pages.AeonMerxPage;

public class AeonMerxTest {

    private AeonMerxPage aeonMerxPage;

    @BeforeMethod
    public void setUp() {
        aeonMerxPage = new AeonMerxPage();
        aeonMerxPage.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAeonMerxLogo() {
        try {
            aeonMerxPage.navigateToAeonMerx();
            aeonMerxPage.logIn(MapPageObjects.EMAIL, MapPageObjects.PASSWORD);

            boolean isLogoPresent = aeonMerxPage.isLogoPresent();
            aeonMerxPage.generatePDF("Verificación del logo después de iniciar sesión", isLogoPresent, true);

            Assert.assertTrue(isLogoPresent, "El logo no está presente después de iniciar sesión");
        } catch (Exception e) {
            handleException(e, "Error durante la ejecución del caso de prueba");
        }
    }

    @Test
    public void testCreateAndVerifyProvider() {
        try {
            // Iniciar sesión como administrador
            aeonMerxPage.navigateToAeonMerx();
            aeonMerxPage.logIn(MapPageObjects.EMAIL, MapPageObjects.PASSWORD);

            // Crear un nuevo proveedor
            aeonMerxPage.navigateToProvidersSection();
            aeonMerxPage.createProvider("Nuevo Proveedor 3", "Proveedor3@example.com", "123456721");

            // Verificar que el proveedor esté presente en la lista
            List<String> providerList = aeonMerxPage.getProviderList();
            aeonMerxPage.assertAndHandle(providerList.contains("Nuevo Proveedor 3"), "El proveedor no está en la lista");

            // Generar informe PDF sin comprobar el logo
            aeonMerxPage.generatePDF("Verificación de la creación de proveedor exitosa", true, false);
        } catch (Exception e) {
            handleException(e, "Error durante la ejecución del caso de prueba");
        }
    }
    @Test
    public void testSomeElementText() {
        try {
            aeonMerxPage.navigateToAeonMerx();
            aeonMerxPage.logIn(MapPageObjects.EMAIL, MapPageObjects.PASSWORD);
            aeonMerxPage.navigateToProvidersSection();
            String elementText = aeonMerxPage.obtenerTextoDeUnTitulo();

            // Comparar el texto obtenido con el valor esperado
            String expectedText = "📚 Todos los Proveedores";
            Assert.assertEquals(elementText, expectedText, "El texto del elemento no coincide");
            aeonMerxPage.generatePDF("Verificación del texto de un elemento", true,false);
        } catch (Exception e) {
            handleException(e, "Error durante la ejecución del caso de prueba");
        }
    }
    
    @Test(dataProvider = "userTestData", dataProviderClass = data.TestData.class)
    public void testLogin(String email, String password) {
    	try {
    	aeonMerxPage.navigateToAeonMerx();
        aeonMerxPage.logIn(email, password);
    	} catch (Exception e) {
    		handleException(e, "Error al ejecutar el caso de prueba");
    	}
        // Puedes usar las variables 'email' y 'password' para las pruebas
    } 
   
    //
    @Test(dataProvider = "userTestDataExcel", dataProviderClass = data.TestDataExcel.class)
    public void testLoginWithExcel(String email, String password) {
        try {
            aeonMerxPage.navigateToAeonMerx();
            aeonMerxPage.logIn(email, password);

            // Puedes continuar con las verificaciones o acciones necesarias después del inicio de sesión
        } catch (Exception e) {
            handleException(e, "Error durante la ejecución del caso de prueba");
        }
    }
    
    @AfterMethod
    public void tearDown() {
        aeonMerxPage.closeDriver();
    }

    private void handleException(Exception e, String errorMessage) {
        e.printStackTrace();
        Assert.fail(errorMessage + ": " + e.getMessage());
    }
}
