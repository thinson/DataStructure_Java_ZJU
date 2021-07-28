package linked.list;

// 这里使用泛型来设计类
public class LinkedList<T> {
    private class Node{
        // T 表示的是一个数据类型
        private T t;
        private Node next;
        public Node(T t,Node next){
            this.t = t;
            this.next = next;
        }
        public Node(T t){
            this(t,null);
        }
    }

    //可以用头节点来存储元素的个数，这里的程序并没有用
    //不可以，因为泛型的数据类型不清楚，建议用单独的size来计数
    private Node head;    		//头结点
    private int size;			//链表元素个数
    //构造函数
    public LinkedList(){
        this.head = new Node(null);
        this.size = 0;
    }

    public int get_length(){
        return size;

    }

    public void show_all_element(){
        Node s = this.head;
        int len = 0;
        while(s.next!= null){
            s = s.next;
            len = len + 1;
            System.out.println(s.t);
        }
    }

    public void insert(int index, T value){
        if(index > this.get_length()){
            System.out.println("error!");
        }
        else{
            Node new_node = new Node(value);
            //从头节点开始往后推
            Node s = this.head;
            //定位到指定的node的位置
            for(int i=0;i<index;i++){
                s = s.next;
            }
            new_node.next = s.next;
            s.next = new_node;
            size = size + 1;
        }
    }

    public T delete(int index){
        if(index > this.get_length()){
            System.out.println("error");
            return null;
        }
        else{
            //定位到待删除的节点
            Node s = this.head;
            for(int i=0;i<index;i++){
                s = s.next;
            }
            Node p = s.next;
            s.next = p.next;
            size = size -1;
            return p.t;
        }
    }

    public T search_by_index(int index){
        if(index > this.get_length()){
            System.out.println("error");
            return null;
        }
        else{
            Node s = this.head;
            for(int i=0;i<index;i++){
                s = s.next;
            }
            return s.t;
        }
    }

    public int search_by_value(T value){
        Node s = this.head;
        int i = 0;
        while(s!=null && s.t!= value){
            s = s.next;
            i = i + 1;
        }
        if(s == null) {
            return -1;
        }
        else{
            return i;
        }

    }
    public static void main(String[] args) {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        for(int i=0; i<10;i++){
            list1.insert(0, i*2);
        }
        list1.show_all_element();
        list1.delete(0);
        list1.show_all_element();

        System.out.println(list1.search_by_index(3));
        System.out.println(list1.search_by_value(10));
        System.out.println(list1.get_length());
    }

}
