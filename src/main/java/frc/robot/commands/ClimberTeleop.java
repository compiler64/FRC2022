package frc.robot.commands;

import static frc.robot.Constants.*;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;
import frc.robot.PortMap;
import frc.robot.subsystems.Hanger;
/**
 * Controls the climber with an Xbox controller.
 */
public class ClimberTeleop extends CommandBase {
    private Hanger m_hanger;
    
    /**
     * Creates a new ClimberTeleop command.
     * @param hanger the hanger subsystem of the robot
     */
    public ClimberTeleop(Hanger hanger) {
        m_hanger = hanger;

        addRequirements(hanger);
    }

    @Override
    public void execute() {
        double leftStickY = Controllers.GetRawAxis(PortMap.XBOX_LS_Y, false);
        boolean aButton = Controllers.isButtonPressed(PortMap.XBOX_BUTTON_CLIMBER_ROTATE, false);

        boolean extenderAtUpperLimit = m_hanger.getExtenderPosition() >= CLIMBER_EXTENDER_UPPER_LIMIT;
        // boolean extenderAtLowerLimit = m_hanger.getExtenderPosition() <= CLIMBER_EXTENDER_LOWER_LIMIT;

        if (Math.abs(leftStickY) < JOYSTICK_BUFFER) {
            leftStickY = 0;
        }

        double leftPower = leftStickY * MOTOR_POWER_FACTOR;
        
        if (leftPower > 1 || leftPower < -1) {
            leftPower = 0;
        }

        // if (extenderAtLowerLimit && leftPower < 0) {
        //     leftPower = 0;
        // }
        if (extenderAtUpperLimit && leftPower > 0) {
            leftPower = 0;
        }

        m_hanger.setExtenderSpeed(leftPower * .5);
        
        if (aButton) {
            Value value = m_hanger.getRotatorValue();
            if (value == Value.kForward) {
                m_hanger.setRotatorValue(Value.kReverse);
            } else if (value == Value.kReverse) {
                m_hanger.setRotatorValue(Value.kForward);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_hanger.setExtenderSpeed(0);
        
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
