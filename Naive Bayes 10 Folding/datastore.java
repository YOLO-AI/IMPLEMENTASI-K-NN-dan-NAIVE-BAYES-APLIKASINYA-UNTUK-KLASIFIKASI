// file: datastore.java
import java.util.*;
import java.io.*;
public class datastore{
	public static classDomain ClassDomain = new classDomain();
	/* struktur:
		|class 	|
		|val1 	|
		|val2 	|
		|val3 	|
	*/
	public static attributeDomainTable AttributeDomainTable = new attributeDomainTable();
	/* struktur:
		|XXXXX		|domain 1 	|domain 2	|domain 3	|dst...		|
		|atribut 1 	|val 1 		|val 2 		|val 3 		|			|
		|atribut 2 	|val 1 		|val 2 		|val 3 		|			|
		|atribut 3 	|val 1 		|val 2 		|val 3 		|			|
		|dst... 	|	 		|	 		|			|			|
	*/
	public static instanceTable DataStore = new instanceTable();
	/* struktur:
		|XXXXX	|atribut 1 	|atribut 2	|atribut 3	|dst...		|
		|row 1 	|val 1 		|val 2 		|val 3 		|			|
		|row 2 	|val 1 		|val 2 		|val 3 		|			|
		|row 3 	|val 1 		|val 2 		|val 3 		|			|
		|dst... |	 		|	 		|			|			|
	*/

	public static void inputInstanceDataStore(String[] tokens){
		// menambahkan sebuah instance ke dalam DataStore

		// menyalin isi tokens menjadi sebuah instance temp
		instance temp = new instance(tokens);

       	// temp ditambahkan pada DataStore
       	DataStore.add(temp);
	}

	public static void inputClassDomain(){
		int index = DataStore.getRow(0).size();
		for(int i = 0; i < DataStore.size(); i++){
			String currentlyObserved = DataStore.getRow(i).getElement(index - 1);
			boolean satisfy = true;
			int j = 0;
			while (j < ClassDomain.size() && satisfy){
				if (currentlyObserved.equals(ClassDomain.getElement(j))){
					satisfy = false;
				}
				j++;
			}
			if(satisfy){
				ClassDomain.add(currentlyObserved);
			}
		}
	}

	public static void inputAttributeDomainTable(){
		int index = DataStore.getRow(0).size();
		for(int i = 0; i < index-1; i++){
			attributeDomain temp = new attributeDomain();
			for(int j = 0; j < DataStore.size(); j++){
				String currentlyObserved = DataStore.getElement(j,i);
				boolean satisfy = true;
				int k = 0;
				while (k < temp.size() && satisfy){
					if (currentlyObserved.equals(temp.getElement(k))){
						satisfy = false;
					}
					k++;
				}
				if(satisfy){
					temp.add(currentlyObserved);
				}
			}
			AttributeDomainTable.add(temp);
		}
	}

	public static void printClass(){
		System.out.println("Kelas-Kelas Klasifikasi:");
       	ClassDomain.printThis();
	}

	public static void printAttribute(){
		System.out.println("Atribut-Atribut dan Domainnya:");
       	AttributeDomainTable.printThis();
	}

	public static void inputDatastore() throws Exception {
    	System.out.print("Input file name: ");
    	Scanner scan = new Scanner(System.in);
    	String inputFile = scan.nextLine();
        String separator = "\\.";
        String[] fileName = inputFile.split(separator);
        for(int i=0; i<fileName.length; i++){
            System.out.println(fileName[i]);
        }
      	String thisLine = null;
      	try{
        	// open input stream test.txt for reading purpose.
         	BufferedReader br = new BufferedReader(new FileReader(inputFile));
         	DataStore = new instanceTable();
         	thisLine = br.readLine();
                while(!thisLine.equals("@data")){
                    thisLine = br.readLine();
                }
                
                while ((thisLine = br.readLine()) != null) {
                        String delims = ",";
                        String[] tokens = thisLine.split(delims);
                        inputInstanceDataStore(tokens); //menambahkan satu baris data ke dataList
         	}       
      	}catch(Exception e){
         	e.printStackTrace();
      	}

      	System.out.println();
      	inputClassDomain();
      	printClass();
      	System.out.println();
       	System.out.println();
      	
       	inputAttributeDomainTable();
       	printAttribute();
       	System.out.println();
       	System.out.println();
  	}

}
