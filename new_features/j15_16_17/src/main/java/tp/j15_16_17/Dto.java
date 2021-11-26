package tp.j15_16_17;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

public class Dto {
	
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public record Address(Integer number,String street,String zipCode,String town) {
	};
	
	@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
	public record Person(Integer id,String firstName,String lastName,Address address) {
		public Person(Integer id,String firstName,String lastName) {
			this(id,firstName,lastName,null);
		}
	};
	
	//... record LineRecord(...) en PHASE3 du Tp de Synthese
	public record LineRecord(int x1,int y1,int x2,int y2,String lineColor) {
		@Override
		public String toString() {
			return """
					{ 
					  "x1" : %d, 
					  "y1" : %d,
					  "x2" : %d,
					  "y2" : %d,
					  "lineColor" : "%s"
					}
					""".formatted(x1,y1,x2,y2,lineColor);
		}
	};
	
	//... record RectangleRecord(...) en PHASE3 du Tp de Synthese
	public record RectangleRecord(int x1,int y1,int largeur,int hauteur,String fillColor) {
		@Override
		public String toString() {
			return """
					{ 
					  "x1" : %d, 
					  "y1" : %d,
					  "largeur" : %d,
					  "hauteur" : %d,
					  "fillColor" : "%s"
					}
					""".formatted(x1,y1,largeur,hauteur,fillColor);
		}
	};
		
	//... record CircleRecord(...) en PHASE3 du Tp de Synthese
	public record CircleRecord(int xc,int yc,int radius,String fillColor) {
		@Override
		public String toString() {
			return """
					{ 
					  "xc" : %d, 
					  "yc" : %d,
					  "radius" : %d,
					  "fillColor" : "%s"
					}
					""".formatted(xc,yc,radius,fillColor);
		}
	};
		
	//... record FigureRecord(...) en PHASE3 du Tp de Synthese
	public record FigureRecord(String type,int x1,int y1,int x2,int y2,String color) {
		@Override
		public String toString() {
			return """
					{ 
					  "type" : "%s", 
					  "x1" : %d, 
					  "y1" : %d,
					  "x2" : %d,
					  "y2" : %d,
					  "color" : "%s"
					}
					""".formatted(type,x1,y1,x2,y2,color);
		}
	};

		

}
