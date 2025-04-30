import java.awt.Color;

public enum AppColor {
  WHITE(new Color(240, 238, 234)),
  DEEP_NAVY(new Color(37, 39, 58)),
  
  OFF_BUTTON(new Color(244, 67, 54)),        // red
  ERROR_BUTTON(new Color(244, 67, 54)),        // red
  ON_BUTTON(new Color(139, 195, 74));        // greenish

  private final Color color;

  AppColor(Color color) {
    this.color = color;
  }

  public Color get() {
    return color;
  }
}
