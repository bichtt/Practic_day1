
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
public class test {
    protected WebDriver driver ;
	@Before
    public void Init()
	{
	    //System.setProperty("webdriver.chrome.driver","E:\\selenium\\driver\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver","E:\\selenium\\driver\\geckodriver.exe");
        //driver =new ChromeDriver();
        driver = new FirefoxDriver();
	    driver.manage().window().maximize();
	    driver.get("https://www.saucedemo.com/");

	}
	public  void Login(String username, String password)
	{
	    WebElement txtusername=driver.findElement(By.id("user-name"));
	    txtusername.sendKeys(username);
	    WebElement txtpassword=driver.findElement(By.id("password"));
	    txtpassword.sendKeys(password);
	    WebElement bnt_login=driver.findElement(By.id("login-button"));
	    bnt_login.click();

	}

    @Test
    public void tc_Login_Success()
    {

        String username="standard_user";
        String pass="secret_sauce";
        Login(username,pass);
        String actResult="https://www.saucedemo.com/inventory.html";
        String expResult=driver.getCurrentUrl();
        Assert.assertEquals(actResult,expResult);
    }
    @Test
    public void tc_Login_Fail1()
    {

        String username="";
        String pass="";
        Login(username,pass);
        String actResult="Epic sadface: Username is required";

        CompareResult(actResult);
    }
    @Test
    public void tc_Login_Fail2()
    {

        String username="standard_user";
        String pass="";
        Login(username,pass);
        String expResult="Epic sadface: Password is required";

        CompareResult(expResult);
    }
    @Test
    public void tc_Login_Fail3()
    {

        String username="";
        String pass="secret_sauce";
        Login(username,pass);
        String expResult="Epic sadface: Username is required";

        CompareResult(expResult);
    }
    @Test
    public void tc_Login_Fail4()
    {

        String username="abc";
        String pass="secret_sauce";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail5()
    {

        String username="standard_user";
        String pass="123abc";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail6()
    {

        String username="abc";
        String pass="123abc";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail7()
    {

        String username=" ";
        String pass=" ";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail8()
    {

        String username="STANDARD_USER";
        String pass="secret_sauce";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail9()
    {

        String username="standard_user";
        String pass="SECRET_SAUCE";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail10()
    {

        String username="' or 1=1;-";
        String pass="secret_sauce";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }
    @Test
    public void tc_Login_Fail11()
    {

        String username="<script>alert('Test login')</script>";
        String pass="secret_sauce";
        Login(username,pass);
        String expResult="Epic sadface: Username and password do not match any user in this service";
        CompareResult(expResult);

    }

    public void CompareResult(String expResult)
    {

        WebElement text=driver.findElement(By.cssSelector("#login_button_container > div > form > div.error-message-container.error > h3"));

        String actResult=text.getText();

        Assert.assertEquals(expResult,actResult);
    }

    @After
	public void CloseDriver()
	{
	    	driver.quit();
	}
}
