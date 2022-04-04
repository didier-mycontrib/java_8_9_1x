package tp.j15_16_17;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;

import tp.j15_16_17.Dto.CatFact;

public class HttpJsonRecordTestApp {

	public static void main(String[] args) {
		// autre exercice de synthese:
		
		//1 via l'api HttpClient , appeler en mode GET 
		//le web service REST dont l'URL est "https://catfact.ninja/fact"
		//Ce WS facile à appeler (sans api_key) retourne
		//une réponse au format JSON de type
		// { "fact" : "un fait sur les chats" , "length" :  21_ou_autre }
		
		//On pourra effectuer l'appel en mode synchrone ou bien en asynchrone via
		//client.sendAsync(req, BodyHandlers.ofString()).thenAccept(resp -> ...);
		//si mode asynchrone choisie prévoir pause d'attente en fin du main()

		//2. au sein de la callback appelée pour gérer la réponse on pourra:
		// transformer la réponse du format "jsonString" vers le format
		// instance de DTO.CatFact (record à coder avec propriétés .fact et .length )
		//en s'appuyant sur l'api jackson databind en version >= 2.12.5 gérant bien les "record"
		//catFact = jacksonObjectMapper.readValue(catFactAsJsonString,CatFact.class);
		
		//3. on affichera finalement les valeurs du DTO/record java construit via ... .toString()
		
		HttpClient client = HttpClient.newHttpClient();

		HttpRequest req =
		   HttpRequest.newBuilder(URI.create( "https://catfact.ninja/fact"  
				      /* "http://www.d-defrance.fr/qcm-api/public/qcm/6215ef77a8f36f4037eeef0d"*/))
		              .header("User-Agent","Java")
		              .GET()
		              .build();
		
		client.sendAsync(req, BodyHandlers.ofString())
	      .thenApply(resp -> {
	    		System.out.println("recuperation reponse asynchrone / interpreted by " + Thread.currentThread().getName());
	    	    System.out.println("reponse status:" + resp.statusCode()); 
				System.out.println("reponse uri:" + resp.uri().toString()); 
				System.out.println("reponse type:" + resp.headers().map().get("Content-Type"));
				System.out.println("reponse text:" + resp.body());
				return resp.body();
	      		})
	      //.thenApply((jsonString)->Dto.Qcm.fromJSonString(jsonString))
	      .thenApply((jsonString)->Dto.CatFact.fromJSonString(jsonString))
		  .thenAccept((javaCatFact)-> { System.out.println("catFact as java record:" + javaCatFact); });
	      //.thenAccept((javaQcm)-> { System.out.println("qcm as java record:" + javaQcm); });
        System.out.println("suite synchrone interpreted by " + Thread.currentThread().getName());
		try {
			Thread.sleep(2000);//pause ici pour eviter arret complet du programme 
			                    // avant la fin des taches de fond asynchrones
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("fin synchrone / interpreted by " + Thread.currentThread().getName());
	}

}
