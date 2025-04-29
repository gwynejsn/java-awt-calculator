import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Calculator extends Frame {
  public static void main(String[] args) {
    new Calculator(400, 600);
  }

  public Calculator(int sizeX, int sizeY) {
    // create output screen
    Label output = new Label("some number");

    Panel outScreen = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 20), new FlowLayout(), new Component[]{output}, AppColor.WHITE.get());

    // create buttons
    Panel buttons = ComponentBuilder.panelBuilder(sizeX, (int) getPercentage(sizeY, 80), new GridLayout(6, 4), ComponentBuilder.generateCalculatorButtons());


    // add everything up
    setLayout(new GridLayout(2, 1));
    add(outScreen);
    add(buttons);

    setSize(sizeX, sizeY);
    setVisible(true);
  }

  private double getPercentage(int screenSize, int percentage) {
    return screenSize * (percentage / 100);
  }

}

