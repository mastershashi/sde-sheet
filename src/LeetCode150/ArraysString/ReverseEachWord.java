public class ReverseEachWord {
    public static String reverseWords(String str){
        if(str == null || str.length() == 0) return str;
        str = str.trim();
        String [] words = str.split(" ");
        int left = 0;
        int right = words.length - 1;
        StringBuilder sb = new StringBuilder();
        while (right >= left){
            if(words[right].length() == 0 ||Character.isWhitespace(words[right].charAt(0))){
                right--;
                continue;
            }
            sb.append(words[right]).append(" ");
            right--;
        }
        return sb.toString().trim();
    }
    public static void main(String[] args) {
        String s = "  a    good   example  ";
        System.out.println(reverseWords(s));
    }
    
}
