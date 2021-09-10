package passion.springboot.passion.repository;

import org.springframework.stereotype.Repository;
import passion.springboot.passion.domain.Member;

import java.sql.*;

@Repository
public class MemberDAOImpl {
    Connection conn = null;
    ResultSet rs = null;

    public Connection getConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE"; // DBMS URL
        String userName = "system";    // DBMS 사용자 아이디
        String password = "cometrue";   // DBMS 사용자 비밀번호

        // 드라이버 로딩
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch(ClassNotFoundException e) {
            System.out.println(" !! JDBC Driver load 오류 : " + e.getMessage());
        }
        // 드라이버 연결
        try {
            conn = DriverManager.getConnection(url, userName, password);
            System.out.println("정상적으로 연걸되었습니다.");
        } catch(SQLException e) {
            System.out.println("연결 오류 : " + e.getMessage());
        }
        return conn;
    }

    // 드라이버 연결해제
    public void closeConnection(Connection conn) {
        try {
            if(conn != null)
                conn.close();
        }catch(SQLException e) {
            System.out.println("conn 오류 : " + e.getMessage());
        }
    }

    // CRUD : Read
    public Member readByEmail(Member member) {
        Member ret = null;
        String selectAllByEmail = "select * from member where email='"+member.getEmail()+"'";
        try {
            PreparedStatement pstmt = getConnection().prepareStatement(selectAllByEmail);
            pstmt.setString(1, member.getEmail());
            rs = pstmt.executeQuery();
            if(rs.next()) {
                ret = new Member();
                ret.setPw(rs.getString("pw"));
                ret.setName(rs.getString("name"));
                ret.setBirth(rs.getString("birth"));
                ret.setPhone(rs.getString("phone"));
                ret.setAddress(rs.getString("address"));
                ret.setAddress2(rs.getString("address2"));
            }
            return ret;
        }catch(SQLException e) {
            e.printStackTrace();
            return ret;
        }finally{
            closeConnection(conn);  // Connection만 자원회수함
            // 실제로 Statement, PreparedStatement, ResultSet도 자원회수해야 함
        }
    }
}