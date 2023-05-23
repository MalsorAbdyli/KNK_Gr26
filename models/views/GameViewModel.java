package project.models.views;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import project.models.Game;
import project.utils.DateHelper;

public class GameViewModel {
    private IntegerProperty id;
    private StringProperty ekipiI;
    private StringProperty ekipiII;
    private StringProperty hour;
    private StringProperty result;
    private StringProperty playsAt;
    private StringProperty createdAt;
    private StringProperty updatedAt;

    public GameViewModel() {
        id = new SimpleIntegerProperty();
        ekipiI = new SimpleStringProperty();
        ekipiII = new SimpleStringProperty();
        hour = new SimpleStringProperty();
        result = new SimpleStringProperty();
        playsAt = new SimpleStringProperty();
        createdAt = new SimpleStringProperty();
        updatedAt = new SimpleStringProperty();
    }

    public GameViewModel(Game model) {
        this();
        this.setId(model.getId());
        this.setEkipiI(model.getEkipiI());
        this.setEkipiII(model.getEkipiII());
        this.setHour(model.getHour());
        this.setResult(model.getResult());
        this.setPlaysAt(model.getPlaysAt());
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

    public StringProperty ekipiIProperty() {
        return ekipiI;
    }

    public String getEkipiI() { return ekipiI.getValue(); }

    public void setEkipiI(String value) { ekipiI.setValue(value); }

    public StringProperty ekipiIIProperty() {
        return ekipiII;
    }

    public String getEkipiII() { return ekipiII.getValue(); }

    public void setEkipiII(String value) {
        ekipiII.setValue(value);
    }

    public StringProperty hourProperty() {
        return hour;
    }

    public String getHour() { return hour.getValue(); }

    public void setHour(String value) { hour.setValue(value); }

    public StringProperty resultProperty() {
        return result;
    }

    public String getResult() { return result.getValue(); }

    public void setResult(String value) {
        result.setValue(value);
    }

    public StringProperty playsAtProperty() {
        return playsAt;
    }

    public String getPlaysAt() { return playsAt.getValue(); }

    public void setPlaysAt(String value) { playsAt.setValue(value); }

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

    public Game getModel() {
        return new Game(getId(), getEkipiI(), getEkipiII(), getHour(), getResult(), getPlaysAt(), getCreatedAt(),
                getUpdatedAt());
    }
}