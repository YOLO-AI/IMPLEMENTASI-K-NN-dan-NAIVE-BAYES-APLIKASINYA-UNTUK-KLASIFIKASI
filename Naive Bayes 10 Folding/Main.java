// file: Main.java
public class Main{
	public static void main(String[] args) throws Exception{
		datastore Datastore = new datastore();
		Datastore.mulai();
		NaiveBayes _NaiveBayes = new NaiveBayes(datastore.DataStore);
		_NaiveBayes.mulai();
	}
}