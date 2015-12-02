// file: instance.java
import java.util.*;
import java.io.*;
public class instance{
	// kelas ini merepresentasikan struktur data satu baris data

	public ArrayList<String> instanceList;

	public instance(){
		 instanceList = new ArrayList<String>();
	}

	public instance(String[] values){
		instanceList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		instanceList.add(values[i]);
       	}
	}
        
        public instance(ArrayList<String> values){
		instanceList = new ArrayList<String>();
		for (int i = 0; i < values.size(); i++){
    		instanceList.add(values.get(i));
       	}
	}

	public void setInstanceList(String[] values){
		instanceList = new ArrayList<String>();
		for (int i = 0; i < values.length; i++){
    		instanceList.add(values[i]);
       	}
	}

	public void addElement(String value){
		instanceList.add(value);
	}

	public void add(String value){
		instanceList.add(value);
	}

	public int size(){
		return instanceList.size();
	}

	public String getElement(int attribute){
		return instanceList.get(attribute);
	}

	public void printThis(){
		for (int i = 0; i < size(); i++){
			System.out.print(getElement(i) + " ");
		}
	}
}
