package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class hanger extends SubsystemBase {
    private CANSparkMax extenderMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID, MotorType.kBrushless);
    private CANSparkMax rotatorMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_ROTATE_ID, MotorType.kBrushless);

    public hanger() {

    }

    public void setExtenderSpeed(double extenderSpeed) {
        extenderMotor.set(extenderSpeed);
    }

    public void setRotatorSpeed(double rotatorSpeed) {
        rotatorMotor.set(rotatorSpeed);
    }
}
