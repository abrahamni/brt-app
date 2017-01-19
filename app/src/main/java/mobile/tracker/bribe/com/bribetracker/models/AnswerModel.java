package mobile.tracker.bribe.com.bribetracker.models;

/**
 * Created by Mikheil on 9/28/2016.
 */

public class AnswerModel {
    int offeredBribe;
    private Integer payedBribe;
    private int govenrtmentArea;
    private int bribeReason;
    private int bribeCity;
    private String date;
    private String amount;
    private String title;
    private String desc;
    private boolean anonimouse;
    private String name;
    private String email;
    private String phone;

    public int getOfferedBribe() {
        return offeredBribe;
    }

    public void setOfferedBribe(int offeredBribe) {
        this.offeredBribe = offeredBribe;
    }

    public Integer getPayedBribe() {
        return payedBribe;
    }

    public void setPayedBribe(Integer payedBribe) {
        this.payedBribe = payedBribe;
    }

    public int getGovenrtmentArea() {
        return govenrtmentArea;
    }

    public void setGovenrtmentArea(int govenrtmentArea) {
        this.govenrtmentArea = govenrtmentArea;
    }

    public int getBribeReason() {
        return bribeReason;
    }

    public void setBribeReason(int bribeReason) {
        this.bribeReason = bribeReason;
    }

    public int getBribeCity() {
        return bribeCity;
    }

    public void setBribeCity(int bribeCity) {
        this.bribeCity = bribeCity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isAnonimouse() {
        return anonimouse;
    }

    public void setAnonimouse(boolean anonimouse) {
        this.anonimouse = anonimouse;
    }
}
