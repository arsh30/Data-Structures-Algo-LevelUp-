import java.util.Random;

public class quickSortArray {

    public static Random rand = new Random();
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int segregate(int[] arr, int pivotIdx, int sp, int ep) {
        int p = sp - 1, itr = sp; // p is pointer   agar sp wala pointer uss index pr aajaye jiske around segregate krna h to correct location pr aajayege isliye last index pr late h  so jidr sp pointer hai vo sabse chote ik taraf hoje
        while (itr <= ep) { // itr jb tk ending point se chota h coz itr last tk chlega
            if (arr[itr] <= arr[ep]) //data ko check krra idr
                swap(arr, ++p, itr);
            itr++;
        }
        return p;
    }

    //time complex : average: Nlog N
    // worst case : O(N^2)  //ex decreasing order sort and 1st wala hi sbse aayega rhega
    public static void quickSort(int[] arr, int si, int ei) {
        if (si > ei) //idr hum >= bhi lga skte hai coz kyoki akela elemnent khud me sorted hi hota h
            return;

        // int pivotIdx = ei;  //type 1 at the end pivotidx why it work coz upr hmne swap kra hua h

        // int pivotIdx = si;  //pivotind in the starting
        // int pivotIdx = rand.nextInt(ei - si + 1) + si; //random pivot index

        int pivotIdx = (si + ei) / 2;  //mid pivot index : 4 types of pivot index

        pivotIdx = segregate(arr, pivotIdx, si, ei);

        //for optimaztion :  segreagate k baad check krege abhi vese bhi for loop chl hi rha h and check hohi rha h iss range me aane wala
       //==========================================================================================================================================================
        // boolean flag = true;

        // for (int i = si + 1; i <= ei; i++)
        //     if (arr[i - 1] > arr[i]) { //agar next value is biggere than prev toh mean array  is not sorted
        //         flag = false;
        //         break;
        //     }
        // if (flag)
        //     return; //means aage kyo jare ho array is sorted
        //==========================================================================================================================================================
            
    
        quickSort(arr, si, pivotIdx - 1); //faith ki left lekr aajega
        quickSort(arr, pivotIdx + 1, ei);
    }
    
    public static void main(String[] args) {
        //ik array bnakr pass kra
        int[] arr = { -12, 2, 7, 4, 34, 23, 0, 1, -1, -50, 16, 23, 7, 4, 2, 3 };

        //call quicksrt
        quickSort(arr, 0, arr.length - 1);

        // to display the array
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }
}
