package table;

import java.util.LinkedList;
import java.util.Queue;

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
        visited = new int[v_num];
    }

    // 添加边到链表，相当于头插法
    public void add_edge(int v,int w){
        AdjVNode new_node = new AdjVNode(w,1);
        new_node.next = this.adjLists[v].first_edge;
        this.adjLists[v].first_edge = new_node;
    }

    public void dfs(int v){
        this.visited[v] = 1;
        AdjVNode tmp = this.adjLists[v].first_edge;
        while(tmp != null){
            if(visited[v]==0){
                dfs(tmp.index);
            }
            tmp = tmp.next;
        }
    }

    public void bfs(int v){
        this.visited[v] = 1;
        AdjVNode tmp = this.adjLists[v].first_edge;
        queue.add(tmp.index);
        while(!queue.isEmpty()){
            v = queue.remove();
            tmp = this.adjLists[v].first_edge;
            while(tmp != null){
                if(visited[tmp.index]==0){
                    visited[tmp.index]=1;
                    queue.add(tmp.index);
                }
                tmp = tmp.next;
            }
        }
    }
}
