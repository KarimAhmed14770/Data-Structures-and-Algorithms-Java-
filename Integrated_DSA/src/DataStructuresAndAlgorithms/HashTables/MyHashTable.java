package DataStructuresAndAlgorithms.HashTables;

import DataStructuresAndAlgorithms.Arrays.Array;
import DataStructuresAndAlgorithms.LinkedLists.LinkedList;

public class MyHashTable<K extends Comparable<K>,V> {//this hashtable class operates on the
    int size;//to set the size of the hash table
    int count;//to track count of number of elements in the hash table
    Array<LinkedList<Entry<K,V>>> table;

    private static class Entry<A extends Comparable<A> ,D>implements Comparable<Entry<A,D>>{
        A key;
        D value;

        Entry(){
            this.key=null;
            this.value=null;
        }
        Entry(A key,D value){
            this.key=key;
            this.value=value;
        }
        private A getKey(){
            return this.key;
        }
        private D getValue(){
            return this.value;
        }
        @Override
        public int compareTo(Entry<A,D> other){
            return this.key.compareTo( other.key);
        }

    }
    public MyHashTable(int size){
        this.size=size;
        count=0;
        table=new Array<>(size);
        for(int i=0;i<size;i++){
            table.insert(i,new LinkedList<Entry<K,V>>());
        }
    }

    private double getLoadFactor(){
        return (double)count/size;
    }
    private int hashFunction(K key){
        return Math.abs(key.hashCode()%size);//note that integers hashcode is the same as the integer itself
    }
    private void rehash(){
        size*=2;
        count=0;
        int index;
        Array<LinkedList<Entry<K,V>>> newTable=new Array<>(size);
        for(int i=0;i<size;i++){
            newTable.insert(i,new LinkedList<Entry<K,V>>());
        }
        for(LinkedList<Entry<K,V>> Bucket:table){
            for(Entry<K,V> entry:Bucket){
                if(entry==null){
                    break;
                }
                index=hashFunction(entry.key);
                newTable.getElement(index).insertInSorted(entry);
                count++;
            }
        }
        this.table=newTable;


    }

    public void put(K key,V value){
        int index=hashFunction(key);
        Entry<K,V> newEntry=new Entry<>(key,value);
        if(!(table.getElement(index).searchModified(newEntry))){
            table.getElement(index).insertInSorted(newEntry);//if element isn't found insert it in sorted order
            count++;
        }else{
            table.getElement(index).update(newEntry);
        }
        if(getLoadFactor()>=0.75){
            rehash();
        }


    }


    public V get(K key){
        int index=hashFunction(key);
        V dummy=null;
        Entry<K,V> newEntry=new Entry<>(key,dummy);
        Entry<K,V> found=table.getElement(index).find(newEntry);
        if(found!=null)
        {
            return found.getValue();
        }
        else return null;
        //if element isn't found insert it in sorted order
    }

    public void remove(K key){
        int index=hashFunction(key);
        V dummy=null;
        Entry<K,V> newEntry=new Entry<>(key,dummy);
        int found=table.getElement(index).indexOf(newEntry);
        if(found!=-1){
            table.getElement(index).delete(found);
            count--;
        }
    }

    @Override
    public String toString(){
        /*there is a small "pro-tip" regarding your toString logic. Using += with Strings inside nested
        loops is a performance trap. In Java, Strings are immutable, so every time you use +=, Java creates a
        brand new String object and copies the old data into it. For a large table, this becomes very slow
        (O(n^2)).

        old code
        String a="Table Size: "+size+"\nElements Count: "+count+"\n";
        String tableData="";
        int i=0;
        boolean found=false;
        for(LinkedList<Entry<K,V>> buckets:this.table){
            tableData+=i+": ";
            for(Entry<K,V> entry:buckets){
                tableData+="{ "+entry.key+" : "+entry.value+"} , ";
                found=true;
            }
            if(found) {
                tableData += "\b\b}";
            }
            else{
                tableData+="\b";
            }
            tableData+="\n";
            i++;
            found=false;
        }
        return tableData;

        better is to use stringbuilder
         */
            StringBuilder sb = new StringBuilder();
            sb.append("Table Size: ").append(size).append("\n");
            sb.append("Elements Count: ").append(count).append("\n");
            sb.append("Load Factor: ").append(getLoadFactor()).append("\n");

            int i = 0;
            // Outer loop: Using your Array iterator
            for (LinkedList<Entry<K, V>> bucket : this.table) {
                sb.append(i).append(": ");

                boolean bucketHasData = false;
                // Inner loop: Using your LinkedList iterator
                for (Entry<K, V> entry : bucket) {
                    sb.append("{ ").append(entry.key).append(" : ").append(entry.value).append(" } -> ");
                    bucketHasData = true;
                }

                if (bucketHasData) {
                    sb.append("null"); // Shows the end of the chain
                }

                sb.append("\n");
                i++;
            }
            return sb.toString();

    }






}



/*hashtable test

        MyHashTable<Integer,String> hashTable=new MyHashTable<>(10);

        hashTable.put(11,"Reda");
        hashTable.put(13,"alaa");
        hashTable.put(12,"elbahar");
        hashTable.put(15,"tawfik");
        hashTable.put(1,"Karim");
        hashTable.put(3,"Marwan");
        System.out.println("Table 1:");
        System.out.println(hashTable);
        hashTable.put(2,"Ola");
        hashTable.put(5,"Ahmed");

        System.out.println("Table 2:");
        System.out.println(hashTable);

        hashTable.put(1,"Bonty");
        hashTable.put(3,"Ga7ed");


      hashTable.remove(1);
      //System.out.println(hashTable);
      hashTable.put(1,"Karim");

        //System.out.println(hashTable);

        MyHashTable<String,String> hashTable2=new MyHashTable<>(10);

        hashTable2.put("A","Reda");
        hashTable2.put("B","alaa");
        hashTable2.put("C","elbahar");
        hashTable2.put("D","tawfik");
        hashTable2.put("E","Karim");
        hashTable2.put("F","Marwan");
        System.out.println("Table 1:");
        System.out.println(hashTable2);
        hashTable2.put("G","Ola");
        hashTable2.put("H","Ahmed");

        System.out.println("Table 2:");
        System.out.println(hashTable2);

        hashTable2.put("I","Bonty");
        hashTable2.put("J","Ga7ed");
    }




* */
