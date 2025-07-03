package backend;

import frontend.Admin_UI;

import java.util.ArrayList;
import java.util.Arrays;

public class admin extends user {
    public admin(String id, String username, String password, String ic, String email, String contact_number, String address) {
        super(id, username, password, ic, email, contact_number, address);

        Admin_UI UI = new Admin_UI();

        UI.Run(this);
    }


    public ArrayList<Object> addTutor(String username, String password, String ic, Integer Level, String subject) {
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

    public ArrayList<Object> addReceptionist(String username, String password) {
        ArrayList<Object> back = new ArrayList<>();

        ArrayList<String> allUserData = datamanager.loadData("users.txt");

        if (username.equalsIgnoreCase("") || username.isEmpty()) {
            back.add(false);
            back.add("Username cannot be empty!");
            return back;
        }

        for (String each : allUserData) {
            String [] afterSplit = each.split(",");

            if (afterSplit[1].equalsIgnoreCase(username)){
                back.add(false);
                back.add("This username already exists!");
                return back;
            }
        }

        if (password.equalsIgnoreCase("") || password.isEmpty()) {
            back.add(false);
            back.add("Password cannot be empty!");
            return back;
        }

        ArrayList<Object> userData = new ArrayList<>(Arrays.asList(
                username,
                password,
                true,
                "receptionist",
                "null",
                "null",
                "null",
                "null"
        ));

        String ID = datamanager.addData("users.txt", userData);

        ArrayList<Object> tutorData = new ArrayList<>(Arrays.asList(
                ID
        ));

        datamanager.addLine("receptionists.txt", tutorData);
        back.add(true);
        back.add("Successful Added " + username +" !");
        back.add(ID);

        return back;
    }

    public boolean deleteReceptionist(String id) {
        datamanager.deleteData("users.txt",id);
        return true;
    }

    public boolean saveReceptionist(String id, String username, String password) {
        ArrayList<Object> oldData = datamanager.getData("users.txt",id);

        datamanager.updateData("users.txt",new ArrayList<>(Arrays.asList(
                id,
                username,
                password,
                oldData.get(3),
                oldData.get(4),
                oldData.get(5),
                oldData.get(6),
                oldData.get(7),
                oldData.get(8)
        )));

        return true;
    }
}
