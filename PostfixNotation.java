import java.util.EmptyStackException;

// since this allows decimals, a requirement is to have spaces in parenthesis, operands, and operators
public class PostfixNotation {
  private MySinglyLLStack<String> stack;

  public double evaluatePostfix(String postfix) {
    stack = new MySinglyLLStack<>();
    String[] elements = postfix.split(" ");
    
    for (int i = 0; i < elements.length; i++) {
      String s = elements[i];
      if (isOperator(s)) {
        Double b = Double.parseDouble(stack.pop());
        Double a = Double.parseDouble(stack.pop());

        char c = s.charAt(0);
        switch (c) {
          case '*':
            stack.push((a * b) + "");
            break;
          case '/':
            stack.push((a / b) + "");
            break;
          case '+':
            stack.push((a + b) + "");
            break;
          case '-':
            stack.push((a - b) + "");
            break;
          case '^':
            stack.push((Math.pow(a, b)) + "");
            break;
        }
      } else {
        stack.push(s);
      }
    }

    return Double.parseDouble(stack.pop());
  }
  
  public String infixToPostfix(String infix) {
    stack = new MySinglyLLStack<>();
    String[] elements = infix.trim().split(" ");
    String postfix = "";
    for (int i = 0; i < elements.length; i++) {
      String s = elements[i];

      if (isOperator(s)) {
        while (!stack.isEmpty() && getPrecedence(s) <= getPrecedence(stack.peek()))
          postfix += stack.pop() + " ";
        stack.push(s);
      } else if (s.equals("(")) {
        stack.push(s);
      } else if (s.equals(")")) {
        while (!stack.isEmpty() && !stack.peek().equals("("))
            postfix += stack.pop() + " ";
        stack.pop(); // pop (
      } else {
        postfix += s + " ";
      }
    }
    // empty stack
    while (!stack.isEmpty()) {
        String top = stack.pop();
        if (top.equals("(")) {
            throw new IllegalArgumentException("Unmatched opening parenthesis in expression.");
        }
        postfix += top + " ";
    }
    return postfix;
  }

  public boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") ||
            token.equals("*") || token.equals("/") ||
            token.equals("^");
  }

  private int getPrecedence(String s) {
    char c = s.charAt(0);
    switch (c) {
      case '*':
      case '/':
        return 4;
      case '+':
      case '-':
        return 2;
      case '^':
        return 2;
      default:
        return 1;
    }
  }
}

class MySinglyLLStack <T> {
  Node<T> top;

  MySinglyLLStack() {
    this.top = null;
  }

  T peek() {
    if (isEmpty()) throw new EmptyStackException();
    return top.getData();
  }

  void push(T data) {
    if (isEmpty()) {
      top = new Node<T>(data);
    } else {
      Node<T> newNode = new Node<T>(data);
      newNode.setNext(top);
      top = newNode;
    }
  }

  T pop() {
    if (isEmpty()) throw new EmptyStackException();
    T topData = top.getData();
    top = top.getNext();
    return topData;
  }

  boolean isEmpty() {
    return top == null;
  }
}

class Node <T> {
  private T data;
  private Node<T> next;

  Node(T data) {
    this.data = data;
    this.next = null;
  }

  void setData(T data) {
    this.data = data;
  }

  T getData() {
    return this.data;
  }

  void setNext(Node<T> next) {
    this.next = next;
  }

  Node<T> getNext() {
    return this.next;
  }
}
