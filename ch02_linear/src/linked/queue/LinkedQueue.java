package linked.queue;

import linked.list.LinkedList;

public class LinkedQueue<T> {
    private class Node{
        private T t;
        private Node next;

        public Node(T t){
            this.t = t;
            this.next = null;
        }

        public Node(){
            this.next = null;
        }
    }

    //front是头节点，不保存数值，指向第一个有值的节点, delete
    private Node front;
    //rear是为尾节点，指向链表的最后一个有值的节点, add
    private Node rear;
    //不包含size

    public LinkedQueue(){
        this.front = new Node();
        this.rear = new Node();

        //this.front.next = null;
        //this.rear.next = null;
    }

    public int get_len(){
        int i=0;
        Node s = this.front;
        while(s.next != null){
            s = s.next;
            i++;
        }
        return i;
    }

    public boolean isEmpty(){
        return this.front.next==null;
    }

    public void add(T t){
        Node s = new Node(t);
        if(rear.next == null){
            this.front.next = s;
            this.rear.next = s;
        }
        else {
            this.rear.next.next = s;
            this.rear.next = s;
        }
    }

    public T delete(){
        if(isEmpty()) return null;
        else{
            Node s = this.front.next;

            //如果网络只有一个节点的时候
            if(this.front.next == this.rear.next){
                this.front.next = null;
                this.rear.next = null;
            }
            else {
                this.front.next = this.front.next.next;
            }
            return s.t;
        }
    }

    public void show(){
        Node s = this.front.next;
        System.out.println("Value");
        while(s!=null){
            System.out.println(s.t);
            s = s.next;
        }
    }

    public static void main(String[] args){
        LinkedQueue<Integer> queue = new LinkedQueue<Integer>();
        for(int i=0;i<10;i++){
            queue.add(i*2);
        }
        queue.show();
        System.out.println("len: " + queue.get_len());
        for(int i=0;i<10;i++){
            queue.delete();
        }
        queue.delete();
        queue.delete();
        queue.add(666);
        queue.show();
        System.out.println("len: " + queue.get_len());
    }


}
