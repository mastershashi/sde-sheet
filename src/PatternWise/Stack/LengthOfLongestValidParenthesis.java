package PatternWise.Stack;

import java.util.Stack;

public class LengthOfLongestValidParenthesis {

    public int longestValidParentheses(String s){
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = 0;
        for(int i =0 ;i < s.length() ;i++){
            char ch = s.charAt(i);
            if(ch =='(' || ch =='{' || ch == '['){
                stack.push(i);
            }else{
                stack.pop();

                if(!stack.isEmpty()){
                    maxLen = Math.max(maxLen, i - stack.peek());
                }else{
                    stack.push(i);
                }
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LengthOfLongestValidParenthesis solution = new LengthOfLongestValidParenthesis();
        String s = "({()}";
        System.out.println(solution.longestValidParentheses(s));
    }
    
}
