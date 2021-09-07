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

    // 堆排序


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
        sort1.Merge_sort2(0, sort1.list.length-1);
        sort1.Print();
    }
}
