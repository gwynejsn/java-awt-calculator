import java.awt.Button;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Calculator extends Frame {
  private CalculatorButton[] buttonValues;
  private Button[] buttons;
  private Label output;
  public static void main(String[] args) {
    new Calculator(400, 600);
  }

  public Calculator(int sizeX, int sizeY) {
    buttonValues = CalculatorButton.values();
    buttons = new Button[buttonValues.length];

    // create output screen
    output = new Label();
    Panel outScreen = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 20), new FlowLayout(), new Component[]{output}, AppColor.WHITE.get());

    // create buttons
    generateCalculatorButtons();
    Panel buttonsPanel = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 80), new GridLayout(6, 4), buttons);


    // add everything up
    setLayout(new GridLayout(2, 1));
    add(outScreen);
    add(buttonsPanel);

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

  private Component[] generateCalculatorButtons() {
    for (int i = 0; i < buttonValues.length; i++) {
      Button button = new Button(buttonValues[i].getLabel());
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Button buttonClicked = (Button) e.getSource();
          String labelClicked = buttonClicked.getLabel();

          if (labelClicked == CalculatorButton.OFF.getLabel()) offButtonClicked();
          else if (labelClicked == CalculatorButton.ON.getLabel()) onButtonClicked();
          else if (labelClicked == CalculatorButton.CLEAR.getLabel()) output.setText("");
          else if (labelClicked == CalculatorButton.EQUALS.getLabel()) evaluateAnswer();
          else output.setText(output.getText() + labelClicked);
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
  }

  private void onButtonClicked() {
    for (Button button : buttons) {
      button.setEnabled(true);
    }
  }

  private void evaluateAnswer() {

  }
}

