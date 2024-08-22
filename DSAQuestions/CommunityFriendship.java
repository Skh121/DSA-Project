import java.util.Arrays;
 
public class CommunityFriendship {
 
    static class UnionFind {
        int[] parent;
        int[] rank;
 
        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(rank, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
 
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);  // Path compression
            }
            return parent[x];
        }
 
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
 
    public static String[] processFriendRequests(int n, int[][] restrictions, int[][] requests) {
        UnionFind uf = new UnionFind(n);
        String[] results = new String[requests.length];
 
        for (int i = 0; i < requests.length; i++) {
            int houseA = requests[i][0];
            int houseB = requests[i][1];
 
            int rootA = uf.find(houseA);
            int rootB = uf.find(houseB);
 
            boolean canBeFriends = true;
 
            // Check restrictions
            for (int[] restriction : restrictions) {
                int restrictedA = uf.find(restriction[0]);
                int restrictedB = uf.find(restriction[1]);
 
                // If trying to union (rootA, rootB) would connect restrictedA and restrictedB, deny the request
                if ((rootA == restrictedA && rootB == restrictedB) ||
                    (rootA == restrictedB && rootB == restrictedA)) {
                    canBeFriends = false;
                    break;
                }
            }
 
            if (canBeFriends) {
                uf.union(houseA, houseB);
                results[i] = "approved";
            } else {
                results[i] = "denied";
            }
        }
 
        return results;
    }
 
    public static void main(String[] args) {
        int n1 = 3;
        int[][] restrictions1 = {{0, 1}};
        int[][] requests1 = {{0, 2}, {2, 1}};
        System.out.println(Arrays.toString(processFriendRequests(n1, restrictions1, requests1))); // Output: [approved, denied]
 
        int n2 = 5;
        int[][] restrictions2 = {{0, 1}, {1, 2}, {2, 3}};
        int[][] requests2 = {{0, 4}, {1, 2}, {3, 1}, {3, 4}};
        System.out.println(Arrays.toString(processFriendRequests(n2, restrictions2, requests2))); // Output: [approved, denied, approved, denied]
    }
}
