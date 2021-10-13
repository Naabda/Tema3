/**
 * @author David Abellán Navarro
 * @author Juan Carlos Corredor Sánchez
 * @course 2º D.A.M.
 * @date 13/10/2021
 * @github 
 * 
 */
package ejerTema3;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ejer31 {

	public static void muestraErrorSQL(SQLException e) {
		System.err.println("SQL ERROR mensaje: " + e.getMessage());
		System.err.println("SQL Estado: " + e.getSQLState());
		System.err.println("SQL código específico: " + e.getErrorCode());
	}

	public static int insert (Statement s) throws SQLException {
		int nFil =s.executeUpdate("INSERT INTO CLIENTES (DNI,APELLIDOS,CP) VALUES "
				+ "('78901234X','NADALES','44126'),"
				+ "('89012345E','HOJAS', null),"
				+ "('56789012B','SAMPER','29730'),"
				+ "('09876543K','LAMIQUIZ', null);");
		return nFil;
	}
	
	public static int update (Statement s) throws SQLException {
		int nFil = s.executeUpdate("UPDATE CLIENTES SET "
				+ "apellidos = 'ROJAS'"
				+ "WHERE dni = '89012345E';");
		
		return nFil;
	}
	
	public static int delete (Statement s) throws SQLException {
		int nFil =s.executeUpdate("DELETE FROM CLIENTES "
				+ "WHERE dni = '09876543K';");
		return nFil;
	}
	
	public static void main(String[] args) {

		String basedatos = "ejer_tema3";
		String host = "localhost";
		String port = "3306";
		String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
		String user = "acd";
		String pwd = "admin";
		int nFil = 0;

		try (
				Connection c = DriverManager.getConnection(urlConnection, user, pwd);
				Statement s = c.createStatement()) {

//			nFil = insert(s);
//			System.out.println(nFil + " Filas insertadas.");
			
			nFil = update(s);
			System.out.println(nFil + " Filas actualizadas.");
			
			nFil = delete(s);
			System.out.println(nFil + " Filas borradas.");
			
		} catch (SQLException e) {
			muestraErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

}
