package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

public class Controllers {
  private static XboxController driverController = new XboxController(PortMap.XBOX_DRIVER_PORT);
  private static XboxController climberController = new XboxController(PortMap.XBOX_CLIMBER_PORT);

  public static double GetRawAxis(int axis, boolean driver) {
    if (driver) {
      return driverController.getRawAxis(axis);
    }
    else {
      return climberController.getRawAxis(axis);
    }
  }

  public static boolean isButtonPressed(int button, boolean driver) {
    if (driver) {
      return driverController.getRawButtonPressed(button);
    }
    else {
      return climberController.getRawButtonPressed(button);
    }
  }

  public static boolean isButtonReleased(int button, boolean driver) {
    if (driver) {
      return driverController.getRawButtonReleased(button);
    }
    else {
      return climberController.getRawButtonReleased(button);
    }
  }
}
