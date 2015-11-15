//file: datastore.java
import java.util.*;
import java.io.*;
public class datastore{
	public static ArrayList<ArrayList<String>> attributeDomainList = new ArrayList<ArrayList<String>>(); //list value dari atribut-atribut
	/* struktur:
		atr1 : val11 val12 val13 dst...
		atr2 : val21 val22 val23 dst...
		atr3 : val31 val32 val33 dts...
	*/

	public static ArrayList<String> classDomainList = new ArrayList<String>(); //list value dari kelas
	/* struktur:
		class
		val1
		val2
		val3
	*/

	public static ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>(); //list berisi data value atribut dan kelas
	/* struktur:
		row1 : valatr1 valatr2 valatr3 dst...
		row2 : valatr1 valatr2 valatr3 dst...
		row3 : valatr1 valatr2 valatr3 dts...
	*/

	public static void inputDatalist(String[] tokens){
		// menambahkan sebuah baris data ke dalam dataList

		// menyalin isi tokens menjadi sebuah ArrayList temp
		ArrayList<String> temp = new ArrayList<String>(); //temp adalah list yang menyimpan value dari atr" dalam satu row
		for (int i = 0; i < tokens.length; i++){ //looping untuk setiap atr
    		temp.add(tokens[i]); //mengisi temp --> ini copying tokens ke temp
       	}

       	// temp ditambahkan pada dataList
       	dataList.add(temp);
	}


	public static void inputClassDomainList(){
		int index = dataList.get(0).size();
		for(int i = 0; i < dataList.size(); i++){
			String currentlyObserved = dataList.get(i).get(index - 1);
			boolean satisfy = true;
			int j = 0;
			while (j < classDomainList.size() && satisfy){
				if (currentlyObserved.equals(classDomainList.get(j))){
					satisfy = false;
				}
				j++;
			}
			if(satisfy){
				classDomainList.add(currentlyObserved);
			}
		}
	}

	public static void inputAttributeDomainList(){
		int index = dataList.get(0).size();
		for(int i = 0; i < index-1; i++){
			ArrayList<String> temp = new ArrayList<String>();
			for(int j = 0; j < dataList.size(); j++){
				String currentlyObserved = dataList.get(j).get(i);
				boolean satisfy = true;
				int k = 0;
				while (k < temp.size() && satisfy){
					if (currentlyObserved.equals(temp.get(k))){
						satisfy = false;
					}
					k++;
				}
				if(satisfy){
					temp.add(currentlyObserved);
				}
			}
			attributeDomainList.add(temp);
		}
	}

	public static void main(String[] args) throws Exception {
    	System.out.print("Input file name: ");
    	Scanner scan = new Scanner(System.in);
    	String inputFile = scan.nextLine();

      	String thisLine = null;
      	try{
        	// open input stream test.txt for reading purpose.
         	BufferedReader br = new BufferedReader(new FileReader(inputFile));
         	while ((thisLine = br.readLine()) != null) {
            	String delims = ",";
            	String[] tokens = thisLine.split(delims);
            	inputDatalist(tokens); //menambahkan satu baris data ke dataList
         	}       
      	}catch(Exception e){
         	e.printStackTrace();
      	}

      	inputClassDomainList();
      	for(int i = 0; i < classDomainList.size(); i++){
      		System.out.println(classDomainList.get(i));
       	}

       	inputAttributeDomainList();
       	for(int i = 0; i < attributeDomainList.size(); i++){
	      	for(int j = 0; j < attributeDomainList.get(i).size(); j++){
	      		System.out.print(attributeDomainList.get(i).get(j));
	       	}
	       	System.out.println();
	    }
  	}


}

