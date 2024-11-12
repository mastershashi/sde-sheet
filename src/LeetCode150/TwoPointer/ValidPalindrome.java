package LeetCode150.TwoPointer;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        if(s == null || s.length() < 0) return false; 
         int left = 0;
         int right = s.length() -1;
         s = s.toLowerCase();
         while(left < right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left++;
            }else if(!Character.isLetterOrDigit(s.charAt(right))){
                right--;
            }else{
                if(s.charAt(left) != s.charAt(right)) return false;
                left ++;
                right--;
            }
         }
         return true;
          
    }
    public static void main(String args[]){
        ValidPalindrome obj = new ValidPalindrome();
        String  s = "A man, a plan, a canal: Panama";
        System.out.println(obj.isPalindrome(s));
}
}