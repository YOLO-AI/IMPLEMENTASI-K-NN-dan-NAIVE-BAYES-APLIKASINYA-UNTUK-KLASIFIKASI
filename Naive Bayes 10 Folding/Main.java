// file: Main.java
public class Main{
	public static void main(String[] args) throws Exception{
		datastore Datastore = new datastore();
		Datastore.inputDatastore();

		tenFoldCrossValidationNB TenFoldCrossValidationNB = new tenFoldCrossValidationNB();
		TenFoldCrossValidationNB.mulai();

		probabilityNB ProbabilityNB = new probabilityNB();
		NaiveBayes _NaiveBayes = new NaiveBayes();
		ProbabilityNB = _NaiveBayes.makeModel(datastore.DataStore);
	}
}