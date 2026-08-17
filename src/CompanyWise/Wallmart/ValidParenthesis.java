package CompanyWise.Wallmart;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {
    boolean validParenthesis(String paranthesis) {

        Map<Character, Character> characterMap = new HashMap<>();
        characterMap.put('{', '}');
        characterMap.put('(', ')');
        characterMap.put('(', ')');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < paranthesis.length(); i++) {
            Character ch = characterMap.get(paranthesis.charAt(i));
            if (ch != null) {
                stack.push(paranthesis.charAt(i));
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character leftCH = stack.peek();
                Character rightCH = characterMap.get(leftCH);
                Character stackEntry = paranthesis.charAt(i);
                if (rightCH.charValue() != stackEntry) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }
        if (stack.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        ValidParenthesis obj = new ValidParenthesis();
        String str = "{()}";
        boolean isValid = obj.validParenthesis(str);
        System.out.println("Is valid :" + isValid);

    }

}
