package linked.stack;

public class LinkedStack<T> {
    private class Node{
        private T t;
        //可以直接套娃，内存里是如何存储的呢？
        private Node next;

        public Node(T t, Node next){
            this.t = t;
            this.next = next;
        }

        public Node(T t){
            this.t = t;
            this.next = null;
        }

        public Node(){
            this.next = null;
        }
    }

    //定义堆栈本身需要的一些变量

    private Node head;
    private int size;

    //size直接放在头节点上吧
    //private int size;
    public LinkedStack(){
        this.head = new Node();
    }

    public int get_len(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
//    链表不用考虑这个情况
//    public boolean isFull(){
//
//    }
    public void push(T t){
        //在尾节点处进行push操作，每次都需要遍历一遍以找到尾指针，非常麻烦
        //故在头节点出进行push和pop操作
        if(this.size==0){
            Node s = new Node(t);
            this.head.next = s;
            this.size++;
        }
        else{
            //然而发现其实不分情况也是一样的
            Node s = new Node(t);
            s.next = this.head.next;
            this.head.next = s;
            this.size++;
        }
    }

    public T pop(){
        if(isEmpty()){
            return null;
        }
        else{
            Node s = this.head.next;
            this.head.next = s.next;
            this.size--;
            return s.t;
        }
    }

    public void show_all_elements(){
        if(isEmpty()) System.out.println("EMPTY");
        else{
            System.out.println("Stack:");
            Node s = this.head;
            for(int i=0;i<size;i++){
                s = s.next;
                System.out.println(s.t);
            }
        }
    }

    public static void main(String[] args){
        LinkedStack<Integer> stack1 = new LinkedStack<Integer>();
        for(int i=0;i<5;i++){
            stack1.push(i*2);
        }
        stack1.show_all_elements();
        System.out.println("POP:");
        System.out.println(stack1.pop());
        stack1.show_all_elements();
        System.out.println("NUM:");
        System.out.println(stack1.get_len());
    }



}
