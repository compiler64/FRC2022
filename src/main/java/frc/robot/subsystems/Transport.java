// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Transport extends SubsystemBase {
  private VictorSPX m_motorBelt = new VictorSPX(PortMap.MOTOR_TRANSFER_ID);
  /** Creates a new Transport. */
  public Transport() {}

  public void run(double speed) {
    m_motorBelt.set(ControlMode.PercentOutput, speed);
  }
}
