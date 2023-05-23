package project.repository;

import project.models.Team;
import project.utils.DateHelper;
import project.utils.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TeamRepository {
    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM team");
        res.next();
        return res.getInt(1);
    }

    public static List<Team> getAll(int pageSize, int page) throws Exception {
        ArrayList<Team> list = new ArrayList<>();

        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM team ORDER BY id ASC LIMIT ? OFFSET ?");
        stmt.setInt(1, pageSize);
        stmt.setInt(2, pageSize * page);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static Team find(int id) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM team WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static List<Team> find(String text) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM team WHERE name LIKE ? ORDER BY id ASC");
        stmt.setString(1, text + "%");

        ResultSet res = stmt.executeQuery();
        ArrayList<Team> list = new ArrayList<>();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static Team create(Team model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO team (name, coach, logo, nr_players) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getName());
        stmt.setString(2, model.getCoach());
        stmt.setString(3, model.getLogo());
        stmt.setInt(4, model.getNr_players());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM team ORDER BY createdAt DESC LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static Team update(Team model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE team SET name = ?, coach = ?, logo = ?, nr_players = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getName());
        stmt.setString(2, model.getCoach());
        stmt.setString(3, model.getLogo());
        stmt.setInt(4, model.getNr_players());
        stmt.setInt(5, model.getId());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }

    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM team WHERE id = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }

    private static Team parseRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String coach = res.getString("coach");
        String logo = res.getString("logo");
        int nr_players = res.getInt("nr_players");
        Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
        Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));

        return new Team(
                id, name, coach, logo, nr_players, createdAt, updatedAt
        );
    }
}
