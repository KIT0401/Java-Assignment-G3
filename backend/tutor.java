package backend;

import frontend.Tutor_UI;

public class tutor extends user {
    // set tutor attribute & data here aka private xxxx

    public tutor(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Tutor_UI UI = new Tutor_UI();

        UI.Run(this);
    }




}
