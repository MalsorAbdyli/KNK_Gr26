package project.models;

import java.util.Date;

public class Game {
    private int id;
    private String ekipiI;
    private String ekipiII;
    private String hour;
    private String result;
    private String playsAt;
    private Date createdAt;
    private Date updatedAt;

    public Game() {
        this(-1, "", "", "", "0-0", "", new Date(), new Date());
    }

    public Game(int id, String ekipiI, String ekipiII, String hour, String result, String playsAt, Date createdAt, Date updatedAt) {
        this.id = id;
        this.ekipiI = ekipiI;
        this.ekipiII = ekipiII;
        this.hour = hour;
        this.result = result;
        this.playsAt = playsAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getEkipiI() {
        return ekipiI;
    }

    public void setEkipiI(String ekipiI) {
        this.ekipiI = ekipiI;
    }

    public String getEkipiII() {
        return ekipiII;
    }

    public void setEkipiII(String ekipiII) {
        this.ekipiII = ekipiII;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) { this.hour = hour; }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getPlaysAt() { return playsAt; }

    public void setPlaysAt(String playsAt) {
        this.playsAt = playsAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
