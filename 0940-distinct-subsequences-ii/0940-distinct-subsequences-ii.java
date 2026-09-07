class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        long total = 0;
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            long newSubseqs = (total + 1) % MOD;
            
            total = (total + newSubseqs - last[idx] + MOD) % MOD;
            
            last[idx] = newSubseqs;
        }

        return (int) total;
    }
}