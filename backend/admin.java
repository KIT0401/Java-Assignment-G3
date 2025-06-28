package backend;

import frontend.Admin_UI;

public class admin extends user {
    public admin(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Admin_UI UI = new Admin_UI();

        UI.Run(this);
    }




}
