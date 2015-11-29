// file: Main.java
public class MainKNN{
	public MainKNN() throws Exception{
		datastore Datastore = new datastore();
		Datastore.inputDatastore();

		kNN knn= new kNN(Datastore.DataStore,Datastore.DataStore,Datastore,5);
                knn.printklasifikasi();
                knn.printAccuracy();
	}
}