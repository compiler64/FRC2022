package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class Hanger extends SubsystemBase {
    private CANSparkMax extenderMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_EXTEND_ID, MotorType.kBrushless);
    private RelativeEncoder extenderEncoder;
    private CANSparkMax rotatorMotor = new CANSparkMax(PortMap.MOTOR_CLIMBER_ROTATE_ID, MotorType.kBrushless);
    private RelativeEncoder rotatorEncoder;

    public Hanger() {
        extenderEncoder = extenderMotor.getEncoder();
        rotatorEncoder = rotatorMotor.getEncoder();
    }

    public void setExtenderSpeed(double extenderSpeed) {
        extenderMotor.set(extenderSpeed);
    }

    public void setRotatorSpeed(double rotatorSpeed) {
        rotatorMotor.set(rotatorSpeed);
    }

    public double getExtenderPosition() {
        return extenderEncoder.getPosition();
    }

    public double getRotatorPosition() {
        return rotatorEncoder.getPosition();
    }
}
