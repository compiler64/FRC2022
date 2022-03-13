// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean
 * constants. This class should not be used for any other purpose. All constants
 * should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */

public final class Constants {
    public static final double JOYSTICK_BUFFER = 0.1;
    public static final double MOTOR_POWER_FACTOR = 1;
    public static final double AUTO_SPEED = 0.3; // TODO raise speed
    public static final double AUTO_TIME = 15;
    public static final double AUTO_INTAKE_SPEED = 1;
    public static final double AUTO_BELT_SPEED = -1;
    public static final double AUTO_FLYWHEEL_SPEED = -0.55;
    public static final double BALL_SHOOT_TIME = 5; // TODO make shoot time shorter
    public static final double INDEXING_WHEEL_SPEED = -0.5;
    public static final double TRANSFER_SPEED = -.5;
    public static final double REDUCED_SPEED_FACTOR = 0.25;

    public static final double WHEEL_DIAMETER = 6.0 / 12.0;
    public static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
    public static final double DISTANCE_PER_PULSE = (1 / 4096) / (8.2) * WHEEL_CIRCUMFERENCE;

    public static final double CLIMBER_EXTENDER_UPPER_LIMIT = 2.083333;
    public static final double CLIMBER_EXTENDER_LOWER_LIMIT = 0;

    public static final int RED_PIPELINE = 0;
    public static final int BLUE_PIPELINE = 1;

    public static final double CAMERA_Y_OF_BALL_AT_INTAKE = -20;
    public static final double BAD_BALL_MAX_Y = 0;
}
