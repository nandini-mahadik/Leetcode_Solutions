import java.util.Stack;

class Solution {
    public int scoreOfParentheses(String s) {
        int score;

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);

            if(ch == '('){
                stack.push(0);
            }
            else if(ch == ')'){
                int top = stack.pop();

                if(top == 0){
                    score = 1;
                }
                else{
                    score = 2 * top;
                }
                
                stack.push(stack.pop() + score);

            }
            
        }
        return stack.peek();
    }
}