//file: naiveBayes.java

public class kNN {

    public float accuracy;
    public instanceTable dataTest, dataTraining;
    public datastore DataStore;
    public String[] trueAns, preAns;
    public int[] jarak ;
    public boolean[] flagjarak ;
    public int k;
    public int[] indexjawab;
    public int[] arrklasifikasi ;
    public int countpredicted;

    public kNN(instanceTable datatest, instanceTable datatraining, datastore DataStore, int k) {
        this.dataTest = new instanceTable(datatest);
        this.dataTraining = new instanceTable(datatraining);
        this.k = k;
        this.DataStore = DataStore;
        trueAns=new String[dataTest.size()];
        preAns=new String[dataTest.size()];
        jarak= new int[dataTest.size()];
        flagjarak= new boolean[dataTest.size()];
        arrklasifikasi= new int[DataStore.ClassDomain.classDomainList.size()];
        indexjawab = new int[k];

        for (int i = 0; i < datatraining.size(); i++) {
            inisialisasiflag();
            inisialisasiarrklasifikasi();
            isiJarak(datatest.getRow(i));
            sortJarak();
            isiklasifikasi();
            int maxklas=0;
            int indmaxklas=0;
            for (int j =0;j<k - 1;j++){
                if (maxklas<arrklasifikasi[j]){
                    maxklas=arrklasifikasi[j];
                    indmaxklas=j;
                }
            }
            preAns[i]=DataStore.ClassDomain.getElement(indmaxklas);   
        }
        calculateAccuracy();
    }

    public void inisialisasiflag() {
        for (int i = 0; i < dataTest.size(); i++) {
            flagjarak[i] = false;
        }
    }

    public void inisialisasiarrklasifikasi() {
        for (int i = 0; i < DataStore.ClassDomain.classDomainList.size(); i++) {
            arrklasifikasi[i] = 0;
        }
    }

    public void isiJarak(instance data) {
        //isi jarak setiap instance dibandingkan dengan data training
        for (int i = 0; i < dataTraining.size(); i++) {
            jarak[i] = 0;
            for (int j = 0; j < data.size() - 1; j++) {
                if (!data.getElement(j).equals(dataTraining.getElement(i, j))) {
                    jarak[i] = jarak[i] + 1;
                }
            }
        }
    }

    public void sortJarak() {
        //ambil k index dengan jarak terkecil
        for (int i = 0; i < k; i++) {
            int min = jarak[0];
            int indexmin = 0;
            for (int j = 1; j < dataTraining.size(); j++) {
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
            for (int j = 0; j < DataStore.ClassDomain.classDomainList.size(); j++) {
                if (dataTraining.getElement(indexjawab[i], dataTraining.getRow(i).size() - 1).equals(DataStore.ClassDomain.getElement(j))) {
                    arrklasifikasi[j]++;
                }
            }
        }
    }
    
    public void calculateAccuracy(){
        countpredicted = 0;
        int j;
        for ( j = 0; j < dataTest.size(); j++) {
            if (dataTraining.getElement(j, dataTraining.getRow(j).size() - 1).equals(preAns[j])) {
                countpredicted += 1;
            }
        }
        System.out.println(countpredicted);
        System.out.println(j);
        accuracy = (float) ((float)countpredicted / j);       
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
}
