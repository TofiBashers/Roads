
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

/** This class is implement of Disjoint-Set Union data structure.
 Supports union and delete single elements, that belongs to single/multiple
 sets in structure.*/
public class DisjointSetUnion {
    private final int N;
    private int[] attSet;
    private Set <Integer> adjList[];
    private int setsNum;
    
    /** 
     * @param numV summary count of elements
     */
    DisjointSetUnion(int numV)
    {
        N = numV+1;
        setsNum = numV;
        attSet = new int[N];
        for(int i = 0; i<N; i++)
        {
            attSet[i] = i;
        }
        adjList = new Set[N];
        for(int i = 0; i<N; i++)
        {
            adjList[i] = new HashSet<Integer>();
        }
    }
    
    /** Union two elements. If they are located in different sets,
     * this method combines two sets.
     */
    public void union(int a, int b)
    {
        adjList[a].add(b);
        adjList[b].add(a);
        if(attSet[a] != attSet[b])
        {
            for(int i = 1; i < N; i++)
            {
                if(attSet[i] == attSet[b])
                {
                    attSet[i] = attSet[a];
                }
            }
            setsNum --;
        }
    }
    
    /** Deletes relation with elements. If they are located in single set,
     * this method divides the set into two sets.
     */
    public void delete(int a, int b)
    {
        /** This class used deep first search algorithm
         * for checking, whether two connected elements have 
         * a workaround to each other.
         * (i.e. answer the question "If the direct path between 
         * element will be reoved, it separates the set?)
         * If workaround is found, search should be stopped,
         * and method getAdjQueue() returns null.
         * Otherwise, the method returns all elements, adjoint 
         * with source.
        */
        class DFSConnected
        {
            boolean[] checked = new boolean[N];
            boolean VSConn;
            
            DFSConnected(int S, int V)
            {
                checked[V] = true;
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
                        VSConn = true;
                        return;
                    }
                    if(!checked[T])
                    {
                        checked[T] = true;
                        search(S, T);
                    }
                }
            }
            
            public Queue<Integer> getAdjQueue()
            {
                if(VSConn)
                {
                    return null;
                }
                Queue<Integer> adjList = new LinkedList();
                for(int i = 0; i<N; i++)
                {
                    if(checked[i])
                    {
                        adjList.add(i);
                    }
                }
                return adjList;
            }
        }
        
        DFSConnected searcher = new DFSConnected(a, b);
        Queue<Integer> vAdjQueue = searcher.getAdjQueue();
        if(vAdjQueue != null)
        {
            separateSet(vAdjQueue, a);
        }
        adjList[a].remove(b);
        adjList[b].remove(a);
    }
    
    /** Allocates a separate set.
     * @param a source element number
     * @param adj all elements, that includes to new set. This vertecies
     * are adjoint with source element
     */
    private void separateSet(Queue<Integer> adj, int a)
    {
        while(!adj.isEmpty())
        {
            attSet[adj.poll()] = a;
        }
        setsNum++;
    }
    
    public int getSetsNum(){
        return setsNum;
    }
}
