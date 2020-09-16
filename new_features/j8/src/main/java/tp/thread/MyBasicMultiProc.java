package tp.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import tp.thread.sam.ResultAggregate;
import tp.thread.sam.SequentialComputing;

//ForkJoin (RecursiveAction) / divide & conquer since jdk 1.7
public class MyBasicMultiProc extends RecursiveTask<Double> {
	
	private static final long serialVersionUID = 1L;
	private static final int FORK_JOIN_MIN_SIZE=1024*50; // THRESHOLD/SEUIL à ajuster selon complexité du calcul
	//plus le calcul/traitement est simple , plus le SEUIL doit avoir une grande valeur
	//pour ne pas trop décomposer pour déléguer 3 fois rien .
	
	private double[] tab;
	private int start,end;

	
	public MyBasicMultiProc(double[] tableau,int deb,int fin ){
		this.tab = tableau;
		this.start = deb;
		this.end = fin;

	}
	   
     static double maxi_multiProc(double[] tableau){
    	 MyBasicMultiProc myStatsMultiProc= new MyBasicMultiProc(tableau, 0, tableau.length - 1);
    	 ForkJoinPool threadPool = new ForkJoinPool();
    	 return threadPool.invoke(myStatsMultiProc);
	  }
     

	@Override
	protected Double compute() {
		Double res =0.0;
		//System.out.println("MyStatsMultiProc.compute() executé par "+Thread.currentThread().getName() );
		
		//NB: etant donné que la version forkJoin ajoute une complexité au niveau du code 
        // (instance à créer , thread à gérer) , cette version ne sera activée/utilisée
        //que pour traiter des sous tableaux dont la taille minimum est supérieure à forkJoinMinSize
      		
		int sizeSubPart = (end - start)+1;
		
	    if(sizeSubPart >= FORK_JOIN_MIN_SIZE) {
	    	   int indiceMilieu = this.start + (sizeSubPart / 2);
	    	   // pas de parametre dans .compute() et donc les tab et indices doivent être renseignés en tant qu'attributs + constructeurs
	    	   MyBasicMultiProc sousCalculGaucheViaForkJoin=new MyBasicMultiProc(tab,start,indiceMilieu-1);
	    	   MyBasicMultiProc sousCalculDroitViaForkJoin=new MyBasicMultiProc(tab,indiceMilieu,end);
	    	  
	    	   sousCalculGaucheViaForkJoin.fork();//déléguer (via potentiel autre thread)
	    	   
	    	   //sous solution A (.compute() ) :
	    	   //Double maxiSousPartie2 = sousCalculDroitViaForkJoin.compute();//faire soit même (via meme thread)
	    	   
	    	   //sous solution B (.fork/join ) :
	    	   sousCalculDroitViaForkJoin.fork();//déléguer (via potentiel autre thread)
	    	   Double maxiSousPartie2 = sousCalculDroitViaForkJoin.join(); //attendre
	    	   
	    	   Double maxiSousPartie1 = sousCalculGaucheViaForkJoin.join(); //attendre 
	    	   
	    	   res = (maxiSousPartie1>maxiSousPartie2)?maxiSousPartie1:maxiSousPartie2;
	    }else {
	    	res = MyBasicAlgo.maxi_ordinaire_subPart(this.tab,this.start,this.end);
	    }
	    return res;

	}
	 
     

}
