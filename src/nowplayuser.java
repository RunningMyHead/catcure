import java.sql.*;

public class nowplayuser {
    private final String id; // 用户ID，唯一且不可修改
    private String username;
    private String account;
    private String password;

    // 构造函数，id是唯一且不可修改的
    public nowplayuser(String id) {
        this.id = id;
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // 从数据库中读取用户信息的方法
    public static nowplayuser getUserFromDatabase(String userId) {
        nowplayuser user = new nowplayuser(userId);
        String url = "jdbc:mysql://localhost:3306/text00?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8"; // 修改为你的数据库URL
        String dbUsername = "root"; // 修改为你的数据库用户名
        String dbPassword = "20040612"; // 修改为你的数据库密码

        String sql = "SELECT username, account, password FROM users WHERE account = ?";

        try (Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    user.setUsername(rs.getString("username"));
                    user.setAccount(rs.getString("account"));
                    user.setPassword(rs.getString("password"));
                } else {
                    user = null; // 如果没有找到用户，返回null
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
