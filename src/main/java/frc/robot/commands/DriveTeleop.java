package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.DriveTrain;

// drive in teleop mode
public class DriveTeleop extends CommandBase {
  private DriveTrain m_driveTrain;

    //initialize
  public DriveTeleop(DriveTrain driveTrain) {
    m_driveTrain = driveTrain;
    addRequirements(driveTrain);
  }

  @Override
  public void execute() {
    double leftStickY = -Controllers.GetRawAxis(PortMap.XBOX_LS_Y, true);
    double rightStickX = Controllers.GetRawAxis(PortMap.XBOX_RS_X, true);

    //set the value to zero if it is close to 0
    //System.out.println("Left stick Y: " + leftStickY);
    if (Math.abs(leftStickY) < Constants.JOYSTICK_BUFFER) {
      leftStickY = 0;
    }

    if (Math.abs(rightStickX) < Constants.JOYSTICK_BUFFER) {
      rightStickX = 0;
    }

    double power = leftStickY * Constants.MOTOR_POWER_FACTOR;
    double turningFactor = rightStickX;

    double leftMotorPower = 1;
    double rightMotorPower = 1;

    // If the controller wants to go a direction, it slows down that motor's side.

    if (turningFactor < 0) {
      leftMotorPower += turningFactor;
    } else if (turningFactor > 0) {
      rightMotorPower -= turningFactor;
    }

    // Multiplies the motor power by the original power to scale the speed.

    leftMotorPower *= power;
    rightMotorPower *= power;

    if (power == 0) {

      leftMotorPower = turningFactor * Constants.MOTOR_POWER_FACTOR;
      rightMotorPower = -turningFactor * Constants.MOTOR_POWER_FACTOR;

    }

    if (leftMotorPower > 1 || leftMotorPower < -1 || rightMotorPower > 1 || rightMotorPower < -1) {
      leftMotorPower = 0;
      rightMotorPower = 0;
    }

    // Sets the motors to the speed
    m_driveTrain.setLeftMotors(leftMotorPower);
    m_driveTrain.setRightMotors(rightMotorPower);
  }

  @Override
  public void end(boolean interrupted) {
    // stop both motors
    m_driveTrain.setBothMotors(0);
    System.out.println("Ending drive teleop");
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
