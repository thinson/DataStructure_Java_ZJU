package table;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;

// 此文件只做最简单的BFS,DFS和最小生成树算法
public class Graph {
    public class AdjVNode{
        int index;
        int weight;
        AdjVNode next;

        public AdjVNode(int index, int weight){
            this.index = index;
            this.weight = weight;
            this.next = null;
        }
    }
    public class AdjList{
        AdjVNode first_edge;
        int size;

        public AdjList(){
            this.first_edge = null;
            this.size = 0;
        }
    }

    Queue<Integer> queue = new LinkedList<>();
    int v_num;
    int e_num;
    AdjList[] adjLists;
    int[] visited;

    // 建图的时候先根据顶点，建立，然后再逐步加边
    public Graph(int v_num){
        this.v_num = v_num;
        this.e_num = 0;
        this.adjLists = new AdjList[v_num];
        for(int i=0;i<v_num;i++){
            this.adjLists[i] = new AdjList();
        }
        visited = new int[v_num];
    }

    // 添加边到链表，相当于头插法
    public void add_edge(int v,int w,int weight){
        AdjVNode new_node = new AdjVNode(w,weight);
        new_node.next = this.adjLists[v].first_edge;
        this.adjLists[v].first_edge = new_node;
    }

    public void dfs(int v){
        this.visited[v] = 1;
        AdjVNode tmp = this.adjLists[v].first_edge;
        while(tmp != null){
            if(visited[tmp.index]==0){
                dfs(tmp.index);
            }
            tmp = tmp.next;
        }
    }

    public void bfs(int v){
        this.visited[v] = 1;
        //AdjVNode tmp = this.adjLists[v].first_edge;
        queue.add(v);
        while(!queue.isEmpty()){
            v = queue.remove();
            AdjVNode tmp = this.adjLists[v].first_edge;
            while(tmp != null){
                if(visited[tmp.index]==0){
                    visited[tmp.index]=1;
                    queue.add(tmp.index);
                }
                tmp = tmp.next;
            }
        }
    }

    //Kru算法实现mst
    // 思路是把逐步把权重最小，并且不构成回路，且没有访问过的边放到树中
    public void kru(int root){
        // 初始化 不需要dist变量，需要一个变量记录是否访问过某边



    }
    public static void main(String[] args){
        Graph graph = new Graph(5);
        graph.add_edge(0,1,1);
        graph.add_edge(0,2,5);
        graph.add_edge(1,2,3);
        graph.add_edge(1,3,2);
        graph.add_edge(2,3,4);
        graph.add_edge(3,4,2);
        System.out.println(Arrays.toString(graph.visited));
        graph.bfs(0);
        System.out.println(Arrays.toString(graph.visited));
        System.out.println("hello");

    }
}
