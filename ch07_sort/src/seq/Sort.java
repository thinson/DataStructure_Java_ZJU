package seq;
import java.util.Arrays;

public class Sort {
    public int[] list = {5,3,4,9,6,11,15,7,2,22,17};
    //public int[] list = {3,5,4,9};
    public int[] tmp_list  = new int[list.length];
    public int length = this.list.length;

    // 冒泡排序
    public void Bubble_sort(){
        int tmp;
        for(int j=length; j>1; j--) {
            for (int i = 0; i < j - 1; i++) {
                if (this.list[i] > this.list[i + 1]) {
                    tmp = this.list[i];
                    this.list[i] = this.list[i + 1];
                    this.list[i + 1] = tmp;
                }
            }
        }
    }

    // 插入排序
    // 待排数组和tmp数组不断比较
    public void Insertion_sort(){
        if(list.length == 0 ) return;
        tmp_list[0] = list[0];
        for(int i=1; i<length; i++){
            for(int j=i-1; j>=0 ; j--){
                if (list[i]>tmp_list[j]){
                    tmp_list[j+1] = list[i];
                    break;
                }
                else if (j==0) {
                    tmp_list[j+1] = tmp_list[j];
                    tmp_list[j] = list[i];
                }
                else {
                   tmp_list[j+1] = tmp_list[j];
                }
            }
        }
        for(int i=0;i<length;i++){
            list[i] = tmp_list[i];
        }
    }

    // 堆排序



    // 快速排序
    public void Fast_sort(int L, int R_End){
        // 递归结束的条件
        if(R_End <= L) return;
        else{
            // 选主元
            int tmp;
            int center = (L + R_End) / 2;
            // 手动冒泡排序
            if( list[center] < list[L]){
                tmp = list[center];
                list[center] = list[L];
                list[L] = tmp;
            }
            if( list[center] > list[R_End] ){
                tmp = list[center];
                list[center] = list[R_End];
                list[R_End] = tmp;
            }
            if( list[center] < list[L]){
                tmp = list[center];
                list[center] = list[L];
                list[L] = tmp;
            }

            // center和R_End-1互换
            tmp = list[center];
            list[center] = list[R_End-1];
            list[R_End-1] = tmp;

            // 比主元小的放在左边，比主元大的放在右边
            int i = L+1;
            int j = R_End - 2;
            while(true){
                if(list[i] < list[R_End - 1]) i++;
                if(list[j] > list[R_End - 1]) j--;
                if(i<j){
                    tmp = list[i];
                    list[i] = list[j];
                    list[j] = tmp;
                }
                else{
                    tmp = list[i];
                    list[i] = list[R_End-1];
                    list[R_End-1] = tmp;
                    break;
                }
            }

            // 递归左边和右边
            Fast_sort(L, i-1);
            Fast_sort(i+1, R_End);
        }
    }


    // 归并排序
    // 左半边排序，右半边排序，左右子列merge
    public void Merge(int L, int R, int R_End){
        int i=L,j=R,k=L;
        while(i<=R-1 && j<=R_End){
            if(list[i]<list[j]){
                tmp_list[k] = list[i];
                k++;
                i++;
            }
            else{
                tmp_list[k] = list[j];
                k++;
                j++;
            }
        }
        while(i<=R-1){
            tmp_list[k] = list[i];
            k++;
            i++;
        }
        while(j<=R_End){
            tmp_list[k] = list[j];
            k++;
            j++;
        }

        for(i = L;i <= R_End; i++){
            list[i] = tmp_list[i];
        }
    }

    // 归并排序的递归写法
    public void Merge_sort(int L, int R_End){
        if(L < R_End){
            // 左分支排序，右分支排序，左右分支归并
            int center = (L + R_End) / 2;
            Merge_sort(L, center);
            Merge_sort(center+1, R_End);
            Merge(L, center+1, R_End);
        }
    }

    // 归并排序的非递归写法
    // 两个小单元merge成一个大单元，依次类推
    public void Merge_sort2(int L, int R_End){
        int list_len =  R_End-L+1;
        int i;
        // sub_len 表示一个子列的长度，依次*2
        for(int sub_len = 1; sub_len <= list_len; sub_len = sub_len*2){
            // 前面的都按照分组，进行归并
            for(i = L; i + 2 * sub_len <= list_len; i = i + 2*sub_len){
                Merge(i, i+sub_len,i + 2*sub_len-1);
            }

            // 后面的再单独进行归并
            // 不足两个子列，但满足一个子列多一部分
            if(i + sub_len < list_len){
                Merge(i, i+sub_len, R_End);
            }
            // 不足一个子列就没法merge，不用管，默认就在list内部
        }
    }


    // 用于打印数组
    public void Print(){
        System.out.println("list: " + Arrays.toString(this.list));
        System.out.println("tmp_list: " + Arrays.toString(this.tmp_list));
    }

    public static void main(String[] args){
        Sort sort1 = new Sort();
        sort1.Print();
        // sort1.Bubble_sort();
        // sort1.Print();
        // sort1.Merge(0,2,3);
        // sort1.Merge_sort2(0, sort1.list.length-1);
        // sort1.Insertion_sort();
        sort1.Fast_sort(0, sort1.list.length-1);
        sort1.Print();
    }
}
