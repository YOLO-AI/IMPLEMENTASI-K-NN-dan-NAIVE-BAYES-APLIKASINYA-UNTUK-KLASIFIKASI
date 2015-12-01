
import static java.lang.Integer.min;

//file: naiveBayes.java

public class kNN {

    public float accuracy;
    public instanceTable dataTest, dataTraining;
    public String[] trueAns, preAns;
    public float[] jarak ;
    public boolean[] flagjarak ;
    public int k;
    public int[] indexjawab;
    public int[] arrklasifikasi ;
    public int countpredicted;

    public float[] maxnumeric;
    public float[] minnumeric;
    

    public int[][] Matrix = new int[datastore.ClassDomain.size()][datastore.ClassDomain.size()];


    public kNN(instanceTable datatest, instanceTable datatraining, int k) {
        this.dataTest = new instanceTable(datatest);
        this.dataTraining = new instanceTable(datatraining);
        this.k = min(k,dataTraining.size());
        trueAns=new String[dataTest.size()];
        preAns=new String[dataTest.size()];
        jarak= new float[dataTraining.size()];
        flagjarak= new boolean[dataTraining.size()];
        arrklasifikasi= new int[datastore.ClassDomain.classDomainList.size()];
        indexjawab = new int[this.k];
        
        maxnumeric= new float[datastore.AttributeDomainTable.size()];
        minnumeric= new float[datastore.AttributeDomainTable.size()];
        for (int i=0; i < datastore.AttributeDomainTable.size();i++){
            if (datastore.AttributeDomainTable.getElement(i,0).equals("numeric")){
                float max=Float.parseFloat(datatraining.getElement(0, i));
                float min=Float.parseFloat(datatraining.getElement(0, i));
                for (int j=1; j< datatraining.size();j++){
                    if(max<Float.parseFloat(datatraining.getElement(j, i))){
                        max=Float.parseFloat(datatraining.getElement(j, i));
                    }
                    if(min>Float.parseFloat(datatraining.getElement(j, i))){
                        min=Float.parseFloat(datatraining.getElement(j, i));
                    }
                }
                maxnumeric[i]=max;
                minnumeric[i]=min;
            }
        }
        
        //datastore.AttributeDomainTable.getelement()
        for (int i = 0; i < dataTest.size(); i++) {
            inisialisasiflag();
            inisialisasiarrklasifikasi();
            isiJarak(dataTest.getRow(i));
            sortJarak();
            isiklasifikasi();
            int maxklas=0;
            int indmaxklas=0;
            for (int j =0;j < datastore.ClassDomain.classDomainList.size() ;j++){
                if (maxklas<arrklasifikasi[j]){
                    maxklas=arrklasifikasi[j];
                    indmaxklas=j;
                }
            }
            preAns[i]=datastore.ClassDomain.getElement(indmaxklas);   
        }
        calculateAccuracy();
    }

    public void inisialisasiflag() {
        for (int i = 0; i < dataTraining.size(); i++) {
            flagjarak[i] = false;
        }
    }

    
    public void inisialisasiarrklasifikasi() {
        for (int i = 0; i < datastore.ClassDomain.classDomainList.size(); i++) {
            arrklasifikasi[i] = 0;
        }
    }

    public void isiJarak(instance data) {
        //isi jarak setiap instance dibandingkan dengan data training
        for (int i = 0; i < dataTraining.size(); i++) {
            jarak[i] = 0;
            for (int j = 0; j < data.size() - 1; j++) {
                if (datastore.AttributeDomainTable.getElement(j,0).equals("numeric")){
                    jarak[i]=jarak[i]+((Float.parseFloat(data.getElement(j))-minnumeric[j])/(maxnumeric[j]-minnumeric[j]));
                }
                
                if (!data.getElement(j).equals(dataTraining.getElement(i, j))) {
                    jarak[i] = jarak[i] + 1;
                }
                
            }
        }
    }

    public void sortJarak() {
        //ambil k index dengan jarak terkecil
        for (int i = 0; i < k; i++) {
            int a=0;
            while (flagjarak[a]==true){
                a++;
            }
            float min = jarak[a];
            int indexmin = a;
            for (int j = 0; j < dataTraining.size(); j++) {
                if (min > jarak[j] && flagjarak[j] == false) {
                    min = jarak[j];
                    indexmin = j;
                }
            }
            indexjawab[i] = indexmin;
            flagjarak[indexmin] = true;

        }
    }

    public void isiklasifikasi() {
        //dari k isi array klasifikasi
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < datastore.ClassDomain.classDomainList.size(); j++) {
                if (dataTraining.getElement(indexjawab[i], dataTraining.getRow(i).size() - 1).equals(datastore.ClassDomain.getElement(j))) {
                    arrklasifikasi[j]++;
                }
            }
        }
    }
    
    public void calculateAccuracy(){
        countpredicted = 0;
        int j;
        for ( j = 0; j < dataTest.size(); j++) {
            if (dataTest.getElement(j, dataTest.getRow(j).size() - 1).equals(preAns[j])) {
                countpredicted += 1;
            }
        }
        accuracy = (float) ((float)countpredicted / j); 
    }
    
    public void confusionMatrix(){
        for (int a = 0; a < Matrix.length; a++){
            for (int b = 0; b < Matrix.length; b++){
                Matrix[a][b] = 0;
            }
        }
        int j;       
        for ( j = 0; j < dataTest.size(); j++) {
            int lock = 0;
            int k = 0, m = 0;
            //if (dataTest.getElement(j, dataTest.getRow(j).size() - 1).equals(preAns[j])) {
            while (k < datastore.ClassDomain.size() && !dataTest.getElement(j, dataTest.getRow(j).size() - 1).equals(datastore.ClassDomain.getElement(k))){
                k++;
            }
            
            while (m < datastore.ClassDomain.size() && !preAns[j].equals(datastore.ClassDomain.getElement(m))){
                m++;
            }
            
            Matrix[k][m] = Matrix[k][m] + 1;
        }        
    }
    
    public void printConfusionMatrix(){
        confusionMatrix();
        System.out.print("      ");
        for (int a = 0; a < datastore.ClassDomain.size(); a++){
            System.out.print(datastore.ClassDomain.getElement(a) + "        ");
        }
        System.out.println();
        for (int a = 0; a < Matrix.length; a++){
            System.out.print(datastore.ClassDomain.getElement(a) + "    ");
            for (int b = 0; b < Matrix.length; b++){
                System.out.print(Matrix[a][b] + "           ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void printklasifikasi(){
        for(int i=0;i<dataTest.size();i++){
            System.out.println(preAns[i]);
        }
    }

    public void printAccuracy() {
        System.out.print("Akurasi Model Probabilitas:");
        System.out.println(accuracy*100 + "%");
    }
    
    public void printClass(){
        System.out.println(preAns[0]);
    }
}
