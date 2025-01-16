import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Question3 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("situation", "sitting", "sitty");

        // find longest common prefix 
        Optional<String> longestCommonPrefix = list.stream()
        .reduce(
            (s1,s2) ->{
                int i = 0;
                while(i < s1.length() && i < s2.length() && s1.charAt(i) == s2.charAt(i)){
                    i++;
                }
                return s1.substring(0, i);
            });
         System.out.println(longestCommonPrefix.get());   
    }
}
