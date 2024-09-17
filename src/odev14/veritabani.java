package odev14;

import java.sql.*;

public class veritabani {
    static String url = "jdbc:mysql://localhost:3306/odev14";
    //uzak serverda ise localhost yerine oranın ip adresi yazılır
    static Connection conn = null;

    static void baglan(){
        try {
            conn = DriverManager.getConnection(url,"root","");
            System.out.println("Veritabanı bağlantısı başarılı");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static ResultSet listele(String sorgu){
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(sorgu);
            return rs;
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    static void sil(String sorgu){
        Statement st;
        try {
            st = conn.createStatement();
            st.execute(sorgu);

        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    static void ekle(String sorgu){
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(sorgu);

        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }

    static void guncelle(String sorgu){
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(sorgu);

        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }
    }
}
