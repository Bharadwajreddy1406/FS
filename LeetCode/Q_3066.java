import java.util.*;
public class  Q_3066 {
    public int minOperations(int[] nums, int k) {
        int n = nums.length;
        if(n<2) return 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        for(int i=0;i<n;i++) pq.add((long)(nums[i]));
        if(pq.peek() > k) return 0;
        int count =0;
        while(pq.peek()<k){
            if(pq.size()<2){
                break;
            }
            Long x = pq.poll();
            Long y = pq.poll();
            Long val = Math.min(x,y) * 2 + Math.max(x,y);
            pq.add(val);
            count++;
        }
        return count;
    }
}

// time complexity is O(nlogn) and space complexity is O(n).

// we need minimum 2 elements, and that too repeatedly until the sum of the elements is greater than k.
// we can use priority queue to get the minimum element in O(1) time.
// we can use the same priority queue to add the new element in O(logn) time.
// we can use the same priority queue to remove the minimum element in O(logn) time.
// we can use the same priority queue to remove the maximum element in O(logn) time.
