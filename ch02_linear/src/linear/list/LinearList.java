package linear.list;

public class LinearList {
    public int[] data;
    public int last;
    public int max_size;
    public LinearList(int size){
        data = new int[size];
        last = -1;
        max_size = size;
    }

    public boolean isFull(){
        if(last == max_size){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        if(last == -1){
            return true;
        }
        else{
            return false;
        }
    }

    public int find_by_index(int index){
        if(index>last){
            System.out.println("error");
            return -1;
        }
        else{
            return data[index];
        }
    }

    public int find_by_value(int val) {
        for (int i = 0; i < last; i++) {
            if (data[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public void insert(int index, int value){
        if(isFull() || index>last){
            System.out.println("error, list is full!");
        }
        else{
            last = last + 1;
            for(int i=last; i>=index; i--){
                data[i+1] = data[i];
            }
            data[index] = value;
        }
    }

    public void delete_by_index(int index){
        if(isEmpty() || index>last){
            System.out.println("error");
        }
        else{
            for(int i=index; i<last-1;i++){
                data[i] = data[i+1];
            }
            last = last - 1;
        }
    }


    public static void main(String[] args) {
        //创建一个顺序表
        LinearList list = new LinearList(20);
        //赋初值
        for(int i=0; i<5;i++){
            list.data[i] = i+1;
        }
        list.last = 5;
        // int result = list.find_by_index(4);
        int result = list.find_by_value(4);
//        System.out.println(result);

        list.insert(4,10);
        list.delete_by_index(3);
        for(int i=0; i<list.last;i++){
            System.out.println(list.data[i]);
        }


    }

}
