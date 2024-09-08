import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesis {

    public static void main(String args[]){
        Scanner scan = new Scanner(System.in);
        Map<Character, Character> paramMap = new HashMap<Character,Character>();
        System.out.println("Enter String");
        String input = scan.next();
        paramMap.put('[',']');
        paramMap.put('{','}');
        paramMap.put('(',')');
         Stack<Character> stackArray = new Stack<>();
        for(int i = 0; i< input.length(); i++){
           Character ch = paramMap.get(input.charAt(i));
           System.out.println("ch=: "+ch);
            if(ch != null){
                stackArray.push(input.charAt(i));
            }else{
                if(stackArray.size() == 0){
                    System.out.println("Invalid String");
                    break;
                }
                //Buggy Code
                Character leftBracket = stackArray.peek();
                System.out.println("leftBracket: "+leftBracket);
                Character correctBracket = paramMap.get(leftBracket);
                System.out.println("correctBracket: "+correctBracket);
                if(input.charAt(i) != correctBracket){
                    System.out.println("invalid String");
                }else{
                    stackArray.pop();
                }
            }
        }
        if(stackArray.size() == 0)
        {
            System.out.println("valid String");
        }else{
            System.out.println("Invalid String");
        }

    }
    
}
