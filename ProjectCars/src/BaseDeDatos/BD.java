package BaseDeDatos;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Vehiculo.Coche;
import Vehiculo.Usuario;

public class BD {
	private Connection conexion;
	private static Statement stmt;
	
	
		public void conectar(){
			Connection connection = null;
			try{
				conexion = DriverManager.getConnection("jdbc:sqlite:ProjectCars.db");
				try{
					Statement statement = connection.createStatement();
					statement.setQueryTimeout(30);
					statement.executeUpdate("Create table Score (String nombre, int tiempo)"); //tiempo en segundos
				}catch (SQLException e1){
					throw new RuntimeException("La tabla ya esta creada");
				}
		    } catch (SQLException E) {
		        E.printStackTrace();
		    	throw new RuntimeException("No se ha podido realizar la conexion");
		        
		    }
		}
		public void desconectar(){
			try{
				conexion.close();
			}catch(SQLException E){
				E.printStackTrace();
			}
		}
		public void Sentencia(){
			try{
				stmt = conexion.createStatement();
			}catch(SQLException E){
				E.printStackTrace();
			}
		}
		
		public void closeSentencia(){
			try{
				stmt.close();
			}catch(SQLException E){
				E.printStackTrace();
			}
		}
		
		public void insertarScore(String nick, int tiempo){
			String bd = "INSERT INTO Score(nick, tiempo) VALUES ('"+nick+"','"+tiempo+"')";
			try{
				stmt.execute(bd);
			}catch(SQLException iC){
				iC.printStackTrace();
			}
		}
		
		public void obtenerMaxScore(){
			String s = "SELECT * FROM Score ORDER BY tiempo";
		}
		
		
		
		
		/*public static boolean compruebaNick(String nick){
			boolean comprobado = false;
			String s = "SELECT * FROM Usuario WHERE Nick='"+nick+"'";
			ResultSet resultado;
			try {
				resultado = stmt.executeQuery(s);
				if(resultado.next())
					comprobado=true;
				resultado.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return comprobado;
		}
		public void insertarUsuario(String n,String ni,String c,String e){
			String bd = "INSERT INTO cliente(nombre, nick, contraseņa, email) VALUES ('"+n+"','"+ni+"','"+c+"','"+e+"')";
			try{
				stmt.execute(bd);
			}catch(SQLException iC){
				iC.printStackTrace();
			}
		}

		public Usuario obtenerDatos( String nombre){
			String s = "SELECT * FROM Usuario WHERE nombre='"+nombre+"'";
			Usuario u=null;
			try {
				ResultSet od = stmt.executeQuery(s);
				if(od.next())
					u = new Usuario(od.getString("nombre"),od.getString("nick"),od.getString("email"),od.getString("contraseņa"));
				od.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return u;
		}
		
		public void guardarCoches(ArrayList<Coche> coche){
			for(Coche c: coche){
				String s = "insert into coche values("+c.getPosX()+","+c.getPosY()+",'"+c.getName()+"',"+")";
				try {
					stmt.executeUpdate(s);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}*/
}
		