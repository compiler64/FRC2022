// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.commands.OneBallAuto;
// import frc.robot.commands.AutoCommandNew;
// import frc.robot.commands.ClimberTeleop;
// import frc.robot.commands.DriveTeleop;
// import frc.robot.commands.FollowBall;
// import frc.robot.commands.IntakeTeleop;
// import frc.robot.commands.ShootTeleop;
// import frc.robot.commands.SingleSolenoid;
import frc.robot.commands.TeleopCommand;
import frc.robot.commands.TwoBallAuto;
// import frc.robot.commands.TriangleAutoCommand;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Hanger;
import frc.robot.subsystems.Intake;

// import static frc.robot.Constants.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final Gyro m_gyro = new Gyro();
    private final DriveTrain m_driveTrain = new DriveTrain(m_gyro);
    private final Camera m_camera = new Camera();
    private final Intake m_intake = new Intake();
    private final Shooter m_shooter = new Shooter();
    private final Hanger m_hanger = new Hanger();
    private final Transport m_transport = new Transport();

    private final PneumaticsControlModule m_pneumaticsModule = new PneumaticsControlModule();

    /**
     * Returns the camera subsystem of the robot.
     * Getter for private final field m_camera.
     * @return the camera subsystem
     */
    public final Camera getCamera() {
        return m_camera;
    }

    /**
     * Returns the pneumatics control module.
     * @return the pneumatics control module
     */
    public final PneumaticsControlModule getPneumaticsModule() {
        return m_pneumaticsModule;
    }

    // private final OneBallAuto m_autonomousCommand = new OneBallAuto(m_driveTrain, m_gyro, m_camera, m_intake, m_transport, m_shooter);
    private final TwoBallAuto m_autonomousCommand = new TwoBallAuto(m_driveTrain, m_gyro, m_camera, m_intake, m_transport, m_shooter);
    private final TeleopCommand m_teleopCommand = new TeleopCommand(m_driveTrain, m_gyro, m_camera, m_intake, m_shooter, m_transport, m_hanger);
    
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        Shuffleboard.getTab("main").addBoolean("Compressor On", m_pneumaticsModule::getCompressor);
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
     * it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_autonomousCommand;
    }

    /**
     * Returns the command that will run during teleop mode.
     * @return the teleop command
     */
    public Command getTeleopCommand() {
        return m_teleopCommand;
    }

    /**
     * Returns the command that will run during test mode.
     * @return the test command
     */
    public Command getTestCommand() {
        return null;
    }
}
