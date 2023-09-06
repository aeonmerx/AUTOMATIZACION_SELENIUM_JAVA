package pages;

import base.BaseClase;
import mapObjects.MapPageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AeonMerxPage extends BaseClase {

    public AeonMerxPage() {
        super();
    }

    public void navigateToAeonMerx() {
        navigateTo(MapPageObjects.BASE_URL);
    }

    public void logIn(String email, String password) {
        click(MapPageObjects.LINK_CO);
        type(MapPageObjects.TEXTBOX_SEARCH, email);
        type(MapPageObjects.PASSWORD_INPUT, password);
        click(MapPageObjects.BUTTON_INPUT);
    }

    public boolean isLogoPresent() {
        return isElementPresent(MapPageObjects.LOGO_AEON);
    }

    public void assertAndHandle(boolean condition, String message) {
        if (!condition) {
            handleAssertionFailure(message);
        }
    }

    private void handleAssertionFailure(String message) {
        Assert.fail(message);
    }

    public void generatePDF(String message, boolean isSuccess, boolean checkLogo) {
        try {
            String screenshotPath = null;

            if (checkLogo) {
                boolean isElementPresent = isLogoPresent();
                if (isElementPresent && isSuccess) {
                    screenshotPath = "src/test/java/resultados_de_prueba/screenshot.png";
                    captureScreenshot(screenshotPath);
                }
            }

            generatePDF(isSuccess, message, "src/test/java/resultados_de_prueba/test_result.pdf", screenshotPath);

        } catch (Exception e) {
            handleException(e, "Error al generar el PDF");
        }
    }


    public void createProvider(String providerName, String direccion, String nit) {
        // Lógica para crear un nuevo proveedor con los detalles proporcionados
        click(MapPageObjects.BUTTON_PROVIDER);
        click(MapPageObjects.BUTTON_CREATE_PROVIDER);

        // Ingresa los detalles del proveedor (nombre, email, teléfono) en los campos correspondientes
        type(MapPageObjects.PROVIDER_NAME_INPUT, providerName);
        type(MapPageObjects.PROVIDER_DIRECCION_INPUT, direccion);
        type(MapPageObjects.PROVIDER_NIT_INPUT, nit);
         
        // Dar click al boton crear proveedor
        click(MapPageObjects.BUTTON_CREATE_PROVIDER_AFTER);
        
        // Espera para asegurar que la creación del proveedor se haya completado
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            handleException(e, "Error al crear el proveedor");
        }
    }

   
    private void handleException(Exception e, String errorMessage) {
        e.printStackTrace();
        Assert.fail(errorMessage + ": " + e.getMessage());
    }

    public void navigateToProvidersSection() {
        navigateTo("https://aeonmerx.com/providers");
    }
    
    public List<String> getProviderList() {
        List<String> providerNames = new ArrayList<>();

        try {
            // Aquí, se navega a la sección de proveedores si aún no se ha hecho
            navigateToProvidersSection();

            // Espera a que la tabla de proveedores esté presente
			WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement table = wait.until(ExpectedConditions.presenceOfElementLocated(MapPageObjects.PROVIDER_TABLE));

            // Obtén todas las filas de la tabla, excluyendo la primera fila de encabezados
            List<WebElement> rows = table.findElements(MapPageObjects.PROVIDER_TABLE_ROWS);

            // Itera a través de las filas y extrae el nombre del proveedor desde la columna correspondiente
            for (WebElement row : rows) {
                String providerName = row.findElement(MapPageObjects.PROVIDER_NAME_COLUMN).getText();
                providerNames.add(providerName);
            }
        } catch (Exception e) {
            handleException(e, "Error al obtener la lista de proveedores");
        }

        return providerNames;
    }
    // Obtener texto de un titulo
    public String obtenerTextoDeUnTitulo() {
        return getElementText(MapPageObjects.TEXTO_LOCATOR);
    }


}
