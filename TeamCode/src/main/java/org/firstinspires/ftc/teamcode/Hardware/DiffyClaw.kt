package org.firstinspires.ftc.teamcode.Hardware

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.Bot
import org.firstinspires.ftc.teamcode.Config

/**
 * Class for controlling a differential claw
 * Servos are assumed to have a range of 0 degrees to 270 degrees
 */
class DiffyClaw {
    private val diffyServo1 = ServoClass(Bot.opMode.hardwareMap.get(Servo::class.java, Config.diffyServo1), 0.0, 1.0)
    private val diffyServo2 = ServoClass(Bot.opMode.hardwareMap.get(Servo::class.java, Config.diffyServo2), 0.0, 1.0)
    private val intakeClaw = ServoClass(Bot.opMode.hardwareMap.get(Servo::class.java, Config.intakeClaw), 0.0, 0.5)

    // Utility methods for forward and inverse kinematics
    private fun calcSwing(theta1: Double, theta2: Double) = (theta1 + theta2) / 2
    private fun calcTwist(theta1: Double, theta2: Double) = (theta1 - theta2) / 2
    private fun calcTheta1(swing: Double, twist: Double) = swing + twist
    private fun calcTheta2(swing: Double, twist: Double) = swing - twist + 135

    // Utility methods for converting between servo positions and degrees
    private fun degreesToServoPos(degrees: Double) = degrees / 270.0
    private fun servoPosToDegrees(servoPos: Double) = servoPos * 270.0

    /**
     * Goes to a swing and twist position
     * @param swing degrees of swing between 0 and 135
     * @param twist degrees of twist between 0 and 135
     */
    fun goToPosition(swing: Double, twist: Double) {
        val theta1 = degreesToServoPos(calcTheta1(swing, twist))
        val theta2 = degreesToServoPos(calcTheta2(swing, twist))
        diffyServo1.anyPos(theta1)
        diffyServo2.anyPos(theta2)
    }

    /**
     * Moves the servos to the home position (0, 0)
     */
    fun home() {
        goToPosition(0.0, 0.0)
    }

    // Claw servo
    fun openClaw() = intakeClaw.openServo()
    fun closeClaw() = intakeClaw.closeServo()

    // Getters
    val swing: Double
        get() {
            val theta1 = servoPosToDegrees(servo1)
            val theta2 = servoPosToDegrees(servo2)
            return calcSwing(theta1, theta2)
        }

    val twist: Double
        get() {
            val theta1 = servoPosToDegrees(servo1)
            val theta2 = servoPosToDegrees(servo2)
            return calcTwist(theta1, theta2)
        }

    val servo1: Double
        get() = diffyServo1.pos

    val servo2: Double
        get() = diffyServo2.pos

    val claw: Double
        get() = intakeClaw.pos
}