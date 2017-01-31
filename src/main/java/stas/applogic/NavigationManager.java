package stas.applogic;

import stas.pages.MainPage;

/**
 * Created by stas on 1/23/17.
 */
public class NavigationManager extends DriverHelper implements NavigationManage {

    private String startURL;


    public NavigationManager(ApplicationManage appManage) {
        super(appManage.getDriver());
        startURL = appManage.getStartURL();
    }

    public MainPage openMainPage() {
        driver.get(startURL);
        return pageManager.getMainPage();
    }
}
