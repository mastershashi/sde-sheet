package PatternWise.BinarySearch;

public class KokoEatingBanana {
    public static boolean canFinish(int piles[],int hours , int speed ){
        int totalhours = 0;
        for ( int pile :piles){
            totalhours += Math.ceil((double)pile/speed);
        }
        return totalhours <= hours;

    }
    public static int findMinKToFinishBananaPile(int piles[] , int hours){

        int left  = 0;
        int right = piles.length - 1;
        while(left < right){
            int mid = left + (right -left)/2;
            if(canFinish(piles, hours, mid)){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;

    }
    public static void main(String[] args) {
        int piles[] = {3,6,7,11};
        int hours = 8;
        System.out.println(KokoEatingBanana.findMinKToFinishBananaPile(piles, hours));
        
    }
    
}
