package seq.queue;


// 抽象是环形的，在内存中还是以数组的形式保存的
// 先入先出，插入新的值要在rear，删除值在front处删除
// front指向有值的前一项，rear指向末尾节点
// front和rear的相对位置如何判断空还是满
// 空 front == rear
// 满 front = rear + 1
public class SeqQueue{
    private int front;
    private int rear;
    private int[] data;
    public int Maxsize;

    public SeqQueue(int maxsize){
        this.Maxsize = maxsize;
        this.data = new int[maxsize];
        this.front = 0;
        this.rear = 0;
    }

    public int get_len(){
        //!!!为了避免front的值比rear引起的计算错误
        return (rear - front + Maxsize)%Maxsize;
    }

    public boolean isEmpty(){
        return front==rear;
    }

    public boolean isFull(){
        return front == (rear + 1)%Maxsize;
    }

    public void add(int value){
        if(isFull()) System.out.println("isFull!");
        else{
            this.rear = (this.rear + 1)%Maxsize;
            this.data[rear] = value;
        }
    }

    public int delete(){
        if(isEmpty()) return -1;
        else{
            this.front = (this.front+1)%Maxsize;
            return this.data[this.front];
        }
    }

    public void show(){
        System.out.println("\nfront:" +  this.front +"\nrear:"+  this.rear);
        System.out.println("Value:");
        for(int i=0; i<get_len();i++){
            System.out.println(data[(this.front+1+i)%Maxsize]);
        }
    }

    public static void main(String[] args){
        SeqQueue queue = new SeqQueue(10);
        for(int i=0;i<12;i++){
            queue.add(i*2);
        }
        queue.show();
        System.out.println("len:" + queue.get_len());
        queue.delete();
        queue.delete();
        queue.add(666);
        queue.show();
        System.out.println("len:" + queue.get_len());
    }
}
