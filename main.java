


public class main {

	/**
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		System.out.println("Hello world!");
		
		
		// Example:
		String filename = "test.kml";
			
		
		textmodifier.WriteToFile(filename, textmodifier.kmlBegin);

		textmodifier.AppendToFile(filename, textmodifier.kmlNewPolygon("x","x",textmodifier.testPoly1));
		textmodifier.AppendToFile(filename, textmodifier.kmlNewPolygon("y","y",textmodifier.testPoly2));
		textmodifier.AppendToFile(filename, textmodifier.kmlNewPoint("point", "descr", textmodifier.testPoint));

		textmodifier.AppendToFile(filename, textmodifier.kmlEnd);
		
		//Output works in QGis 2.6
		//Not tested with Google Earth!!
		
	}

}
