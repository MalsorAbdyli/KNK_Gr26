package project.repository;

import project.models.User;
import project.models.UserRole;
import project.utils.DateHelper;
import project.utils.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRepository {
    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM users");
        res.next();
        return res.getInt(1);
    }

    public static List<User> getAll(int pageSize, int page) throws Exception {
        ArrayList<User> list = new ArrayList<>();

        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users ORDER BY id ASC LIMIT ? OFFSET ?");
        stmt.setInt(1, pageSize);
        stmt.setInt(2, pageSize * page);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }

    public static User find(int id) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE id = ? LIMIT 1");
        stmt.setInt(1, id);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static User find(String email) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1");
        stmt.setString(1, email);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static User create(User model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO users (name, email, password, salt, role, active) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getName());
        stmt.setString(2, model.getEmail());
        stmt.setString(3, model.getPassword());
        stmt.setString(4, model.getSalt());
        stmt.setString(5, model.getRole() == UserRole.Admin ? "A" : "E");
        stmt.setInt(6, model.getActive() ? 1 : 0);


        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM users ORDER BY createdAt DESC LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static User update(User model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE users SET name = ?, email = ?, password = ?, salt = ?, role = ?, active = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getName());
        stmt.setString(2, model.getEmail());
        stmt.setString(3, model.getPassword());
        stmt.setString(4, model.getSalt());
        stmt.setString(5, model.getRole() == UserRole.Admin ? "A" : "E");
        stmt.setInt(6, model.getActive() ? 1 : 0);
        stmt.setInt(7, model.getId());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getId());
    }

    public static boolean remove(int id) throws Exception {
        String query = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() == 1;
    }

    private static User parseRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
        String name = res.getString("name");
        String email = res.getString("email");
        String password = res.getString("password");
        String salt = res.getString("salt");
        UserRole role = res.getString("role").equals("A") ? UserRole.Admin : UserRole.Employee;
        boolean active = res.getInt("active") == 1;
        Date createdAt = DateHelper.fromSql(res.getString("createdAt"));
        Date updatedAt = DateHelper.fromSql(res.getString("updatedAt"));

        return new User(
                id, name, email, password, salt, role, active, createdAt, updatedAt
        );
    }
}