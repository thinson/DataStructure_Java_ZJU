package seq.stack;
/*
    该程序是在一个数据里实现两个堆栈的一种数据结构
 */
public class DoubleStack {
    private int MAXSIZE;
    private int[] data;
    private int top1;
    private int top2;

    //需要注意的是单栈为空填充-1，指向的是有值的部分
    public DoubleStack(int maxsize){
        this.MAXSIZE = maxsize;
        this.data = new int[MAXSIZE];
        this.top1 = -1;
        this.top2 = maxsize;
    }

    public boolean isEmpty(){
        return (this.top1 == -1) && (this.top2 == this.MAXSIZE + 1);
    }

    public boolean isFull(){
        return this.top1 + 1 == this.top2;
    }

    public int get_len_stack1(){
        return this.top1 + 1;
    }

    public int get_len_stack2(){
        return this.MAXSIZE - this.top2;
    }

    public int get_len_total(){
        return get_len_stack1() + get_len_stack2();
    }

    public void push1(int val){
        if(isFull()){
            System.out.println("full");
        }
        else{
            top1++;
            this.data[top1] = val;
        }
    }

    public void push2(int val){
        if(isFull()){
            System.out.println("full");
        }
        else{
            top2--;
            this.data[top2] = val;
        }
    }

    public int pop1(){
        if(isEmpty()){
            return -1;
        }
        else{
            top1--;
            return this.data[top1+1];
        }
    }

    public int pop2(){
        if(isEmpty()){
            return -1;
        }
        else{
            top2++;
            return this.data[top2-1];
        }
    }

    public void show_all_elements(){
        System.out.println("STACK1:");
        for(int i=0; i<top1+1; i++){
            System.out.println(this.data[i]);
        }
        System.out.println("STACK2:");
        for(int i=this.MAXSIZE-1; i>top2-1; i--){
            System.out.println(this.data[i]);
        }
    }

    public static void main(String[] args){
        DoubleStack d_stack = new DoubleStack(10);
        for(int i=0; i<4; i++){
            d_stack.push1(i);
            d_stack.push2(i*2);
        }
        System.out.println("COUNT:");
        System.out.println(String.format("cnt1:%d, cnt2:%d, cnt:%d", d_stack.get_len_stack1(), d_stack.get_len_stack2() ,d_stack.get_len_total()));
        d_stack.show_all_elements();
        System.out.println("POP1:");
        System.out.println(d_stack.pop1());
        System.out.println("POP2:");
        System.out.println(d_stack.pop2());
        System.out.println(d_stack.pop2());
        System.out.println("COUNT:");
        System.out.println(String.format("cnt1:%d, cnt2:%d, cnt:%d", d_stack.get_len_stack1(), d_stack.get_len_stack2() ,d_stack.get_len_total()));
        d_stack.show_all_elements();
    }



}
