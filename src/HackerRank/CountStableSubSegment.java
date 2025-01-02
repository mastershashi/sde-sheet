public class CountStableSubSegment{

    //countStableSubSegment

    public int countStableSubSegmentFunction(int n, int[] server){
        int i = 0;
        int j = n-1;
        
    }

    public static void main(String[] args) {
        CountStableSubSegment obj = new CountStableSubSegment();
        int server[] = {9,3,3,3,9};
        int count= obj.countStableSubSegmentFunction(5,server );
        System.out.println(count);
    }

}