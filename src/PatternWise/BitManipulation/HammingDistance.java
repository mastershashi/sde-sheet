package PatternWise.BitManipulation;

public class HammingDistance {
    public static int hammingDistance(int x, int y) {
        // XOR x and y, then count the number of 1 bits
        int xor = x ^ y;
        int distance = 0;
        
        // Count the number of 1's in the XOR result
        while (xor != 0) {
            distance += xor & 1;  // Increment distance if the least significant bit is 1
            xor >>= 1;             // Shift xor right by 1 to check the next bit
        }
        
        return distance;
    }
    public static void main(String[] args) {
       System.out.println( HammingDistance.hammingDistance(1,4));
    }
}
