// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

/**
 * Starts the intake.
 */
public class StartIntake extends CommandBase {
  private Intake m_intake;
  private Transport m_transport;
  private double m_intakeSpeed;
  private double m_transportSpeed;
  private double m_intakeTime;
  

  /**
   * Starts the intake.
   * @param intake the intake subsystem
   * @param transport the transport subsystem
   * @param intakeSpeed the speed to run the intake at
   * @param transportSpeed the speed to run the transport at.
   */
  public StartIntake(Intake intake, Transport transport, double intakeSpeed, double transportSpeed) {
    m_intake = intake;
    m_transport = transport;
    m_intakeSpeed = intakeSpeed;
    m_transportSpeed = transportSpeed;
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.raise();
    m_intake.setIntakeSpeed(m_intakeSpeed);
    m_transport.run(m_transportSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
