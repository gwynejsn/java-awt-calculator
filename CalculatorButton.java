public enum CalculatorButton {
  ON("ON"), OFF("OFF"), CLEAR("C"), BACKSPACE("←"),
  SEVEN("7"), EIGHT("8"), NINE("9"), DIVIDE("/"),
  FOUR("4"), FIVE("5"), SIX("6"), MULTIPLY("*"),
  ONE("1"), TWO("2"), THREE("3"), MINUS("-"),
  ZERO("0"), DOT("."), EQUALS("="), PLUS("+"),
  LEFT_PARENTHESES("("), RIGHT_PARENTHESES(")"), POWER("^");

  private final String label;

  CalculatorButton(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
