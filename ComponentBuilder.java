import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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

  static Button buttonBuilder(String label) {
    Button button = new Button(label);
    button.setFont(new Font("Arial", Font.PLAIN, 20));

    // customize colors
    if (label.equals(CalculatorButton.OFF.getLabel()))
      button.setForeground(AppColor.OFF_BUTTON.get());
    else if (label.equals(CalculatorButton.ON.getLabel()))
      button.setForeground(AppColor.ON_BUTTON.get());
    
    return button;
  }
}