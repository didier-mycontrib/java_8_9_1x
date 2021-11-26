package tp.j15_16_17;

import java.util.List;
import java.util.function.Function;

import tp.data.Figure;
import tp.util.FigureUtil;

public class SyntheseTestApp {

	private List<Figure> listeFigures = FigureUtil.initSampleFigureList();

	public static void main(String[] args) {
		// Exercice de synthese (avec code à compléter)
		SyntheseTestApp  syntheseTestApp = new SyntheseTestApp();
		syntheseTestApp.selonTypeFigure();
		syntheseTestApp.autresExperimentationsSelonInspirationDuMoment();
	}

	private void selonTypeFigure() {
		for(Figure fig : listeFigures) {
		/*	
		//switch/case avec lambda expression
		//PHASE1: afficher LIGNE (en francais) si fig.type vaut Figure.TYPE_LINE 
		//             RECTANGLE si fig.type vaut Figure.TYPE_RECTANGLE
        //             CERCLE (en francais)  si fig.type vaut Figure.TYPE_CIRCLE
		//             TYPE DE FIGURE INCONNU sinon (par défaut)
		switch(fig.getType()) {
		    case Figure.TYPE_LINE -> System.out.println("LIGNE");
		    case Figure.TYPE_RECTANGLE -> System.out.println("RECTANGLE");
		    case Figure.TYPE_CIRCLE -> System.out.println("CERCLE");
		    default -> System.out.println("TYPE DE FIGURE INCONNU");
		}
		*/
		//PHASE2: via switch/case en tant qu'expression retournant valeur
		//et pour chaque case :
		//      fonctionalité de la PHASE1 ET
		//      via yield , retourner une fonction de transformation adéquate
		Function<Figure,Object> transformationFunction =
				switch(fig.getType()) {
				    case Figure.TYPE_LINE -> { System.out.println("LIGNE"); yield FigureUtil::figureToLine; }
				    case Figure.TYPE_RECTANGLE ->  { System.out.println("RECTANGLE"); yield FigureUtil::figureToRectangle; }
				    case Figure.TYPE_CIRCLE -> { System.out.println("CERCLE"); yield FigureUtil::figureToCircle; }
				    default -> { System.out.println("TYPE DE FIGURE INCONNU"); yield FigureUtil::figureToUnknown; }
				};
				
		Object resTransformation = transformationFunction.apply(fig);
		System.out.println("resTransformation="+resTransformation);
		
		}
	}
	
	private void autresExperimentationsSelonInspirationDuMoment() {
	   //....
	}
}
