package tp.util;

import java.util.Optional;

public class MyFileConsumer {
	
	//cette classe utilitaire va surveiller l'existence d'un nouveau fichier
	//potentiellement déposé dans ./files/input
	//et va récupérer le contenu de ce fichier 
	//et supprimer ce fichier
	//ou bien déplacer et renommer le fichier traité dans ./files/done/fileName_timestamp
	
	private String mainDirectoryPath="./files";
	private String inputSubDirectory="input";
	private String doneSubDirectory="done";
	
	Optional<String> extractNewFileContentIfExists() {
		String fileContent=null;
		return Optional.ofNullable(fileContent);
	}

}
