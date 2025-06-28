package backend;

import frontend.Student_UI;

public class student extends user {
    // set student attribute & data here aka private xxxx

    public student(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Student_UI UI = new Student_UI();

        UI.Run(this);
    }




}
