import java.math.BigInteger;

public class PowerOfTwo{

    static long powerOfTwo(Long n){
        return 1L << n;
    }
    public static void main(String[] args) {
        
        System.out.println(PowerOfTwo.powerOfTwo(32L));
    }
}