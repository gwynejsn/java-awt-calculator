public class TestPost {
  public static void main(String[] args) {
    PostfixNotation pn = new PostfixNotation();
    String infix = "( 3 + 4 ) * ( 5 - 2 ) / ( 6 + ( 1 * 2 ) ) - 8 + 7";
    System.out.println(pn.infixToPostfix(infix));
    System.out.println(pn.evaluatePostfix(pn.infixToPostfix(infix)));
    
  }
}
