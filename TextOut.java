import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
public class TextOut {

	//Creates or opens the File "filename" and inserts the "text". Old data in the file will be deleted!
	public static void  WriteToFile (String filename, String text) 
	// Reused code from mkyong:
	// http://www.mkyong.com/java/how-to-write-to-file-in-java-bufferedwriter-example/
	{
		try 
		{
			String content = text;
			//Opens a new file object.
			File file = new File(filename);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// Opens a new object from the class FileWriter with the absolute file path.
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			// Writes the content from text.
			bw.write(content);
			// Closes the file.
			bw.close();
 
			System.out.println("Starting new file:" + filename);
			System.out.println("Appending:" + text);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	//Opens the File "filename" and inserts "text" at the end of the file.
	public static void  AppendToFile (String filename, String text) 
	{
		try 
		{
			String content = text;
 
			File file = new File(filename);
			// Opens a new object from the class FileWriter with the absolute file path. 
			// Adding "true" for appending to existing file data instead of deleting existing data.
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			// Writes the content from text.
			bw.write(content);
			// Closes the file.
			bw.close();
 
			System.out.println("Appending:" + text);
 
		} catch (IOException e) {
			e.printStackTrace();
		}

	}	



	//Begin of kml-File as variable. Sets Version, Encoding and starts the File with document and the Layer name.
	//This part is crucial for adding more than one point/polygon to the layer!
	public static String kmlBegin = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<kml xmlns=\"http://www.opengis.net/kml/2.2\">" +
				"<Document>" + "<name>Test Layer</name>"; 
	
	//Method to generate a new point object.  You need to provide the feature name, a description and the coordinates as string.
	// Coordinates needs a format like: "13.03941, 47.82361".
	public static String  kmlNewPoint (String name, String descr, String coords)
	{
		String out,Placem1, Placem2, Name1, Name2, Descr1, Descr2, Coords1, Coords2;
		
		// Think of the next lines as a XML-structure:
		// (Makes debugging a lot easier ;-) )
		Placem1 = "<Placemark>";
		Name1 ="<name>";
		// name goes here
		Name2 =	"</name><ExtendedData></ExtendedData>"; 
		Descr1 ="<description>";
		// description goes here
		Descr2 = "</description>";
		Coords1 = "<Point>" + "<coordinates>";
		// Coordinate pair goes here
		Coords2 ="</coordinates>"+ "</Point>" ; 
		Placem2 = "</Placemark>";
		
		// Concatenates the string into out.
		out = Placem1 + Name1 + name + Name2 + Descr1 + descr + Descr2 + Coords1 + coords + Coords2 + Placem2;
		
		// Returns out to the method.
		return out;
	}
	
	
	
	// Method to generate a new polygon object. You need to provide the feature name, a description and 
		// the coordinates as string.	
	// Coordinates needs a format like: 13.03941, 47.82361 13.06024, 47.78861. 
	//(Coordinate pairs where separated with a space.)____|
// This method was tested with QGis 2.6.1 and works with it. However the kml File created by this method does not
// work with QGis 2.4 and Google Earth. Most likely: The coordinate String from the PostgreSQL-Database uses
// another comma formatting than kml. Sadly, further testing was not possible due to the fact that I was not
// able to connect to the server without access to the Lab at the weekend.
	public static String  kmlNewPolygon (String name, String descr, String coords)
	{
		String out, Placem1, Placem2, Name1, Name2, Descr1, Descr2, Coords1, Coords2;
		
		// Think of the next lines as a XML-structure:
		// (Makes debugging a lot easier ;-) )
		Placem1 = "<Placemark>";
		Name1 ="<name>";
		// name goes here
		Name2 =	"</name><ExtendedData></ExtendedData>";
		Descr1 ="<description>";
		// description goes here
		Descr2 = "</description>";
		Coords1 = "<Polygon>" +"<outerBoundaryIs>" + " <LinearRing> " +"<coordinates>";
		// Coordinates goes here
		Coords2 ="</coordinates>" + "</LinearRing>" + "</outerBoundaryIs>" +"</Polygon>" ; 
		Placem2 = "</Placemark>";
		
		// Concatenates the string into out.
		out = Placem1 + Name1 + name + Name2 + Descr1 + descr + Descr2 + Coords1 + coords + Coords2 + Placem2;
		
		// Returns out to the method.
		return out;
	}


	//End of KML-File. Closes document and kml only...
	public static String kmlEnd = 				
			"</Document>"+"</kml>";

	
/// TESTING 
	
	//Some coordinates for a test Polygon.
	public static String testPoly1 =
			"13.03941, 47.82 " +
			"13.0602, 47.788 " +
			"13.033, 47.8056 " +
			"13.05354, 47.80628 " +
			"13.04747, 47.81912 ";
	
	//Another set of test coordinates for a Polygon.
	public static String testPoly2 =
			"13.03941, 47.82361 " +
			"13.06024, 47.78861 " +
			"13.03364, 47.80569 " +
			"13.05354, 47.80628 " +
			"13.04747, 47.81912 ";
	// A coordinate set for a test point.
	public static String testPoint =
			"13.03941, 47.82361";
	
	
	
	
	//Test string straight from the lecture. 
	public static String kmlComplete = 
		"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		"<kml xmlns=\"http://www.opengis.net/kml/2.2\">" +
			"<Placemark>" +
				"<name>Google Earth Launcher: single location</name>" +
				"<description>University of Salzburg, Department of Geoinformatics - Z_GIS, "
					+ "Techno-Z Location: 47.823606N, 13.039408E</description>" +
				"<Point>" +
					"<coordinates>13.03941,47.82361</coordinates>" +
				"</Point>" +
			"</Placemark>" +
		"</kml>";
	



}


