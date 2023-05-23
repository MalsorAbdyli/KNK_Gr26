package project.models;

import java.util.Date;

public class Standings {
    private int id;
    private String ekipi;
    private int w;
    private int l;
    private String streak;
    private String last_10;
    private float win;

    public Standings() {
        this(-1, "",0, 0, "", "", 0);
    }

    public Standings(int id, String ekipi, int w, int l, String streak, String last_10, float win) {
        this.id = id;
        this.ekipi = ekipi;
        this.w = w;
        this.l = l;
        this.streak = streak;
        this.last_10 = last_10;
        this.win = win;
    }

    public int getId() {
        return id;
    }

    public String getEkipi() {
        return ekipi;
    }

    public void setEkipi(String ekipi) {
        this.ekipi = ekipi;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getL() {
        return l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String  streak) {
        this.streak = streak;
    }

    public String getLast_10() {
        return last_10;
    }

    public void setLast_10(String last_10) {
        this.last_10 = last_10;
    }

    public float getWin() {
        return win;
    }

    public void setWin(float win) {
        this.win = win;
    }
}