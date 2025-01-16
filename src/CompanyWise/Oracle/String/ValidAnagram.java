package CompanyWise.Oracle.String;

public class ValidAnagram {

    boolean isValid(String first , String second){
        if( first.length() != second.length()){
            return false;
        }
        int []count = new int[26];

        for(int i = 0 ;i < first.length() ;i++){
            count[first.charAt(i)- 'a']++;
            count[second.charAt(i) - 'a']--;
        }

        for(int i : count){
            if ( i != 0){
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        ValidAnagram obj = new ValidAnagram();
       System.out.println("Is Valid Anagram "+ obj.isValid("anagram", "ganrma"));
    }
}
