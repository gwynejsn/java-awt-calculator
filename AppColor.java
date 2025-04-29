import java.awt.Color;

public enum AppColor {
  WHITE(new Color(240, 238, 234)),
  DEEP_NAVY(new Color(37, 39, 58));

  private final Color color;

  AppColor(Color color) {
    this.color = color;
  }

  public Color get() {
    return color;
  }
}
