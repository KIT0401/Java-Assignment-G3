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


    public ArrayList<Object> addTutor(String username, String password, Integer Level, ArrayList<String> Subjects) {
        ArrayList<Object> back = new ArrayList<>();

        if (username.equalsIgnoreCase("") || username.isEmpty()) {
            back.add(false);
            back.add("Username cannot be empty!");
            return back;
        }

        ArrayList<String> allUserData = datamanager.loadData("users.txt");

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
                "tutor",
                "null",
                "null",
                "null",
                "null"
        ));

        String ID = datamanager.addData("users.txt", userData);

        ArrayList<Object> tutorData = new ArrayList<>(Arrays.asList(
                ID,
                Level
        ));

        datamanager.addLine("tutors.txt", tutorData);

        for (String subject : Subjects) {
            ArrayList<Object> subject_Data = new ArrayList<>(Arrays.asList(
                    ID,
                    subject.toLowerCase()
            ));

            datamanager.addData("tutor_subjects.txt", subject_Data);
        }

        back.add(true);
        back.add("Successful Added " + username +" !");
        back.add(ID);

        return back;
    }

    public boolean saveTutor(String id, String username, String password, Integer Level, ArrayList<String> Subjects) {
        ArrayList<Object> old_userData = datamanager.getData("users.txt",id);

        datamanager.updateData("users.txt",new ArrayList<>(Arrays.asList(
                id,
                username,
                password,
                old_userData.get(3),
                old_userData.get(4),
                old_userData.get(5),
                old_userData.get(6),
                old_userData.get(7),
                old_userData.get(8)
        )));

        datamanager.updateData("tutors.txt",new ArrayList<>(Arrays.asList(
                id,
                Level
        )));

        for (String each_subject : Subjects) { // check if current data doesn't have subject then add a new one
            boolean owned = false;

            for (String each_tutor_subjects : datamanager.loadData("tutor_subjects.txt")) {
                String [] each_tutor_subjects_split = each_tutor_subjects.split(",");

                if (each_tutor_subjects_split[2].equals(each_subject) && each_tutor_subjects_split[1].equalsIgnoreCase(id)) {
                    owned = true;
                    break;
                }
            }

            if (!owned) {
                datamanager.addData("tutor_subjects.txt",new ArrayList<>(Arrays.asList(
                        id,
                        each_subject
                        ))
                );
            }

        }

        for (String each_tutor_subjects : datamanager.loadData("tutor_subjects.txt")) { // check if current subject wanted to removed
            String [] each_tutor_subjects_split = each_tutor_subjects.split(",");

            if (!Subjects.contains(each_tutor_subjects_split[2]) && each_tutor_subjects_split[1].equalsIgnoreCase(id)) {
                datamanager.deleteData("tutor_subjects.txt",each_tutor_subjects_split[0]);
            }
        }

        return true;
    }

    public boolean deleteTutor(String id) {
        datamanager.deleteData("users.txt",id);
        return true;
    }

    public ArrayList<Object> addReceptionist(String username, String password) {
        ArrayList<Object> back = new ArrayList<>();

        if (username.equalsIgnoreCase("") || username.isEmpty()) {
            back.add(false);
            back.add("Username cannot be empty!");
            return back;
        }

        ArrayList<String> allUserData = datamanager.loadData("users.txt");

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
