package tp.j15_16_17;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpJsonRecordTestApp {

	public static void main(String[] args) {
		appelsWS();
		//testTemporaireEnModePost();
	}
	public static void testTemporaireEnModePost() {
		HttpClient client = HttpClient.newHttpClient();
		String wsURL = "http://localhost:8080/appliSpringWeb/rest/api-news/news";
		String dataAsJsonString="""
				{ "text":"texte qui va bien pour la news" ,
				   "date" : "2023-09-26" }
				""";
		HttpRequest postReq =
			HttpRequest.newBuilder(URI.create( wsURL))
				              .setHeader("Content-Type", "application/json")
				              .POST(BodyPublishers.ofString(dataAsJsonString))
				              .build();
		
		for(int i=0;i<10;i++)
		 client.sendAsync(postReq, BodyHandlers.ofString())
	      .thenAccept(resp -> {
	    		System.out.println("recuperation reponse asynchrone / interpreted by " + Thread.currentThread().getName());
	    	    System.out.println("reponse status:" + resp.statusCode()); 
				System.out.println("reponse uri:" + resp.uri().toString()); 
				System.out.println("reponse type:" + resp.headers().map().get("Content-Type"));
				System.out.println("reponse text:" + resp.body());
				System.out.println("execute by :" + Thread.currentThread().getName());
	      		});
		try {
			Thread.sleep(2000);//pause ici pour eviter arret complet du programme 
			                    // avant la fin des taches de fond asynchrones
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
	public static void appelsWS() {
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
		
		//HttpClient client = HttpClient.newHttpClient();
		HttpClient client = HttpClient.newBuilder()
		    //.proxy(ProxySelector.of(new InetSocketAddress("proxy.xyz.com", 80)))
		    .build();

		HttpRequest req =
		   HttpRequest.newBuilder(URI.create( "https://catfact.ninja/fact"  
				      /* "http://www.d-defrance.fr/qcm-api/public/qcm/6215ef77a8f36f4037eeef0d"*/))
		              .header("User-Agent","Java")
		              .GET()
		              .build();
		//for(int i=0;i<10;i++) //si boucle de lancement de requetes en asynchrone
		                        //alors execute by :ForkJoinPool.commonPool-worker-1 -2 -3 -8 sur i7 à 8 processeurs
		CompletableFuture<HttpResponse<String>> cf= 
		   client.sendAsync(req, BodyHandlers.ofString());
		
		CompletableFuture<Void> cf2=
	    cf.thenApply(resp -> {
	    		System.out.println("recuperation reponse asynchrone / interpreted by " + Thread.currentThread().getName());
	    	    System.out.println("reponse status:" + resp.statusCode()); 
				System.out.println("reponse uri:" + resp.uri().toString()); 
				System.out.println("reponse type:" + resp.headers().map().get("Content-Type"));
				System.out.println("reponse text:" + resp.body());
				System.out.println("execute by :" + Thread.currentThread().getName());
				return resp.body();
	      		})
		//.thenApply((jsonString)->HttpJsonRecordTestApp.convertJsonToCatFact(jsonString))
		//.thenAccept((javaCatFact)-> { System.out.println("catfact as java object:" + javaCatFact.toString()); });
		
	      //.thenApply((jsonString)->Dto.Qcm.fromJSonString(jsonString))
	      .thenApply((jsonString)->Dto.CatFact.fromJSonString(jsonString))
		  .thenAccept((javaCatFact)-> { System.out.println("catFact as java record:" + javaCatFact); });
	      //.thenAccept((javaQcm)-> { System.out.println("qcm as java record:" + javaQcm); });
        System.out.println("suite synchrone interpreted by " + Thread.currentThread().getName());
		
        /*
        try {
			Thread.sleep(2000);//pause ici pour eviter arret complet du programme 
			                    // avant la fin des taches de fond asynchrones
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
        try {
        	//NB: la classe CompleatableFuture<T> implémente Future<T>
        	//et l'on peut éventuellement appeler .get() de manière bloquante
        	//avec ou sans timeout
			cf2.get(2000, TimeUnit.MILLISECONDS); //attente maximale (timeout)
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("fin synchrone / interpreted by " + Thread.currentThread().getName());
	}
	
	public static CatFactClass convertJsonToCatFact(String jsonString) {
		CatFactClass res=null;
		ObjectMapper jacksonObjectMapper = new ObjectMapper();
		try {
			res =  jacksonObjectMapper.readValue(jsonString, CatFactClass.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

}
