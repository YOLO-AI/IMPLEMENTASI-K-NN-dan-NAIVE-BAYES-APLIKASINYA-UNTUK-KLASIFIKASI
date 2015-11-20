// file: Main.java
public class Main{
	public static void main(String[] args) throws Exception{
                //datastore Datastore = new datastore();	
                //Datastore.inputDatastore();
                datastore.inputDatastore();
               
                naiveBayes NaiveBayes = new naiveBayes();
                NaiveBayes.mulai(datastore.DataStore, datastore.DataStore);
                
		//tenFoldCrossValidationNB TenFoldCrossValidationNB = new tenFoldCrossValidationNB();
		//tenFoldCrossValidationNB.mulai();
                
                //kNN knn = new kNN(datastore.DataStore, datastore.DataStore, Datastore, 5);
                //knn.printklasifikasi();
                //knn.printAccuracy();
	}
}