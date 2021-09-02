package matrix;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
import java.util.Stack;


//所有的代码都没有考虑图不联通的情况


// 使用无向图
public class Graph {
    int[][] G;

    //for Floyd
    int[][] D;
    int vertex_num = 0;
    int edge_num = 0;
    boolean[] visited;

    int[] dist;
    int[] path;

    // 存放mst
    int[] MST;
    //使用java自建容器
    Queue<Integer> queue = new LinkedList<>();

    public Graph(int v_num){
        this.G = new int[v_num][v_num];
        this.vertex_num = v_num;
        this.visited = new boolean[v_num];
        this.dist = new int[v_num];
        this.path = new int[v_num];
        for(int i=0; i<v_num; i++){
            this.path[i] = -1;
            this.dist[i] = -1;
        }
        this.D = new int[v_num][v_num];
        //用来存放最小生成树
        this.MST = new int[v_num];
    }

    public void add_edge(int[] v,int[] w){
        for(int i=0;i<v.length;i++){
            this.G[v[i]][w[i]] = 1;
            //this.G[w[i]][v[i]] = 1;
        }
    }

    public void add_edge2(int[] v,int[] w, int[] weighted){
        // 赋初值2000
        for(int i=0;i<this.vertex_num;i++){
            for(int j=0;j<this.vertex_num;j++){
                this.G[i][j] = 2000;
            }
        }
        for(int i=0;i<v.length;i++){
            this.G[v[i]][w[i]] = weighted[i];
            //this.G[w[i]][v[i]] = 1;
        }

    }

    // 全连通的情况
    public void dfs(int v){
        visited[v] = true;
        System.out.println(v);
        for(int i=0;i<this.vertex_num;i++){
            if(!visited[i] && this.G[v][i]==1){
                dfs(i);
            }
        }
    }

