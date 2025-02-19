import java.util.*;
import java.math.*;

public class SubnetCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String baseIP = sc.nextLine().trim();
        int cidr = sc.nextInt();
        int numSubnets = sc.nextInt();
        
        // Determine extra bits required: ceil(log2(numSubnets))
        int extraBits = 0;
        while((1 << extraBits) < numSubnets) extraBits++;
        int newPrefix = cidr + extraBits;
        int blockSize = 1 << (32 - newPrefix);
        
        // Convert baseIP to integer
        long ipVal = ipToLong(baseIP);
        List<String> subnets = new ArrayList<>();
        for(int i = 0; i < numSubnets; i++){
            long subnetIP = ipVal + (i * blockSize);
            subnets.add(longToIP(subnetIP) + "/" + newPrefix);
        }
        System.out.println(subnets);
    }
    
    static long ipToLong(String ip) {
        String[] parts = ip.split("\\.");
        long result = 0;
        for(String part : parts) {
            result = (result << 8) + Integer.parseInt(part);
        }
        return result;
    }
    
    static String longToIP(long ip) {
        return ((ip >> 24) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." +
               ((ip >> 8) & 0xFF) + "." + (ip & 0xFF);
    }
}
