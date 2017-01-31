package stas.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


/**
 * Created by skir on 1/23/2017.
 */
public class PageManager {
    private WebDriver driver;



    private MainPage mainPage;
    private CommentPage commentsPage;
    private DeletePage deletePage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
        mainPage = initElements(new MainPage(this));
        commentsPage = initElements(new CommentPage(this));
        deletePage = initElements(new DeletePage(this));
    }


    public MainPage getMainPage() {
        return mainPage;
    }

    public CommentPage getCommentsPage() {
        return commentsPage;
    }

    public DeletePage getDeletePage() {
        return  deletePage;
    }

    public WebDriver getWebDriver() {
        return driver;
    }

    private <T extends Page> T initElements(T page) {
        PageFactory.initElements(driver, page);
        return page;
        //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), page);
        /*PageFactory.initElements(new DisplayedElementLocatorFactory(driver, 10), page);
        return page;*/
    }



}
