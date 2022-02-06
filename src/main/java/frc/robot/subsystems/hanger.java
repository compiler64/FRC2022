package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.PortMap;

public class hanger extends SubsystemBase {
    private CANSparkMax motor1 = new CANSparkMax(PortMap.MOTOR_CLIMBER1_ID, MotorType.kBrushless);
    private CANSparkMax motor2 = new CANSparkMax(PortMap.MOTOR_CLIMBER2_ID, MotorType.kBrushless);

    public hanger() {

    }

    public void setMotors(double speed) {
        motor1.set(speed);
        motor2.set(speed);
    }
}
