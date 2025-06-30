package backend;

import frontend.Admin_UI;

import java.util.ArrayList;

public class admin extends user {
    public admin(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Admin_UI UI = new Admin_UI();

        UI.Run(this);
    }


    public ArrayList<Object> addTutor(String username, String password, String ic, String email, String contact_number, String address, Integer Level, String subject) {
        ArrayList<Object> back = new ArrayList<>();


        return back;
    }

    public ArrayList<Object> updateTutor(String id, String username, String password, String ic, String email, String contact_number, String address, Integer Level, String subject) {
        ArrayList<Object> back = new ArrayList<>();


        return back;
    }

    public boolean deleteTutor(String id) {

        return true;
    }

    public ArrayList<Object> addReceptionist(String username, String password, String ic, String email, String contact_number, String address, Integer Level, String subject) {
        ArrayList<Object> back = new ArrayList<>();


        return back;
    }

    public boolean deleteReceptionist(String id) {

        return true;
    }

}
