class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        
        int[] nums3 = new int[size];
        
        for(int i = 0, j = 0, k = 0; i < size; i++) {
            
            if(j == nums1.length) {
                nums3[i] = nums2[k++];
                continue;
            }
            
            if(k == nums2.length) {
                nums3[i] = nums1[j++];
                continue;
            }
            
            if(nums1[j] < nums2[k]) {
                nums3[i] = nums1[j++];
            } else {
                nums3[i] = nums2[k++];
            }
        }
        
        if(size%2 == 1) {
            // return size/2.0;
            return (double)(nums3[size/2]);
        } else {
            return (nums3[(size/2)-1] + nums3[size/2]) / 2.0;
        }
    }
}
