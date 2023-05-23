package project.repository;

import project.models.Game;
import project.utils.DateHelper;
import project.utils.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GameRepository {
    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM game");
        res.next();
        return res.getInt(1);
    }

    public static List<Game> getAll(int pageSize, int page) throws Exception {
        ArrayList<Game> list = new ArrayList<>();

        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM game ORDER BY id ASC LIMIT ? OFFSET ?");
        stmt.setInt(1, pageSize);
        stmt.setInt(2, pageSize * page);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static Game find(int id) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM game WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static Game find(String email) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM game WHERE id = ? LIMIT 1");
        stmt.setString(1, email);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static Game create(Game model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO game (ekipiI, ekipiII, hour, result, playsAt) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getEkipiI());
        stmt.setString(2, model.getEkipiII());
        stmt.setString(3, model.getHour());
        stmt.setString(4, model.getResult());
        stmt.setString(5, model.getPlaysAt());


        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM game ORDER BY createdAt DESC LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static Game update(Game model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE game SET ekipiI = ?, ekipiII = ?, hour = ?, result = ?, playsAt = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getEkipiI());
        stmt.setString(2, model.getEkipiII());
        stmt.setString(3, model.getHour());
        stmt.setString(4, model.getResult());
        stmt.setString(5, model.getPlaysAt());
        stmt.setInt(6, model.getId());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }

    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM game WHERE id = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }

    private static Game parseRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String ekipiI = res.getString("ekipiI");
        String ekipiII = res.getString("ekipiII");
        String hour = res.getString("hour");
        String result = res.getString("result");
        String playsAt = res.getString("playsAt");
        Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
        Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));

        return new Game(
                id, ekipiI, ekipiII, hour, result, playsAt, createdAt, updatedAt
        );
    }
}
