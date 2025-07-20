package backend;

import java.util.ArrayList;
import java.util.Arrays;

public class user {
    protected String id;
    protected String username;
    protected String password;

    protected String ic;
    protected String email;
    protected String contact_number;
    protected String address;

    protected user(String id, String username,String password, String ic, String email,String contact_number,String address){
        this.id = id;
        this.username = username;
        this.password = password;

        this.ic = ic;
        this.email = email;
        this.contact_number = contact_number;
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact_number() {
        return this.contact_number;
    }

    public String getEmail() {
        return this.email;
    }

    public String getIc() {
        return this.ic;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return this.id;
    }

    public void setIc(String ic){
        this.ic = ic;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public void setContact(String contact_number){
        this.contact_number = contact_number;
    }

    public String UpdateProfile(String username,String password, String ic, String email, String contact_number, String address){
        this.username = username;
        this.password = password;

        if (ic.equalsIgnoreCase("")) {
            this.ic = "null";
        } else {
            this.ic = ic;
        }

        if (email.equalsIgnoreCase("")) {
            this.email = "null";
        } else {
            this.email = email;
        }

        if (contact_number.equalsIgnoreCase("")) {
            this.contact_number = "null";
        } else {
            this.contact_number = contact_number;
        }

        if (address.equalsIgnoreCase("")) {
            this.address = "null";
        } else {
            this.address = address;
        }

        ArrayList<Object> old = datamanager.getData("users.txt",this.id);

        ArrayList<Object> newdata = new ArrayList<>(Arrays.asList(
                this.id,
                this.username,
                this.password,
                old.get(3),
                old.get(4),
                this.ic,
                this.email,
                this.contact_number,
                this.address
        ));

        datamanager.updateData("users.txt",newdata);
        return "Update Successful";
    }
}
