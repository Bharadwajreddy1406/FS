import java.util.*;

public class IPClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip = sc.nextLine().trim();
        int firstOctet = Integer.parseInt(ip.split("\\.")[0]);
        String ipClass = "";
        if(firstOctet >= 1 && firstOctet <= 127){
            ipClass = "Class A";
        } else if(firstOctet >= 128 && firstOctet <= 191){
            ipClass = "Class B";
        } else if(firstOctet >= 192 && firstOctet <= 223){
            ipClass = "Class C";
        } else if(firstOctet >= 224 && firstOctet <= 239){
            ipClass = "Multicast (Class D)";
        } else {
            ipClass = "Unknown";
        }
        System.out.println(ipClass);
    }
}
