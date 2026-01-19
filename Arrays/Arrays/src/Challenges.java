import ADT.Array;

public class Challenges {
    /*challenge1:
     * you have an arithmetic sequence with a known number that is added on the element to produce the next
     * element, we want a function that checks if there are missing elements in the sequence and return them\
     * in an array
     * */

    /*this is for the special case that one element is missing from a natural summation sequence diff=1
     * complexity is o(1)
     * */
    public static int checkSingleMissingElementNatural(Array sequence) {
        int start = sequence.getElement(0);
        int end = sequence.getElement(sequence.getCount() - 1);
        int startSum = (start * (start + 1)) / 2;
        int endSum = (end * (end + 1)) / 2;
        int sum = endSum - startSum + start;
        int actualSum = sequence.sum();

        return sum - actualSum;
    }

    /*this is a general case that finds the missing elements from any sequence
     * complexity is O(n), the loop for printing missing elements is very small so i neglect it from
     * complexity analysis*/
    public static Array checkMissingElementsInSequence(Array sequence, int diff) {
        if (diff == 0) {
            return new Array(0);
        }
        Array missingElements = new Array();
        int actualDiff = 0;
        int numberOfMissingElements = 0;
        for (int i = 0; i < sequence.getCount() - 1; i++) {
            actualDiff = sequence.getElement(i + 1) - sequence.getElement(i);
            numberOfMissingElements = (actualDiff / diff) - 1;
            for (int j = 1; j <= numberOfMissingElements; j++) {
                missingElements.append(sequence.getElement(i) + (diff * j));
            }
        }

        return missingElements;
    }

    /*using very simple hash table to search for the missing element it is still O(n),O(n)for creating
    the hash table and O(n) for searching for missing elements
    * but the previous method is faster as here we loop for creating the hash table and then loop
    * to find the missing elements, this is customized only for natural number sequence*/
    public static Array checkMissingElementsHashTable(Array sequence) {
        Array missing = new Array(1);
        int[] hashTable = new int[sequence.max() + 1];

        for (int i = 0; i <= sequence.getCount() - 1; i++) {
            hashTable[sequence.getElement(i)]++;
        }
        for (int i = 1; i < sequence.max() + 1; i++) {
            if (hashTable[i] == 0) {
                missing.append(i);
            }
        }
        return missing;
    }


    /*challenge 2:
     *we have a list which is sorted or unsorted and we want to find duplicates and each duplicate count
     * */
    public static Array[] getDuplicatesSorted(Array sequence) {
        Array duplicates = new Array();
        Array counts = new Array();
        Array[] result = {duplicates, counts};
        int lastDuplicate = 0;
        int j=0;
        int count = 0;
        for (int i = 0; i < sequence.getCount() - 1; i++) {
            if (sequence.getElement(i) == sequence.getElement(i + 1)
                && sequence.getElement(i)!=lastDuplicate) {
                duplicates.append(sequence.getElement(i));
                lastDuplicate=sequence.getElement(i);
                count=1;
                while((i<sequence.getCount()-1)
                        &&sequence.getElement(i)==sequence.getElement(i+1)){
                    i++;
                    count++;
                }
                counts.append(count);
                }
            }

        return result;
    }

    public static Array[] getDuplicatesHashing(Array sequence){
        Array duplicates = new Array();
        Array counts = new Array();
        Array[] result = {duplicates, counts};
        int[] hashTable=new int[sequence.max()+1];
        int count;
        for(int i=0;i<sequence.getCount();i++ ){
            hashTable[sequence.getElement(i)]++;
        }
        for(int i=0;i<hashTable.length;i++){
            if(hashTable[i]>1){
                duplicates.append(i);
                counts.append(hashTable[i]);

            }
        }

        return result;
    }

    public static void main(String[] Args) {
        int[] arr = {1, 2, 3, 4, 6, 7, 10, 11, 12, 14};
        Array sequence = new Array(arr);
        Array missingelements = checkMissingElementsInSequence(sequence, 1);
        missingelements.display();

        int[] arr2 = {10, 11, 12, 14, 15, 16, 17, 18, 19};
        Array sequence2 = new Array(arr2);
        System.out.println(checkSingleMissingElementNatural(sequence2));

        missingelements = checkMissingElementsHashTable(sequence);
        missingelements.display();

        int[] arr3 = {1, 2, 2, 2, 2, 3, 4, 6, 6, 7, 10, 11, 11, 12, 14,15,15,15,15,15,17,18,20,20};
        Array sequence3 = new Array(arr3);
        Array[] result = getDuplicatesSorted(sequence3);
        for (int i = 0; i < result[0].getCount(); i++) {
            System.out.println(result[0].getElement(i) + " : "+result[1].getElement(i));
        }
        System.out.println();
        System.out.println();
        result = getDuplicatesHashing(sequence3);
        for (int i = 0; i < result[0].getCount(); i++) {
            System.out.println(result[0].getElement(i) + " : "+result[1].getElement(i));
        }

        int[] arr4 = {10, 2, 221, 2, 23, 2,3, 4,2,44,66, 6,70,70 ,6, 7, 10, 11, 11, 12, 14,
                15,15,15,15,15,17,18,20,15,30,20};
        Array sequence4 = new Array(arr4);
        System.out.println();
        System.out.println();
        result = getDuplicatesHashing(sequence4);
        for (int i = 0; i < result[0].getCount(); i++) {
            System.out.println(result[0].getElement(i) + " : "+result[1].getElement(i));
        }
    }
}

