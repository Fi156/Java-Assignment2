
public class test {
	public static void  kmlNewPolygon()
	{
		
		System.out.println("Hello world!");
		
		
		// Example:
		String filename = "test.kml";
			
		
		TextOut.WriteToFile(filename, TextOut.kmlBegin);

		TextOut.AppendToFile(filename, TextOut.kmlNewPolygon("x","x",TextOut.testPoly1));
		TextOut.AppendToFile(filename, TextOut.kmlNewPolygon("y","y",TextOut.testPoly2));
		//TextOut.AppendToFile(filename, TextOut.kmlNewPoint("point", "descr", TextOut.testPoint));

		TextOut.AppendToFile(filename, TextOut.kmlEnd);
		
		
	}
	
}
