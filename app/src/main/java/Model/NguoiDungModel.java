package Model;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class NguoiDungModel {
    String userr;
    String mail;
    String imagee;

    public NguoiDungModel(String imagee, String mail, String userr) {
        this.userr = userr;
        this.mail = mail;
        this.imagee = imagee;
    }

    public NguoiDungModel() {
    }

    public String getUserr() {
        return userr;
    }

    public void setUserr(String userr) {
        this.userr = userr;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImagee() {
        return imagee;
    }

    public void setImagee(String imagee) {
        this.imagee = imagee;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imagee", imagee);
        result.put("mail", mail);
        result.put("userr", userr);

        return result;
    }


}
