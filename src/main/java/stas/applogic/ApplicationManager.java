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
    private DeleteHelper deleteHelper;


    public ApplicationManager(WebDriver webDriver) {
        startURL = PropertyLoader.getProperty("url");
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

    public DeleteHelper getDeleteHelper() {
        return deleteHelper;
    }

    public void stopApp() {
      driver.close();
    }

}