    public void bfs(int v){
        visited[v] = true;
        queue.add(v);
        while(!queue.isEmpty()){
            v = queue.remove();
            System.out.println(v);
            for(int i=0; i<this.vertex_num;i++){
                if(this.G[v][i] == 1 && !visited[i]){
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    // 无权图最短路径
    // 类似bfs，结合队列，实现更新
    // dist[i] 存放距离信息，初始化为一个不可能的数字-1
    // path[i] 存放上一节点的信息，默认初始化为-1
    public void shortest_unweighted(int s){
        queue.add(s);
        this.dist[s] = 0;
        while(!queue.isEmpty()){
            int v = queue.remove();
            for(int i=0;i<this.vertex_num;i++){
                if(this.G[v][i]==1 && this.dist[i]==-1){
                    queue.add(i);
                    this.dist[i] = this.dist[v] + 1;
                    this.path[i] = v;
                }
            }
        }
    }

    // 有权图最短路径
    // 贪心算法: 每次往集合里收集合外距离最近的，只使用dist[]，赋很大的初值
    // 往里收的过程中, 如果邻接点到源点的距离走这个点更近的话 更新一下距离，否则不用管
    public void shortest_weighted(int s){
        // 初始化
        int[] add = new int[this.vertex_num];
        for(int i=0;i<this.vertex_num;i++){
            if(this.G[s][i]!=2000){
                this.dist[i] = this.G[s][i];
                this.path[i] = 0;
            }
            else{
                this.dist[i] = 1000;
                this.path[i] = -1;
            }
            add[i] = 0;
        }
        // 赋初值
        this.dist[s] = 0;
        add[0] = 1;

        //循环往集合中加值
        while(true){
            // 结束循环的条件 TODO

            // 往里收顶点，找最小的问题
            int min_val = 1000;
            int min_index = 1000;
            for(int i=0;i<this.vertex_num;i++){
                if(dist[i]<min_val && add[i]==0){
                    min_val = dist[i];
                    min_index = i;
                }
            }
            if(min_index == 1000) break;

            add[min_index] = 1;

            // 对于最小顶点，找到其邻接点，比较距离
            for(int i=0;i<this.vertex_num;i++){
                if(dist[i] > this.G[min_index][i] + dist[min_index]){
                    dist[i] =  this.G[min_index][i] + dist[min_index];
                    path[i] = min_index;
                }
            }

        }
    }

    public void print_shortest_path(int v){
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(v);
        while(this.path[v]!=-1){
            v = this.path[v];
            stack1.push(v);
        }

        while(!stack1.isEmpty()){
            System.out.println(stack1.pop());
        }
    }

    public void floyd(){
        //用什么存储 this.D
        this.D = this.G;
        for(int k=0;k<this.vertex_num;k++){
            for(int i=0;i<this.vertex_num;i++){
                for(int j=0;j<this.vertex_num;j++){
                    if(this.D[i][k] + this.D[k][j] < this.D[i][j]){
                        this.D[i][j] = this.D[i][k] + this.D[k][j];
                    }
                }
            }
        }
    }

    public void prim(int s){
        // 存储样式 使用s[parent]的形式存储mst，默认的mst都是-1，看成是不同的集合
        // 使用dist[i] 来表示顶点i到树的距离，在树内就变成0
        // 更新方式，不断把不在树中且距离树最近的顶点放到树中，同时更新其他树外顶点到树的最小距离
        // prim不需要考虑回环问题
        // 赋初值
        for(int i=0;i< this.vertex_num;i++){
            if(this.G[s][i]!=2000){
                this.dist[i] = this.G[s][i];
                this.path[i] = 0;
            }
            else{
                this.dist[i] = 2000;
                this.path[i] = -1;
            }
        }
        this.dist[s] = 0;
        int[] add = new int[this.vertex_num];
        add[0] = 1;
        while(true) {
            // 找到距离树最近的顶点，需要满足该顶点不在树里面
            int min_val = 2000;
            int min_index = 2000;
            for (int i = 0; i < this.vertex_num; i++) {
                // 没有添加过的顶点
                if (add[i]==0) {
                    if (this.dist[i] < min_val) {
                        min_val = this.dist[i];
                        min_index = i;
                    }
                }
            }
            if(min_index==2000) break;
            add[min_index] = 1;
            // 遍历邻接点，如果边的值小于存放的距离值，更新。从树到这个点的最近方式。
            for (int i = 0; i < this.vertex_num; i++) {
                // 找到min_index的邻接点
                if (this.G[min_index][i] != 2000 && add[i]==0) {
                    if (G[min_index][i] < this.dist[i]) {
                        this.dist[i] = G[min_index][i];
                        this.path[i] = min_index;
                    }
                }
            }
        }


    }
    // TODO
    // - main函数创建一个简单的图用于验证示例 dfs和bfs的可靠性。
    // - 邻接矩阵表示的有权图最短路径问题，使用Dij算法求解（无权用邻接表的形式实现），要求返回最短路径，并且能打印出最短路径是什么。
    // - 邻接矩阵的最小生成树问题 Prim算法，使用类似Dij的思路来做。
    //main省略

    public static void main(String[] args){
        Graph graph = new Graph(5);
        int[] v = {0,0,1,2,1,3};
        int[] w = {1,2,3,3,2,4};
        int[] weight = {1,5,2,4,3,2};
        graph.add_edge2(v,w,weight);
        System.out.println((Arrays.deepToString(graph.G)));
//        System.out.println((Arrays.toString(graph.visited)));
//        graph.bfs(0);
//        System.out.println((Arrays.toString(graph.visited)));

        // 验证无权最短路径算法
        /*
        graph.shortest_unweighted(0);
        System.out.println(Arrays.toString(graph.dist));
        System.out.println(Arrays.toString(graph.path));
        graph.print_shortest_path(2);

         */

        // 验证有权图的最短路径算法
//        graph.shortest_weighted(0);
//        System.out.println(Arrays.toString(graph.dist));
//        System.out.println(Arrays.toString(graph.path));
//        graph.print_shortest_path(2);

        //floyd算法
//        graph.floyd();
//        System.out.println(Arrays.deepToString(graph.D));

        //prim
        graph.prim(0);
        System.out.println(Arrays.toString(graph.dist));
        System.out.println(Arrays.toString(graph.path));


    }

}
