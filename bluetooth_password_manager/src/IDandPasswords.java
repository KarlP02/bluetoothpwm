import java.util.HashMap;

public class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandPasswords() {
        logininfo.put("test1", "password1");
    }

    protected HashMap getLoginInfo() {
        return logininfo;
    }
}
