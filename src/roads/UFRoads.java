/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package roads;
/**
 *
 * @author TofixXx
 */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class UFRoads {
    int N;
    int AttSet[];
    Set <Integer> adjList[];
    int SetsNum;
    UFRoads(int NumV)
    {
        N = NumV+1;
        SetsNum = NumV;
        AttSet = new int[N];
        for(int i = 0; i<N; i++)
        {
            AttSet[i] = i;
        }
        adjList = new Set[N];
        for(int i = 0; i<N; i++)
        {
            adjList[i] = new HashSet<Integer>();
        }
    }
    void Union(int a, int b)
    {
        adjList[a].add(b);
        adjList[b].add(a);
        if(AttSet[a] != AttSet[b])
        {
            int bres = AttSet[b];
            for(int i = 1; i < N; i++)
            {
                if(AttSet[i] == bres)
                {
                    AttSet[i] = AttSet[a];
                }
            }
            SetsNum --;
        }
    }
    void delete(int a, int b)
    {
        class DFSConnected
        {
            boolean Checked[] = new boolean[N];
            boolean VSConn;
            DFSConnected(int S, int V)
            {
                Checked[V] = true;
                Iterator<Integer> it = adjList[V].iterator();
                while(it.hasNext())
                {
                    int T = it.next();
                    if(T != S)
                    {
                        search(S, T);
                    }
                    if(VSConn)
                    {
                        break;
                    }
                     
                }
            }
            private void search(int S, int L)
            {
                Iterator<Integer> it = adjList[L].iterator();
                while(it.hasNext())
                {
                    int T = it.next();
                    if(T == S)
                    {
                        if(!VSConn)
                        {
                            VSConn = true;
                        }
                        return;
                    }
                    if(!Checked[T])
                    {
                        Checked[T] = true;
                        search(S, T);
                    }
                }
            }
            public Queue<Integer> makeVAdjList()
            {
                if(VSConn)
                {
                    return null;
                }
                Queue<Integer> adjList = new LinkedList();
                for(int i = 0; i<N; i++)
                {
                    if(Checked[i])
                    {
                        adjList.add(i);
                    }
                }
                return adjList;
            }
        }
        DFSConnected searcher = new DFSConnected(a, b);
        Queue<Integer> vAdjList = searcher.makeVAdjList();
        if(vAdjList != null)
        {
            SeparateSet(vAdjList, a, b);
        }
        adjList[a].remove(b);
        adjList[b].remove(a);
    }
    void SeparateSet(Queue<Integer> adj, int a, int  b)
    {
        while(!adj.isEmpty())
        {
            AttSet[adj.poll()] = a;
        }
        SetsNum++;
    }
}
