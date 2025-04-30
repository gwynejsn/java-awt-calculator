public class Test {
  public static void main(String[] args) {
    PostfixNotation p = new PostfixNotation();
    System.out.println(p.infixToPostfix("5.3 + ( ( 1 * 3 ) * 4 )"));
  }
}
