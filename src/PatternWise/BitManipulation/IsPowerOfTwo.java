package PatternWise.BitManipulation;

public class IsPowerOfTwo {
    static boolean isPowerOfTwo(int n){

        return n > 0 && (n & (n-1)) == 0;
        // if it's a power of two then there will be only 1 bit that is 1 
        // ex: 4 : 0100
        // ex: 8 : 1000
        // ex: 16: 10000

        // so n-1 will contains only 1  0 bit 
        // ex: 15 : 01111
        // if 16 & 15 = 10000 & 01111  = 00000  then yes it is power of 2 provided n > 0
    }
    public static void main(String[] args) {
        System.out.println(IsPowerOfTwo.isPowerOfTwo(8));
    }
    
}
