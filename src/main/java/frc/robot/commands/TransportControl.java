// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Transport;

public class TransportControl extends CommandBase {
  private Transport m_transport;
  private double m_transportSpeed;
  private boolean transportOn = false;

  /** Creates a new TransportControl. */
  public TransportControl(Transport transport, double speed) {
    m_transport = transport;
    m_transportSpeed = speed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_TRANSFER, true)) {
      if (!transportOn) {
        m_transport.run(m_transportSpeed);
      } else {
        m_transport.run(0);
      }
      transportOn = !transportOn;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
