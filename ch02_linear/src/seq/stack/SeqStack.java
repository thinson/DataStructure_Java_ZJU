package seq.stack;


//数组实现堆栈，顺序存储
public class SeqStack {
    private int MAXSIZE;
    //这样能不能实例化呢，生命周期的问题
    private int[] data; //= new int[MAXSIZE];
    private int top;

    public SeqStack(int maxsize){
        this.MAXSIZE = maxsize;
        this.top = -1;
        //不能实例化泛型
        this.data = new int[maxsize];
    }

    public boolean isFull(){
        if(top+1 == MAXSIZE){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isEmpty(){
        if(top==-1){
            return true;
        }
        else{
            return false;
        }
    }

    public int get_length(){
        return this.top+1;
    }

    public void push(int value){
        if(isFull()){
            System.out.println("error");
        }
        else{
            top = top+1;
            this.data[top] = value;
        }
    }

    public int pop(){
        if(isEmpty()){
            return -1;
        }

        else{
            top = top - 1;
            return this.data[top+1];
        }
    }

    public void show_all_elements(){
        if(isEmpty()){
            System.out.println("Empty");
        }
        else{
            for(int i=0;i<top+1;i++){
                System.out.println(this.data[i]);
            }
        }
    }


    public static void main(String[] args){
        SeqStack stack1 = new SeqStack(5);
        for(int i=0;i<5;i++){
            stack1.push(i);
        }
        System.out.println(stack1.isFull());
        stack1.show_all_elements();

        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());

        stack1.show_all_elements();
        System.out.println(stack1.get_length());
    }

}
