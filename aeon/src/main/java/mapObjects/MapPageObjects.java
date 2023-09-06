package mapObjects;

import org.openqa.selenium.By;

public class MapPageObjects {
	// VARIABLE DE LA URL DE LA WEB A TESTEAR
	 public static final String BASE_URL = "https://aeonmerx.com/";
	 // SELECTORES DE LA PÀGINA
    // Define los selectores para la página
    public static final By LINK_CO = By.cssSelector("body:nth-child(2) div.d-flex.align-items-center.fixed-top:nth-child(2) div.container.d-flex.justify-content-center.justify-content-md-between span:nth-child(1) > a.efecto:nth-child(3)");
    public static final By TEXTBOX_SEARCH = By.xpath("//input[@id='email']");
    public static final String PASSWORD = "HectorLavoe666" ;
    public static final String EMAIL = "aeon.merx@gmail.com" ;
    public static final By PASSWORD_INPUT = By.xpath("//input[@id='password']");
    public static final By BUTTON_INPUT = By.xpath("//button[contains(text(),'Entrar')]");
    public static final By BUTTON_PROVIDER = By.xpath("//a[contains(text(),'Proveedores')]");
    public static final By BUTTON_CREATE_PROVIDER = By.cssSelector("main.py-2 div.container-fluid div.row.justify-content-center div.col-md-12 div.container-fluid div.row.justify-content-center div.col-md-12 div.card div.card-header:nth-child(1) div:nth-child(1) > div.btn.btn-sm.btn-info:nth-child(3)");
    public static final By LOGO_AEON = By.xpath("//body/div[@id='app']/nav[1]/div[1]/img[1]");
	public static final By PROVIDER_NAME_INPUT = By.cssSelector("#nombre"); 
	public static final By PROVIDER_NIT_INPUT = By.cssSelector("#nit"); 
	public static final By PROVIDER_DIRECCION_INPUT = By.cssSelector("#direccion");
	public static final By PROVIDER_TABLE = By.xpath("//table");
	public static final By PROVIDER_TABLE_ROWS = By.xpath("//tbody/tr");
	public static final By PROVIDER_NAME_COLUMN = By.xpath("//tbody/tr/td[2]");
	public static final By BUTTON_CREATE_PROVIDER_AFTER = By.xpath("//button[2]");
	public static final By TEXTO_LOCATOR = By.cssSelector("main.py-2 div.container-fluid div.row.justify-content-center div.col-md-12 div.container-fluid div.row.justify-content-center div.col-md-12 div.card div.card-header:nth-child(1) div:nth-child(1) div.float-left:nth-child(1) > h4:nth-child(1)");
	public static final String USERS_ENDPOINT = "https://www.aeonmerx.com/api/users";



   
    // Agrega más selectores según sea necesario
}
