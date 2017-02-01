package stas.applogic;

/**
 * Created by skir on 2/1/2017.
 */
public class DeleteHelper extends DriverHelper {

    public DeleteHelper(ApplicationManage applicationManage) {
        super(applicationManage.getDriver());
    }

    public void deleteComment() {
        pageManager.getDeletePage().clickYesButton();
    }

    public void notDeleteComment() {
        pageManager.getDeletePage().clickNoButton();
    }
}
