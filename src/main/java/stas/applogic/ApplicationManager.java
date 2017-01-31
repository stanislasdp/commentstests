package stas.applogic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import stas.util.*;



/**
 * Created by stas on 1/23/17.
 */
public class ApplicationManager implements ApplicationManage {
    private String startURL;
    private WebDriver driver;
    private NavigationManage navigationManage;
    private CommentHelper commentHelper;
    private MainHelper commentsHelper;


    public ApplicationManager() {
        startURL = PropertyLoader.getProperty("url");
       //System.setProperty("webdriver.gecko.driver","D:\\KiryanStanislav\\screnshots\\SoftBank\\PCC-22108\\PCC-22108\\PCC-22108\\selenium\\geckodriver.exe");
      //  System.setProperty("webdriver.chrome.driver","D:\\KiryanStanislav\\screnshots\\SoftBank\\PCC-22108\\PCC-22108\\PCC-22108\\selenium\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/media/MEDIA/install/linux/instALL/chromedriver");
     //   System.setProperty("webdriver.chrome.driver", "/mnt/JAVA/instALL/chromedriver");
       // driver = new ChromeDriver();
        driver = new ChromeDriver();
        navigationManage = new NavigationManager(this);
        commentHelper = new CommentHelper(this);
        commentsHelper = new MainHelper(this);

    }
    public String getStartURL() {
        return startURL;
    }


    public MainHelper getCommentsHelper() {
        return commentsHelper;
    }

    public NavigationManage getNavigationManage() {
        return navigationManage;
    }


    public WebDriver getDriver() {
        return driver;
    }

    public CommentHelper getCommentHelper() {
        return commentHelper;
    }

    public void stopApp() {
      /* driver.close();
        driver.quit();*/
    }

}
