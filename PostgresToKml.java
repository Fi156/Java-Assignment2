import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PostgresToKml {

	public static void main(String[] args) {
		
		// Get and save the centroids!
		Connection conn=null;
		try{
			Class.forName("org.postgresql.Driver");
			String url="jdbc:postgresql://ZGIS206.geo.sbg.ac.at/softdev15";
			conn = DriverManager.getConnection(url,"lv453841","geo123");
			
			String query = "SELECT nuts3_name, ST_asText(ST_Centroid(geom)), area, population FROM austria_nuts3";
					
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query); //The data of the table is now stored in rs. 
			
			
			String filename = "austria_nuts3_centro.kml";
			TextOut.WriteToFile(filename, TextOut.kmlBegin);

			//TextOut.AppendToFile(filename, TextOut.kmlNewPolygon("x","x",TextOut.testPoly1));
			
			while(rs.next()){
				
				String nuts3_name=rs.getString("nuts3_name");
				String point=rs.getString("ST_asText");
				
				double area=rs.getDouble("area");
				String area_st=String.valueOf(area);
				double population=rs.getDouble("population"); // now the values of the first row are stored
				String population_st=String.valueOf(population);
				System.out.println("name:"+nuts3_name+
									"; coordinates:"+point+
									"; description:"+area+population); //here i defined the name of the tags with the information in it
				TextOut.AppendToFile(filename, TextOut.kmlNewPoint(nuts3_name, "Area: "+
					area_st+" Population: "+population_st, point.replace("POINT("," ").replace(")"," ")));
			
			}
			
			TextOut.AppendToFile(filename, TextOut.kmlEnd);
			rs.close();
			statement.close();
			conn.close();
			
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}

}
