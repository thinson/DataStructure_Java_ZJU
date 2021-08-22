package Linked;

//这个树指的是二叉搜索树
//Date： 2021年8月22日
// 实现二叉搜索树的遍历，求树高，插入值，删除值，查找值
public class Tree {
    //首先设置一个含有左右指针的节点
    private class Node{
        private int t;
        private Node left;
        private Node right;
        public Node(int t) {
            this.t = t;
            this.left = null;
            this.right = null;
        }
    }

    //树的属性有树的根节点，树的节点数量
    private Node root;
    private int num;

    public Tree(int t){
        this.root = new Node(t);
    }
    //树的方法有：
    //获取：获取根节点的值, 获取树的高度，获取树的节点数，返回最大值，返回最小值
    //方法：查找节点，增加节点，删除节点

    public int get_root_value(){
        return this.root.t;
    }

    public int get_tree_height(Node root){
        //分别遍历左右子树，返回最大值，使用递归的算法来做
        int l_height, r_height;
        if(root != null) {
            l_height = get_tree_height(root.left);
            r_height = get_tree_height(root.right);
            //还有一个根节点需要计算，故+1
            return Math.max(l_height, r_height) + 1;
        }
        else return 0;
    }

    public int get_node_num(Node root){
        // 利用递归的算法，把左右子树看成主体，分别计算左右子树节点的数量最后再相加即可。
        int l_num, r_num;
        if(root != null){
            l_num = get_node_num(root.left);
            r_num = get_node_num(root.right);
            return l_num + r_num + 1;
        }
        else return 0;
    }

    public int get_max(){
        // 一直往右遍历即可，直到找到右节点为null的节点
        Node tmp = this.root;
        while(tmp.right != null){
            tmp = tmp.right;
        }
        return tmp.t;
    }

    public int get_min(Node tmp){
        //一直往左遍历，直到找到左节点为null的节点
        // Node tmp = this.root;
        while(tmp.left != null){
            tmp = tmp.left;
        }
        return tmp.t;
    }

    //查找节点，非递归写法，不用递归整个树并且没有回溯过程就能改成非递归算法
    public boolean find(int value){
        //首先判断和根节点之间的关系，如果值大于根节点就往右，小于就往左，利用一个循环的赋值，直到
        // 遍历到当前节点为空节点为止。
        Node tmp = this.root;
        while(tmp != null){
            if(tmp.t == value) return true;
            else if(tmp.t < value){
                tmp = tmp.right;
            }
            else{
                tmp = tmp.left;
            }
        }
        return false;
    }

    //查找的递归写法
    //对于任意节点，查找的过程就是，比较根节点的大小
    //根据大小跳转到不同的子树中再寻找
    //回溯的条件： 这里不需要回溯，找到就return，找不到就false；
    //所以在递归之前都是return 没有值的传递。
    public boolean find2(int value, Node root){
        if(root != null) {
            if (value == root.t) return true;
            if (value > root.t) return find2(value, root.right);
            else return find2(value, root.left);
        }
        else return false;
    }

    // 采用递归的写法：
    // 对于每一个树，
    // 如果该树为空，新建一个节点返回，（返回的是节点，需要之前重新用指针建立联系）
    // 如果该树不为空，比较值的大小
    // 待插入节点的值大的话，就把右树作为新子树，继续进行 指针联系，不是return
    // 如果小的话到左子树
    public Node add_node(int value, Node root_node){
        if (root_node == null){
            return new Node(value);
        }
        else{
            if(root_node.t > value) root_node.left = add_node(value, root_node.left);
            else if(root_node.t < value) root_node.right = add_node(value, root_node.right);
            else{
                return null;
            }
            return root_node;
        }
    }

    //采用递归的写法
    //左 根 右
    //对于每个不为null的节点，遍历左子树，遍历根（输出根节点的值），遍历右子树，
    public void retrival(Node root_node){
        if(root_node != null){
            retrival(root_node.left);
            System.out.println(root_node.t);
            retrival(root_node.right);
        }
    }

    //删除某个节点，需要用到递归
    //首先是比较大小，不一样返回到不同的子树
    //找到了分为四种情况
    //情况1： 该节点无子树，直接删除这个节点就行了
    //情况2： 该节点右子树为空，把左子树的根节点提上来即可
    //情况3： 该节点左子树为空，把右节点的根节点提上来即可
    //情况4： 该节点左右子树均非空，可以把左子树最大值或者右子树最小值提上来，再删除对应的节点（递归调用，根节点不一样）
    //回溯条件： 找到了这个节点，如果一直没有找到，直接返回null，对应的是空树，即树的结构并不发生变化。
    public Node delete_node(int value, Node root_node){
        if(root_node == null) return null;
        else{
            if(root_node.t == value){
                if(root_node.left == null && root_node.right == null){
                    root_node =  null;
                }
                else if(root_node.left != null && root_node.right == null){
                    root_node =  root_node.left;
                }
                else if(root_node.left == null && root_node.right != null){
                    root_node =  root_node.right;
                }

                else{
                    //获取到右子树的最小值，放到右子树的根节点
                    Node new_node = new Node(get_min(root_node.right));
                    new_node.left = root_node.left;

                    new_node.right = delete_node(new_node.t, root_node.right);

                    root_node = new_node;
                }

            }
            else if (root_node.t > value){
                root_node.left = delete_node(value, root_node.left);
            }
            else{
                root_node.right = delete_node(value, root_node.right);
            }
            return root_node;
        }
    }


    public static void  main(String[] args){
        Tree tree = new Tree(20);
        System.out.println(tree.get_root_value());
        System.out.println(tree.get_tree_height(tree.root));
        System.out.println(tree.get_node_num(tree.root));
        System.out.println("min:" + tree.get_min(tree.root));
        System.out.println("max:" + tree.get_max());
        System.out.println(tree.find(20));
        System.out.println(tree.find2(15, tree.root));
        for(int i=0; i<10;i++){
            tree.add_node(i, tree.root);
        }
        tree.retrival(tree.root);
        tree.delete_node(7, tree.root);
        tree.retrival(tree.root);
    }
}
