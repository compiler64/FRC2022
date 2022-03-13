// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Transport;

/**
 * stops the intake;
 */
public class StopIntake extends CommandBase {
  
  private Intake m_intake;
  private Transport m_transport;
  /**
   * Stops the intake.
   * @param intake the intake subsystem
   * @param transport the transport subsystem
   */
  public StopIntake(Intake intake, Transport transport) {
    m_intake = intake;
    m_transport = transport;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake, transport);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setIntakeSpeed(0);
    m_transport.run(0);
    m_intake.raise();
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
    return false;
  }
}
