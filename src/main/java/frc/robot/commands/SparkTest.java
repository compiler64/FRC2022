package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkTest extends CommandBase {
    CANSparkMax sparkMax = new CANSparkMax(11, MotorType.kBrushless);
    public SparkTest() {
        
    }

    @Override
    public void execute() {
        sparkMax.set(0.15);
    }
}
