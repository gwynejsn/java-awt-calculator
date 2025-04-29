import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Panel;

public class ComponentBuilder {

  static Panel panelBuilder(int sizeX, int sizeZ, LayoutManager layoutManager, Component[] components) {
    return panelBuilder(sizeX, sizeZ, layoutManager, components, null);
  }

  static Panel panelBuilder(int sizeX, int sizeZ, LayoutManager layoutManager, Component[] components, Color color) {
    Panel panel = new Panel();
    panel.setSize(sizeX, sizeZ);
    panel.setLayout(layoutManager);
    if (color != null) panel.setBackground(color);

    if (components != null) {
      for (Component c: components)
        if (c != null) panel.add(c);
    }

    return panel;
  }

  public static Component[] generateCalculatorButtons() {
    CalculatorButton[] buttonValues = CalculatorButton.values();
    Component[] buttons = new Component[buttonValues.length];

    for (int i = 0; i < buttonValues.length; i++)
      buttons[i] = new Button(buttonValues[i].getLabel());

    return buttons;
  }
}