package Seq;

// 使用数组来保存完全二叉树
// val[0] 放置哨兵，大于数组中任意一个元素
// 因为使用线性表，需要提前分配空间 maxsize
// 再设计一个变量size来保存当前使用了几个结点

public class MaxHeap {
    // 保存完全二叉树
    int[] value;
    // 最大多少个值
    int maxsize;
    // 存放当前的占用情况
    int size;
    //哨兵
    int MAX = 10000;

    public MaxHeap(int maxsize){
        this.maxsize = maxsize;
        this.value = new int[maxsize];
        this.value[0] = MAX;
        this.size = 0;
    }

    // 是否空
    public boolean is_empty(){
        return this.size == 0;
    }

    //是否满
    public boolean is_full(){
        return this.size + 1 == this.maxsize;
    }

    // 插入新的结点：
    // 放置到完全二叉树的末尾
    // 循环比较其与其父亲结点的值大小，比父亲大就把父亲结点拉下来，小就直接放置
    // 时间复杂度 O(logN) ,走了一个树的高度
    public void add(int val){
        if(is_full()) return;
        this.value[this.size + 1] = val;
        this.size++;
        for(int i=size; i!=0; i=i/2){
            if(this.value[i] <= this.value[i/2]) break;
            else{
                int tmp = this.value[i];
                this.value[i] = this.value[i/2];
                this.value[i/2] = tmp;
            }
        }
    }

    // 删除最大的结点：
    // 拿一个变量存放树根，因为最后要返回
    // 把最后一个值移动到树根，原树根清空（直接size--）
    // 左右子树树根比较，较大的再和树根比较，若树根小于子树树根，子树较大值和树根换位置，i*2，依次往下走，循环
    // 若树根值最大，跳出循环
    // 返回最大值

    public int delete(){
        if(is_empty()) return 0;
        else{
            // 取出最大值
            int tmp = this.value[1];
            // 树尾巴的值放到根
            this.value[1] = this.value[this.size];
            this.size--;
            // 调整树
            for(int i=1;i<this.size;){
                // 找出两个子结点的最大值
                int max_index;
                if(2*i+1>size) max_index = 2*i;
                else max_index = this.value[i*2]>=this.value[i*2+1]?(2*i):(2*i+1);
                //最大值和根做比较
                if(this.value[i]>=this.value[max_index]) break;
                else{
                    // 互换
                    int tmp2 = this.value[max_index];
                    this.value[max_index] = this.value[i];
                    this.value[i] = tmp2;
                    i = max_index;
                }
            }
            return tmp;
        }
    }

    // 创建树 错误的做法
    // 传入的参数是 一个数组
    // 依次赋值
    // 从最后一个结点开始依次往前:
    //      比较和父节点的大小，如果比父节点大，调换位置,i--
    // 结束条件 i==0
    // 最后得到的就是符合要求的最大堆，时间复杂度O(n)
    /*
    public MaxHeap(int maxsize, int[] val){
        this.maxsize = maxsize;
        this.value = new int[maxsize];
        this.value[0] = MAX;
        this.size = 0;

        // 赋值
        int val_len = val.length;
        for(int i=0; i<val_len; i++){
            size = size + 1;
            this.value[size] = val[i];
        }

        // 比较
        for(int j = val_len; j!=1; j--){
            if(this.value[j]<=this.value[j/2]) continue;
            else{
                int tmp = this.value[j];
                this.value[j] = this.value[j/2];
                this.value[j/2] = tmp;
            }
        }
    }
    */
    // 先赋值
    // 从最后一个结点的父节点开始(size/2)，类似删除的思路，把子树调整为堆,i--
    // 结束条件 i==1
    public MaxHeap(int maxsize, int[] val){
        this.maxsize = maxsize;
        this.value = new int[maxsize];
        this.value[0] = MAX;
        this.size = 0;

        // 赋值
        int val_len = val.length;
        for(int i=0; i<val_len; i++){
            size = size + 1;
            this.value[size] = val[i];
        }

        // 比较, 和2*i与2*i+1的最大值进行比较
        for(int i = val_len/2; i!=0; i--){
            for(int j=i;j<this.size;) {
                // 找出两个子结点的最大值
                int max_index;
                if (j * 2 + 1 > size) max_index = 2 * j;
                else max_index = this.value[j * 2] >= this.value[j * 2 + 1] ? (2 * j) : (2 * j + 1);
                //最大值和根做比较
                if (this.value[j] >= this.value[max_index]) break;
                else {
                    // 互换
                    int tmp2 = this.value[max_index];
                    this.value[max_index] = this.value[j];
                    this.value[j] = tmp2;
                    j = max_index;
                }
            }
        }
    }

    public void show(){
        System.out.println("size:" + this.size);
        for(int i=1;i<=size;i++){
            System.out.print(this.value[i] + " ");
        }
    }

    public static void main(String[] args){
        int[] val = {0,1,2,3,5,6,100};
        MaxHeap mymaxheap = new MaxHeap(100, val);
        mymaxheap.show();
        mymaxheap.add(4);
        mymaxheap.show();
        System.out.println("\n" + mymaxheap.delete());
        mymaxheap.show();
    }


}
