/**
 * @author David Abell�n Navarro
 * @author Juan Carlos Corredor S�nchez
 * @course 2� D.A.M.
 * @date 13/10/2021
 * @github 
 * 
 */
package ejerTema3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ejer33 {
	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL código específico: " + e.getErrorCode());
	}

	public static void main(String[] args) {

		String basedatos = "ejer_tema3";
		String host = "localhost";
		String port = "3306";
		String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
		String user = "acd";
		String pwd = "admin";

		try (
				Connection c = DriverManager.getConnection(urlConnection, user, pwd);
				Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					      ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {

			int i = 0;
			while (rs.next()) {
				i++;
			}
			rs.afterLast();
			while (rs.previous()) {
				System.out.println("[" + (i--) + "]");        
				System.out.println("DNI: " + rs.getString("DNI"));
				System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
				System.out.println("CP: " + rs.getInt("CP"));
			}

		} catch (SQLException e) {
			muestraErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
