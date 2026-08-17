import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindAllAnagram {
    static List<String> findAllAnagram(String s, String p) {
        List<String> result = new ArrayList<>();
        if (s.length() < p.length())
            return result;
        int[] p_count = new int[26];
        int[] s_count = new int[26];
        for (char c : p.toCharArray()) {
            p_count[c - 'a']++;
        }
        int end = 0;
        int start = 0;
        while (end < s.length()) {
            s_count[s.charAt(end) - 'a']++;
            if (end - start + 1 == p.length()) {
                if (Arrays.equals(p_count, s_count)) {
                    result.add(s.substring(start, end + 1));
                }
                s_count[s.charAt(start) - 'a']--;
                start++;
            }
            end++;
        }
        return result;
    }

    public static void main(String[] args) {

        List<String> anagramList = FindAllAnagram.findAllAnagram("acbbaacbcbaacbbac", "abc");
        for (String num : anagramList) {
            System.out.println(num);
        }

    }
}
