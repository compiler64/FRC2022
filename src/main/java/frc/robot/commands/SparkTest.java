package frc.robot.commands;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Controllers;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class SparkTest extends CommandBase {
    CANSparkMax sparkMax = new CANSparkMax(13, MotorType.kBrushless);
    
    RelativeEncoder encoder = sparkMax.getEncoder();
    public SparkTest() {
        Shuffleboard.getTab("main").addNumber("spark encoder",  () -> encoder.getVelocity());
        SparkMaxPIDController pidController = sparkMax.getPIDController();
    }

    @Override
    public void execute() {
        if (Controllers.isButtonPressed(1, true)) {
            sparkMax.set(1);
        }
        if (Controllers.isButtonReleased(1, true)) {
            sparkMax.set(0);
        }
    }
}

