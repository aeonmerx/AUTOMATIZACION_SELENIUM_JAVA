package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;


public class BaseClase {

    protected WebDriver driver;

   
	public BaseClase() {
        // Configuración del navegador Chrome
        String chromeDriverPath = "src/test/resources/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Método para navegar a una URL
    public void navigateTo(String url) {
        driver.get(url);
    }

    // Método para hacer clic en un elemento
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    // Método para ingresar texto en un campo de texto
    public void type(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    // Método para tomar una captura de pantalla
    // ...

    // Método para cerrar el navegador
    public void closeDriver() {
        driver.quit();
    }

    // Método para obtener el objeto WebDriver
    public WebDriver getDriver() {
        return driver;
    }
    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    // Mètodo para tomar una captura de pantalla 
    protected void captureScreenshot(String outputPath) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
            File destinationFile = new File(outputPath);
            destinationFile.getParentFile().mkdirs();
            sourceFile.renameTo(destinationFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 // Generar pdf
    protected void generatePDF(boolean isSuccess, String message, String pdfPath, String imagePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();

            // Agregar texto al PDF
            document.add(new Paragraph("Resultado del Test: " + (isSuccess ? "Éxito" : "Fallo")));
            document.add(new Paragraph("Mensaje: " + message));

            // Agregar una nueva página para la captura de pantalla si se proporciona la ruta
            if (imagePath != null) {
                document.newPage();  // Agregar una nueva página
                Image image = Image.getInstance(imagePath);
                image.scaleToFit(PageSize.A4.getWidth() - 72, PageSize.A4.getHeight() - 72);
                document.add(image);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }


    // Método para esperar a que un elemento sea visible
    public void waitForElementVisible(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Método para esperar a que un elemento sea cliqueable
    public void waitForElementClickable(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Método para ejecutar JavaScript
    public void executeJavaScript(String script) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(script);
    }

    // Método para obtener el texto de un elemento
    public String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Método para obtener el valor de un atributo de un elemento
    public String getAttribute(By locator, String attribute) {
        return driver.findElement(locator).getAttribute(attribute);
    }
 // Método para mover el cursor a un elemento y hacer clic
    public void moveToElementAndClick(By locator) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).click().perform();
    }

    // Método para seleccionar una opción de un elemento Select
    public void selectOptionFromDropdown(By locator, String optionText) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(optionText);
    }

    // Método para desplazarse hacia abajo en la página
    public void scrollDown() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    // Método para cambiar a una ventana o pestaña nueva
    public void switchToNewWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    // Método para volver a la ventana o pestaña anterior
    public void switchToPreviousWindow() {
        String currentWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(currentWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
 
    
    
  
}
