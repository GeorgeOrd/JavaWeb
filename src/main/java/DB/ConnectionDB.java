package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {

    Connection connect = null;

    public static String user = "postgres";
    public static String password = "593638sql";
    public static String db = "Mecanica_Automotriz";
    public static String ip = "localhost";
    public static String port = "5432";
    PreparedStatement ps = null;

    String connectionChain = "jdbc:postgresql://" + ip + ":" + port + "/" + db;

    public Connection setConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            connect = DriverManager.getConnection(connectionChain, user, password);
            System.out.println("Conexion realizada correctamente");

        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos " + e.toString());
        }

        return connect;
    }

    public ResultSet executeQuery(String sql) throws Exception {
        Statement st = null;
        st = setConnection().createStatement();
        ResultSet rs = st.executeQuery(sql);
        return rs;
    }

    public int ejecutarActualizacionP(String sql, Object[] params) throws SQLException {
        ps = setConnection().prepareStatement(sql);
        //definir los valores para los argumentos
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
        int r = ps.executeUpdate(); //retorna la cantidad de registros actualizados    
        return r;
    }

    public void desconectar() throws SQLException {
        setConnection().close();
    }
}
