package matrix;
import java.util.LinkedList;
import java.util.Queue;

// 使用无向图
public class Graph {
    int[][] G;
    int vertex_num = 0;
    int edge_num = 0;
    boolean[] visited;

    //使用java自建容器
    Queue<Integer> queue = new LinkedList<>();

    public Graph(int v_num){
        this.G = new int[v_num][v_num];
        this.vertex_num = v_num;
        this.visited = new boolean[v_num];
    }
    public void add_edge(int[] v,int[] w){
        for(int i=0;i<v.length;i++){
            this.G[v[i]][w[i]] = 1;
            this.G[w[i]][v[i]] = 1;
        }
    }

    // 全连通的情况
    public void dfs(int v){
        visited[v] = true;
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
            for(int i=0; i<this.vertex_num;i++){
                if(this.G[v][i] == 1 && !visited[v]){
                    queue.add(i);
                }
            }
        }
    }
    //main省略

}
