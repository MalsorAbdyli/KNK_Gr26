package project.repository;

import project.models.Standings;
import project.utils.DateHelper;
import project.utils.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StandingRepository {
    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM standings");
        res.next();
        return res.getInt(1);
    }

    public static List<Standings> getAll(int pageSize, int page) throws Exception {
        ArrayList<Standings> list = new ArrayList<>();

        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM standings ORDER BY id ASC LIMIT ? OFFSET ?");
        stmt.setInt(1, pageSize);
        stmt.setInt(2, pageSize * page);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static Standings find(int id) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM standings WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static List<Standings> find(String text) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM standings WHERE ekipi LIKE ? ORDER BY id ASC");
        stmt.setString(1, text + "%");

        ResultSet res = stmt.executeQuery();
        ArrayList<Standings> list = new ArrayList<>();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static Standings create(Standings model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO standings (ekipi, w, l, streak, last_10, win) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getEkipi());
        stmt.setInt(2, model.getW());
        stmt.setInt(3, model.getL());
        stmt.setString(4, model.getStreak());
        stmt.setString(5, model.getLast_10());
        stmt.setFloat(6, model.getWin());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM standings ORDER BY win ASC LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static Standings update(Standings model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE standings SET ekipi = ?, w = ?, l = ?, streak = ?, last_10 = ?, win = ?, WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getEkipi());
        stmt.setInt(2, model.getW());
        stmt.setInt(3, model.getL());
        stmt.setString(4, model.getStreak());
        stmt.setString(5, model.getLast_10());
        stmt.setFloat(6, model.getWin());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }

    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM standings WHERE id = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }

    private static Standings parseRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String ekipi = res.getString("ekipi");
        int w = res.getInt("w");
        int l = res.getInt("l");
        String streak = res.getString("streak");
        String last_10 = res.getString("last_10");
        float win = res.getFloat("win");

        return new Standings(
                id, ekipi, w, l, streak, last_10, win
        );
    }
}
