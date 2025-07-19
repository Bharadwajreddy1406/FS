

Budget Padmanabham planned to visit the tourist places. There are N tourist 
places in the city. The tourist places are numbered from 1 to N.

You are given the routes[] to travel between the tourist places in the city.
where routes[i]=[place-1, place-2, price], A route is a bi-directional route.
You can travel from place-1 to place-2 or place-2 to place-1 with the given price.
 
Your task is to help Budget Padmanabham to find the cheapest route plan, to 
visit all the tourist places in the city. If you are not able to find such plan, 
print -1.
 
Input Format:
-------------
Line-1: Two space separated integers N and R,number of places and routes.
Next R lines: Three space separated integers, start, end and price.
  
Output Format:
--------------
Print an integer, minimum cost to visit all the tourist places.
 
 
Sample Input-1:
---------------
4 5
1 2 3
1 3 5
2 3 4
3 4 1
2 4 5
 
Sample Output-1:
----------------
8
 
Explanation:
------------
The cheapest route plan is as follows:
1-2, 2-3, 3-4 and cost is 3 + 4 + 1 = 8
 
 
Sample Input-2:
---------------
4 3
1 2 3
1 3 5
2 3 4
 
Sample Output-2:
----------------
-1


##  Solution 

import java.util.*;
public class Solution {
    
    static int [] parent, rank;
    static int find(int x){
        if(parent[x] == x){
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    static void union(int a, int b){
        
        int ra = find(a);
        int rb = find(b);
        
        if(ra == rb){
            return;
        }
        
        if(rank[ra] < rank[rb]){
            parent[ra] = rb;
        }else if (rank[ra] > rank[rb]){
            parent[rb] = ra;
        }else{
            parent[rb] = ra;
            rank[ra]++;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt()+1;

        int m = sc.nextInt();

        int[][] routes = new int[m][3];
        for (int i = 0; i < m; i++) {
            routes[i][0] = sc.nextInt();
            routes[i][1] = sc.nextInt();
            routes[i][2] = sc.nextInt();
        }

        Arrays.sort(routes,(a,b)-> a[2] - b[2]);
        
        parent = new int[n];
        rank = new int[n];
        
        for(int i=0;i<n;i++) parent[i] = i;
        for(int i=0;i<n;i++) rank[i] = 0;
        int c=0;
        int sum =0;
        
        for(int i=0;i<m;i++){
            int ra = find(routes[i][0]);
            int rb = find(routes[i][1]);
            
            
            if(ra !=rb){
                c++;
                sum+=routes[i][2];
                union(ra,rb);
            }
            if(c == n-1){ break;}
        }
        
        
        HashSet<Integer> s = new HashSet<>();
        for(int i=1;i<n;i++) s.add(parent[i]);
        System.out.println(s.size() == 1 ? sum:-1);
    }
}