package CompanyWise.Oracle.Stack;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class ValidParenthesis {

    boolean isValid(String paranthesisString){
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> paramMap = new HashMap<Character,Character>();
        paramMap.put('[',']');
        paramMap.put('{','}');
        paramMap.put('(',')');
        for(int i = 0;i < paranthesisString.length()-1 ;i++){
            Character ch = paramMap.get(paranthesisString.charAt(i));
            if( ch != null){
                stack.push(paranthesisString.charAt(i));
            }else{
                if( stack.isEmpty()){
                    return false;
                }
                Character leftCH = stack.peek();
                Character correctCH = paramMap.get(leftCH);
                char rightCH = paranthesisString.charAt(i); 
                if(correctCH.charValue() != rightCH){
                    return false;
                }else{
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter String");
        String input = scan.next();

        ValidParenthesis obj = new ValidParenthesis();
        System.out.println("IsValid String"+ obj.isValid(input));
        
    }
    
}
