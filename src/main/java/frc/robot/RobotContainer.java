// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.DriveTeleop;
import frc.robot.commands.FollowBall;
import frc.robot.commands.SingleSolenoid;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Pneumatics;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Gyro m_gyro = new Gyro();
  private final Pneumatics m_pneumatics = null;//new Pneumatics();
  private final Camera m_camera = new Camera();

  public final Camera getCamera() {
    return m_camera;
  }

  private final AutoCommand m_autonomousCommand = new AutoCommand(m_driveTrain, m_gyro, m_pneumatics, m_camera);
  public final DriveTeleop m_driveCommand = new DriveTeleop(m_driveTrain);
  public final FollowBall m_followBall = new FollowBall(m_driveTrain, m_camera, Constants.AUTO_SPEED);
 // public final SingleSolenoid m_singleSolenoid = new SingleSolenoid(m_pneumatics);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autonomousCommand;
  }

  public Command getDriveCommand() {
    return m_driveCommand;
  }

  public Command getFollowBallCommand() {
    return m_followBall;
  }

  public Command getTestCommand() {
    // return m_driveCommand;
    return m_followBall;
  }
}
