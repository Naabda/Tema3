/**
 * @author David Abell·n Navarro
 * @author Juan Carlos Corredor S·nchez
 * @course 2∫ D.A.M.
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

public class ejer35 {
	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL c√≥digo espec√≠fico: " + e.getErrorCode());
	}
	
	public static String sql (Statement s, String dni) throws SQLException {
		String result = "";
		ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES WHERE DNI = '" + dni + "';");
		while (rs.next()) {
			result = "DNI: " + rs.getString("DNI")+ System.lineSeparator()
				+"Apellidos: " + rs.getString("APELLIDOS")+ System.lineSeparator()
				+ "CP: " + rs.getString("CP");
		}
		return result;
	}

	public static void main(String[] args) {

		String basedatos = "ejer_tema3";
		String host = "localhost";
		String port = "3306";
		String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
		String user = "acd";
		String pwd = "admin";
		String dni = "89012345E";
		try (
				Connection c = DriverManager.getConnection(urlConnection, user, pwd);
				Statement s = c.createStatement()) {
			String result = sql(s, dni);
			System.out.println(result);
		} catch (SQLException e) {
			muestraErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
