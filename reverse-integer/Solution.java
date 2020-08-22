class Solution {
    public int reverse(int x) {
        int result = 0;
        
        while(x != 0) {
            int val = x % 10;
            
            if(result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && val >= 8))
                return 0;
            if(result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && val <= -9))
                return 0;
            
            result = result * 10 + val;
            
            x = x / 10;
        }
        
        return result;
    }
}
