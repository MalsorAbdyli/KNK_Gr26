package project.models.views;

import javafx.beans.property.IntegerProperty;

import java.util.Date;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import project.models.Team;
import project.utils.DateHelper;

public class TeamViewModel {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty coach;
    private StringProperty logo;
    private IntegerProperty nr_players;
    private StringProperty createdAt;
    private StringProperty updatedAt;

    public TeamViewModel() {
        id = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        coach = new SimpleStringProperty();
        logo = new SimpleStringProperty();
        nr_players = new SimpleIntegerProperty();
        createdAt = new SimpleStringProperty();
        updatedAt = new SimpleStringProperty();
    }

    public TeamViewModel(Team model) {
        this();
        this.setId(model.getId());
        this.setName(model.getName());
        this.setCoach(model.getCoach());
        this.setLogo(model.getLogo());
        this.setNr_players(model.getNr_players());
        this.setCreatedAt(model.getCreatedAt());
        this.setUpdatedAt(model.getUpdatedAt());
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int value) {
        id.setValue(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getName() { return name.getValue(); }

    public void setName(String value) { name.setValue(value); }

    public StringProperty coachProperty() {
        return coach;
    }

    public String getCoach() { return coach.getValue(); }

    public void setCoach(String value) {
        coach.setValue(value);
    }

    public StringProperty logoProperty() {
        return logo;
    }

    public String getLogo() { return logo.getValue(); }

    public void setLogo(String value) { logo.setValue(value); }

    public IntegerProperty nr_playersProperty() {
        return nr_players;
    }

    public int getNr_players() { return nr_players.getValue(); }

    public void setNr_players(double value) {nr_players.setValue(value); }

    public StringProperty createdAtProperty() {
        return createdAt;
    }

    public Date getCreatedAt() {
        try {
            return DateHelper.fromSql(createdAt.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public void setCreatedAt(String value) {
        createdAt.setValue(value);
    }

    public void setCreatedAt(Date value) {
        createdAt.setValue(DateHelper.toSqlFormat(value));
    }

    public StringProperty updatedAtProperty() {
        return updatedAt;
    }

    public Date getUpdatedAt() {
        try {
            return DateHelper.fromSql(updatedAt.getValue());
        } catch (Exception e) {
            return null;
        }
    }

    public void setUpdatedAt(String value) {
        updatedAt.setValue(value);
    }

    public void setUpdatedAt(Date value) {
        updatedAt.setValue(DateHelper.toSqlFormat(value));
    }

    public Team getModel() {
        return new Team(getId(), getName(), getCoach(), getLogo(), getNr_players(), getCreatedAt(), getUpdatedAt());
    }
}
