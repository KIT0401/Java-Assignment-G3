package backend;

import frontend.Receptionist_UI;

public class receptionist extends user {
    // set receptionist attribute & data here aka private xxxx

    public receptionist(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Receptionist_UI UI = new Receptionist_UI();

        UI.Run(this);
    }




}
