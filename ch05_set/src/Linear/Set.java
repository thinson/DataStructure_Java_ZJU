package Linear;

public class Set {

    // 对于集合中的每一个结点，都包含一个value和一个指向父亲的parent（使用索引）
    public static class SetNode{
        public int value;
        public int parent;
        public SetNode( int value, int parent){
            this.value = value;
            this.parent = parent;
        }
    }
    int maxsize;
    SetNode[] setNodes;


    public Set(int[] val, int[] parent){
        this.maxsize = 100;
        this.setNodes = new SetNode[this.maxsize];
        for(int i=0;i< val.length;i++){
            this.setNodes[i] = new SetNode(val[i], parent[i]);
//            this.setNodes[i].value = val[i];
//            this.setNodes[i].parent = parent[i];
        }
    }

    //返回根节点的索引
    public int Find(int node1){
        while(this.setNodes[node1].parent>=0){
            //保存父亲的索引
            node1 = this.setNodes[node1].parent;
        }
        return node1;
    }

    //判断两个节点是否在一个集合里面
    public boolean is_in_one_set(int node1, int node2){
        node1 = Find(node1);
        node2 = Find(node2);
        return node1 == node2;
    }

    //把两个集合合并
    public void union(int root1, int root2){
        if(this.setNodes[root1].parent>this.setNodes[root2].parent){
            this.setNodes[root2].parent += this.setNodes[root1].parent;
            this.setNodes[root1].parent = root2;
        }
        else{
            this.setNodes[root1].parent += this.setNodes[root2].parent;
            this.setNodes[root2].parent = root1;
        }
    }

    // 查找的路径压缩写法
    public int Find2(int node1){
        if(this.setNodes[node1].parent<0) return node1;
        else{
            return this.setNodes[node1].parent = Find2(this.setNodes[node1].parent);
        }
    }

    public static void main(String[] args){
        int[] val = {0,1,2,3,4,5};
        int[] parent = {-5,0,1,2,2,4};
        Set myset = new Set(val, parent);
        System.out.println(myset.Find(4));
        System.out.println(myset.is_in_one_set(3,4));
//        myset.union(0,4);
        int root1 = myset.Find2(5);
    }






}
