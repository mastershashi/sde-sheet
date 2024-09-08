import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ValidParanthesis {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Map<Character, Character> paramMap = new HashMap<Character, Character>();
        System.out.println("Enter String");
        String input = scan.nextLine();
        
        paramMap.put('[', ']');
        paramMap.put('{', '}');
        paramMap.put('(', ')');
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            
            if (paramMap.containsKey(ch)) {
                // If it's an opening bracket, push the corresponding closing bracket onto the stack
                stack.push(paramMap.get(ch));
            } else if (paramMap.containsValue(ch)) {
                // If it's a closing bracket, check if it matches the top of the stack
                if (stack.isEmpty() || stack.pop() != ch) {
                    System.out.println("Invalid String");
                    return;
                }
            }
        }
        
        // If the stack is empty, all brackets were matched correctly
        if (stack.isEmpty()) {
            System.out.println("Valid String");
        } else {
            System.out.println("Invalid String");
        }
    }
}