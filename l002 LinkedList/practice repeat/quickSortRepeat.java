public class quickSortRepeat {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int segregate_(int[] arr, int pivotIdx, int sp, int ep) {
        int p = sp - 1, itr = sp;
        while (itr <= ep) {
            if (arr[itr] <= arr[ep]) 
                swap(arr, ++p, itr);
                itr++;
        }
        return p;
    }

    public static void quickSortArray(int[] arr, int si, int ei) {
        if (si > ei)
            return;

        int pivotIdx = ei;
        pivotIdx = segregate_(arr, pivotIdx, si, ei);

        quickSortArray(arr, si, pivotIdx - 1); //faith left
        quickSortArray(arr, pivotIdx + 1, ei); //right faith
    }
    public static void main(String[] args) {
        int arr[] = { -12, 2, 7, 4, 34, 23, 0, -1, -50, 16, 7, 4, 3 };
        quickSortArray(arr, 0, arr.length - 1);
        
        //to display
        for (int ele : arr) {
            System.out.print(ele + " ");
        }
    }
}
