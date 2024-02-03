package frc.robot;

import edu.wpi.first.wpilibj.Timer;
import frc.lib.PIDGains;

public class Constants {
    public static final class LEDs {
        public static final double NoTag = 0.61; // Limelight not seeing any tags
        public static final double RunAuto = 0.77; // LimeLight being a good little boy.
        public static final double SpkrRead = 0.87; // LimeLight sees the Speaker AprilTags
        public static final double AmpRead = 0.93; // Limelight sees the Amp AprilTag
        public static final double SorcRead = 0.57; // Limelight sees the Source AprilTag
    }

    public static final class OperatorInter {
        public static final int DriverController = 1;
        public static final int ManipController = 0;
        public static final double kDriveDeadband = 0.05;
        public static final double kArmManualDeadband = 0.05;
        public static final double kArmManualScale = 0.5;
    }

    public static final class Drivetrain {
        public static final int kFrontLeftCanId = 1;
        public static final int kFrontRightCanId = 4;
        public static final int kRearLeftCanId = 3;
        public static final int kRearRightCanId = 2;

        public static final boolean kFrontLeftInverted = false;
        public static final boolean kFrontRightInverted = true;
        public static final boolean kRearLeftInverted = false;
        public static final boolean kRearRightInverted = true;
        
        public static final PIDGains kDriveGains = new PIDGains(0.8, 0.0, 0.0);

        public static final int kCurrentLimit = 40; //Lower to 30 if motors pop the breaker again. Initial value was 55
        public static final double kTurningScale = 0.5;
        public static final double kDistanceConstant = 6 * Math.PI / 42;
        public static final int kStart = 0;

        public static final double kDistanceConversion = 1 / 6 * Math.PI;
        public static final double kP = 1.2;
        public static final double kI = 0.5;
        public static final double kD = 0.35;
        public static final double turnP = 0.5;
        public static final double turnI = 0.0;
        public static final double turnD = 0.0;
        public static double errorSumL = 0;
        public static double errorSumR = 0;
        public static double lastErrorL = 0;
        public static double lastErrorR = 0;
        public static double errorSum = 0;
        public static double lastError = 0;
        public static final double ilimit = 1;
        public static double lastTimeStampL = Timer.getFPGATimestamp();
        public static double lastTimeStamp = Timer.getFPGATimestamp();
        public static double lastTimeStampR = Timer.getFPGATimestamp();
        public static final double speedLimit = .15;
    }
}
