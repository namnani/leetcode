class Solution {
        /*
        1. Finding a pivot point where all elements to the left are smaller
           and all elements to the right are greater, you can find the median
        
            x x [x]|[x] x x   lLong, rLong
              y [y]|[y] y    lShort, rShort
        
            x x y [y] [x] | [y] y [x] x x 
            
        2. Any pivot point in the smaller array has a corresponding point on the 
           large array, that divides the total # of elements in two
            x x x x|x x
              y|y y y
              
        3. After picking a pivot point, it's possible to determine whether we need to
           go left or right
            1 2 3|4 5 6
              2 3|4 5
              
        4. Code outline:
            1. Binary search on small array
            2. getIndices: m -> lLong, rLong, etc.
            3. getDirection: lLong, rLong, etc. -> get direction (left or right)
            4. getResult: rLong, lLong, etc. -> calculate the median
            
        ## odd case
            y y [y]|[y] y
                [x]|[x] x x
                
            y [x] y [y] | [x] [y] y x x 
        Time O(log(n))
        Space O(1)
        where n is the number of elements in the smaller array  
        */
    
    // return int[] {lShort, rShort, lLong, rLong}
    private int[] getIndices(int rShort, int[] aShort, int[] aLong) {
        int midIndex = (aShort.length + aLong.length) / 2;
        int rLong = midIndex - rShort;
        return new int[]{rShort - 1, rShort, rLong - 1, rLong};
    }
    
    // determine wheter to go left or right
    private int getDirection(int[] indices, int[] aShort, int[] aLong) {
        if (getVal(aShort, indices[0]) > getVal(aLong, indices[3])) {
            return -1;
        } else if (getVal(aLong, indices[2]) > getVal(aShort, indices[1])) {
            return 1;
        }
        else {
            return 0;
        }
    }
    
    // get value at index i of array arr
    private int getVal(int[] arr, int i) {
        if (i == -1) {
            return Integer.MIN_VALUE;
        }
        if (i == arr.length) {
            return Integer.MAX_VALUE;
        }
        return arr[i];
    }
    
    private double getResult(int[] indices, int[] aShort, int[] aLong) {
        int odd = (aShort.length + aLong.length) % 2;
        if (odd == 1) {
            return Math.min(getVal(aLong, indices[3]), getVal(aShort, indices[1]));
        } else {
            return (Math.max(getVal(aShort, indices[0]), getVal(aLong, indices[2]))
                    + Math.min(getVal(aShort, indices[1]), getVal(aLong, indices[3]))) / 2.0;
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // temporary variables.
        int[] indices = null;
        int d = 1;
        int[] aShort;
        int[] aLong;
        
        // determine long and short arrays.
        if (nums1.length < nums2.length) {
            aShort = nums1;
            aLong = nums2;
        } else {
            aShort = nums2;
            aLong = nums1;
        }
        
        // binary search.
        int l = 0;
        int r = aShort.length;
        while (d != 0) {
            int m = (l + r) / 2;
            // getting indices from our m
            indices = getIndices(m, aShort, aLong);
            // get out d (direction)
            d = getDirection(indices, aShort, aLong);
            if (d < 0) {
                r = m -1;
            } else if (d > 0) {
                l = m + 1;
            }
        }
        
        // calculate median.
        return getResult(indices, aShort, aLong);
    }
}
