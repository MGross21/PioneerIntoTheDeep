package org.firstinspires.ftc.teamcode.Hardware

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import org.firstinspires.ftc.teamcode.Bot
import org.firstinspires.ftc.teamcode.Config
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.sign
import kotlin.math.sin

class MecanumBase {
    private val RF: DcMotorEx = Bot.opMode.hardwareMap.get(DcMotorEx::class.java, Config.motorRF)
    private val LF: DcMotorEx = Bot.opMode.hardwareMap.get(DcMotorEx::class.java, Config.motorLF).apply { direction = DcMotor.Direction.REVERSE }
    private val RB: DcMotorEx = Bot.opMode.hardwareMap.get(DcMotorEx::class.java, Config.motorRB)
    private val LB: DcMotorEx = Bot.opMode.hardwareMap.get(DcMotorEx::class.java, Config.motorLB).apply { direction = DcMotor.Direction.REVERSE }
    private var northMode = false

    init {
        listOf(RF, LF, RB, LB).forEach {
            it.mode = DcMotor.RunMode.RUN_USING_ENCODER
            it.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            it.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        }
    }

    fun setZeroPowerBehavior(behavior: DcMotor.ZeroPowerBehavior) {
        listOf(RF, LF, RB, LB).forEach { it.zeroPowerBehavior = behavior }
    }

    fun move(x: Double, y: Double, turn: Double, speed: Double) {
        val (newX, newY) = if (northMode) {
            val theta = -Bot.imu.radians
            x * cos(theta) - y * sin(theta) to x * sin(theta) + y * cos(theta)
        } else {
            x to y
        }

        val denominator = max(abs(newY) + abs(newX) + abs(turn), 1.0)
        val powerRF = (newY - newX - turn) / denominator
        val powerLF = (newY + newX + turn) / denominator
        val powerRB = (newY + newX - turn) / denominator
        val powerLB = (newY - newX + turn) / denominator

        RF.velocity = powerRF * Config.maxDriveTicksPerSecond * speed + (sign(powerRF) * Config.fRF * Config.maxDriveTicksPerSecond)
        LF.velocity = powerLF * Config.maxDriveTicksPerSecond * speed + (sign(powerLF) * Config.fLF * Config.maxDriveTicksPerSecond)
        RB.velocity = powerRB * Config.maxDriveTicksPerSecond * speed + (sign(powerRB) * Config.fRB * Config.maxDriveTicksPerSecond)
        LB.velocity = powerLB * Config.maxDriveTicksPerSecond * speed + (sign(powerLB) * Config.fLB * Config.maxDriveTicksPerSecond)
    }

    fun stop() {
        listOf(RF, LF, RB, LB).forEach { it.velocity = 0.0 }
    }

    fun setNorthMode(northMode: Boolean) {
        this.northMode = northMode
    }

    fun getEncoders(): DoubleArray {
        return doubleArrayOf(
            RF.currentPosition.toDouble(),
            LF.currentPosition.toDouble(),
            RB.currentPosition.toDouble(),
            LB.currentPosition.toDouble()
        )
    }
}