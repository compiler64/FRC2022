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

        if (Math.abs(leftStickY) < JOYSTICK_BUFFER) {
            leftStickY = 0;
        }

        double power = leftStickY * MOTOR_POWER_FACTOR;

        if (power > 1 || power < -1) {
            power = 0;
        }

        m_hanger.setMotors(power);
    }

    @Override
    public void end(boolean interrupted) {
        m_hanger.setMotors(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
