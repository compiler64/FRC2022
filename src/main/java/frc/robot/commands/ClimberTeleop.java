package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.hanger;

import static frc.robot.Constants.*;

public class ClimberTeleop extends CommandBase {
    private hanger m_hanger;
    
    public ClimberTeleop(hanger hanger) {
        m_hanger = hanger;

        addRequirements(hanger);
    }

    @Override
    public void execute() {
        double leftStickY = Controllers.GetRawAxis(PortMap.XBOX_LS_Y, false);
        double rightStickX = Controllers.GetRawAxis(PortMap.XBOX_RS_X, false);

        boolean extenderAtUpperLimit = false;
        boolean rotatorAtUpperLimit = false;
        boolean extenderAtLowerLimit = false;
        boolean rotatorAtLowerLimit = false;

        if (Math.abs(leftStickY) < JOYSTICK_BUFFER) {
            leftStickY = 0;
        }

        if (Math.abs(rightStickX) < JOYSTICK_BUFFER) {
            rightStickX = 0;
        }

        double leftPower = leftStickY * MOTOR_POWER_FACTOR;
        double rightPower = rightStickX * MOTOR_POWER_FACTOR;

        if (leftPower > 1 || leftPower < -1) {
            leftPower = 0;
        }
        if (rightPower > 1 || rightPower < -1) {
            rightPower = 0;
        }

        if (extenderAtLowerLimit && leftPower < 0) {
            leftPower = 0;
        }
        if (extenderAtUpperLimit && leftPower > 0) {
            leftPower = 0;
        }

        if (rotatorAtLowerLimit && rightPower < 0) {
            rightPower = 0;
        }
        if (rotatorAtUpperLimit && rightPower >0) {
            rightPower = 0;
        }

        m_hanger.setExtenderSpeed(leftPower);
        m_hanger.setRotatorSpeed(rightPower);
    }

    @Override
    public void end(boolean interrupted) {
        m_hanger.setExtenderSpeed(0);
        m_hanger.setRotatorSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
