import java.awt.Button;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame {
  private CalculatorButton[] buttonValues;
  private Button[] buttons;
  private Label output;
  private PostfixNotation pn;
  private KeyListener calculatorKeyListeners;
  public static void main(String[] args) {
    new Calculator(400, 600);
  }

  public Calculator(int sizeX, int sizeY) {
    pn = new PostfixNotation();
    buttonValues = CalculatorButton.values();
    buttons = new Button[buttonValues.length];

    calculatorKeyListeners = new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_C)
          handleInput(CalculatorButton.CLEAR.getLabel());
        if (c == KeyEvent.VK_ENTER || c == '=')
          handleInput(CalculatorButton.EQUALS.getLabel());
        else if (c == KeyEvent.VK_BACK_SPACE)
          handleInput(CalculatorButton.BACKSPACE.getLabel());
        else
          handleInput(c + "");
      }
    };

    // create output screen
    output = new Label(" ");
    output.setForeground(AppColor.WHITE.get());
    output.setFont(new Font("Arial", Font.PLAIN, 30));
    Panel outScreen = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 40), new GridLayout(1, 1), new Component[]{output}, AppColor.DEEP_NAVY.get());

    // create buttons
    generateCalculatorButtons();
    Panel buttonsPanel = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 80), new GridLayout(6, 4), buttons);


    // add everything up
    setLayout(new GridLayout(2, 1));
    add(outScreen);
    add(buttonsPanel);

    // make keyboards also functional
    setFocusable(true);
    requestFocusInWindow();
    this.addKeyListener(calculatorKeyListeners);

    setSize(sizeX, sizeY);
    setVisible(true);

    addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }

  private double getPercentage(int screenSize, int percentage) {
    return screenSize * (percentage / 100);
  }

  private void handleInput(String labelClicked) {
    if (labelClicked.equals(CalculatorButton.OFF.getLabel())) offButtonClicked();
    else if (labelClicked.equals(CalculatorButton.ON.getLabel())) onButtonClicked();
    else if (labelClicked.equals(CalculatorButton.CLEAR.getLabel())) output.setText("");
    else if (labelClicked.equals(CalculatorButton.EQUALS.getLabel())) evaluateAnswer();
    else if (labelClicked.equals(CalculatorButton.BACKSPACE.getLabel())) backspaceClicked();
    else if (pn.isOperator(labelClicked)) output.setText(output.getText() + " " + labelClicked + " ");
    else if ((labelClicked.charAt(0) >= '0' && labelClicked.charAt(0) <= '9') || labelClicked.equals("."))
      output.setText(output.getText() + labelClicked);
  }

  private Component[] generateCalculatorButtons() {
    for (int i = 0; i < buttonValues.length; i++) {
      Button button = ComponentBuilder.buttonBuilder(buttonValues[i].getLabel());
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Button buttonClicked = (Button) e.getSource();
          String labelClicked = buttonClicked.getLabel();

          handleInput(labelClicked);
        }
      });
      buttons[i] = button;
    }

    return buttons;
  }

  private void offButtonClicked() {
    for (Button button : buttons) {
      if (!(button.getLabel() == CalculatorButton.ON.getLabel()))
        button.setEnabled(false);
    }
    output.setText(" ");
    this.removeKeyListener(calculatorKeyListeners);
  }

  private void onButtonClicked() {
    for (Button button : buttons) {
      button.setEnabled(true);
    }
    this.addKeyListener(calculatorKeyListeners);
    requestFocus();
  }

  private void evaluateAnswer() {
    String infix = output.getText().trim();
    if ((infix.charAt(0) + "").equals(CalculatorButton.EQUALS.getLabel()))
      // remove = when the user continuously do operations
      output.setText("= " + pn.evaluatePostfix(pn.infixToPostfix(infix.substring(2))));
    else
      output.setText("= " + pn.evaluatePostfix(pn.infixToPostfix(infix)));
  }

  private void backspaceClicked() {
    String currOut = output.getText();
    if (currOut.length() > 0)
      if (
        pn.isOperator(currOut.charAt(currOut.length() - 1) + "") ||
        // case where +{space}number to delete operator instead of space
        pn.isOperator(currOut.charAt(currOut.length() - 2) + "")

      )
        output.setText(currOut.substring(0, currOut.length() - 2));
      else
        output.setText(currOut.substring(0, currOut.length() - 1));
  }
}

