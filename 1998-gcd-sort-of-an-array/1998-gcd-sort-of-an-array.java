class Solution {
    class UnionFind {
        int[] parent;

        public UnionFind(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
            }
        }

        public int find(int i) {
            if (parent[i] == i) {
                return i;
            }
            return parent[i] = find(parent[i]);
        }

        public void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
            }
        }
    }

    public boolean gcdSort(int[] nums) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Sieve of Eratosthenes to find smallest prime factor for each number up to maxVal
        int[] spf = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            spf[i] = i;
        }
        for (int i = 2; i * i <= maxVal; i++) {
            if (spf[i] == i) {
                for (int j = i * i; j <= maxVal; j += i) {
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }

        UnionFind uf = new UnionFind(maxVal + 1);
        for (int num : nums) {
            int temp = num;
            while (temp > 1) {
                int prime = spf[temp];
                uf.union(num, prime);
                while (temp % prime == 0) {
                    temp /= prime;
                }
            }
        }

        int[] sortedNums = nums.clone();
        java.util.Arrays.sort(sortedNums);

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sortedNums[i]) {
                if (uf.find(nums[i]) != uf.find(sortedNums[i])) {
                    return false;
                }
            }
        }

        return true;
    }
}