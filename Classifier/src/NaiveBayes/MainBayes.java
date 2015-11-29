// file: MainBayes.java
public class MainBayes{
	public MainBayes() throws Exception{
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