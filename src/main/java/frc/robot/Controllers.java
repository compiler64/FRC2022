package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * A static class for reading data from the controllers.
 */
public class Controllers {
  private static XboxController driverController = new XboxController(PortMap.XBOX_DRIVER_PORT);
  private static XboxController climberController = new XboxController(PortMap.XBOX_CLIMBER_PORT);

  /**
   * Gets the value of a joystick axis.
   * @param axis the axis number
   * @param driver if the driver controller is being used
   * @return the value of the axis
   */
  public static double GetRawAxis(int axis, boolean driver) {
    if (driver) {
      return driverController.getRawAxis(axis);
    }
    else {
      return climberController.getRawAxis(axis);
    }
  }

  /**
   * Returns true right after a button is pressed.
   * @param button the button number
   * @param driver if the driver controller is being used
   * @return if the button has just been pressed
   */
  public static boolean isButtonPressed(int button, boolean driver) {
    if (driver) {
      return driverController.getRawButtonPressed(button);
    }
    else {
      return climberController.getRawButtonPressed(button);
    }
  }

  /**
   * Returns true right after a button is released.
   * @param button the button number
   * @param driver if the driver controller is being used
   * @return if the button has just been released
   */
  public static boolean isButtonReleased(int button, boolean driver) {
    if (driver) {
      return driverController.getRawButtonReleased(button);
    }
    else {
      return climberController.getRawButtonReleased(button);
    }
  }
}
