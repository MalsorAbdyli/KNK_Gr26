package project.models;

import java.util.Date;

public class Team {
    private int id;
    private String name;
    private String coach;
    private String logo;
    private int nr_players;
    private Date createdAt;
    private Date updatedAt;

    public Team() {
        this(-1, "","", "", 0, new Date(), new Date());
    }

    public Team(int id, String name, String coach, String logo, int nr_players, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.coach = coach;
        this.logo = logo;
        this.nr_players = nr_players;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getNr_players() {
        return nr_players;
    }

    public void setSalt(int nr_players) {
        this.nr_players = nr_players;
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
