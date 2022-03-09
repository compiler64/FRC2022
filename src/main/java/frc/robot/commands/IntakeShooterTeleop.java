// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Transport;

/** controls both the intake and the shooter. */
public class IntakeShooterTeleop extends CommandBase {
  private Shooter m_shooter;
  private Transport m_transport;
  private Intake m_intake;
  private double m_flywheelSpeed;
  private double m_indexingWheelSpeed;
  private double m_transferSpeed;
  private double m_intakeSpeed;
  private boolean isDown = false;
  private boolean intakeOn = false;
  private boolean flywheelOn = false;
  private boolean indexingWheelOn = false;
  /** Creates a new IntakeShooterTeleop. */
  public IntakeShooterTeleop(Shooter shooter, Transport transport, Intake intake, double flywheelSpeed, double indexingWheelSpeed, double transferSpeed, double intakeSpeed) {
    m_shooter = shooter;
    m_transport = transport;
    m_intake = intake;
    m_flywheelSpeed = flywheelSpeed;
    m_indexingWheelSpeed = indexingWheelSpeed;
    m_transferSpeed = transferSpeed;
    m_intakeSpeed = intakeSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, transport, intake);

    Shuffleboard.getTab("main").addBoolean("Flywheel On", () -> flywheelOn);
    Shuffleboard.getTab("main").addBoolean("Indexing Wheel On", () -> indexingWheelOn);
    Shuffleboard.getTab("main").addBoolean("intake on", () -> intakeOn);
    Shuffleboard.getTab("main").addBoolean("intake down", () -> isDown);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // raise and lower the intake
    if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE_LIFT, false)) {
      if (isDown ) {
          m_intake.raise();
      } else {
          m_intake.lower();
      }
      isDown = !isDown;
  }

  if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_INTAKE_LIFT, false)) {
    m_intake.nutral();
  }

  // turn the intake on
  if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INTAKE_WHEELS, false)){
    intakeOn = !intakeOn;
    m_intake.setSpeed(intakeOn ? m_intakeSpeed : 0);
    System.out.println(intakeOn);
    m_transport.run(intakeOn ? m_transferSpeed : 0);
  }

  // the flywheel
  if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_START_FLYWHEEL, false)) {
    flywheelOn = !flywheelOn;
    m_shooter.setFlywheelSpeed(flywheelOn ? m_flywheelSpeed : 0);
}

  // shoot all balls
  if (Controllers.isButtonPressed(PortMap.XBOX_BUTTON_INDEXING_WHEEL, false)) {
    m_shooter.setIndexingWheelSpeed(m_indexingWheelSpeed);
    m_transport.run(m_transferSpeed);
    indexingWheelOn = true;
  }
  if (Controllers.isButtonReleased(PortMap.XBOX_BUTTON_INDEXING_WHEEL, false)) {
    m_shooter.setIndexingWheelSpeed(0);
    m_transport.run(0);
    indexingWheelOn = false;
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
