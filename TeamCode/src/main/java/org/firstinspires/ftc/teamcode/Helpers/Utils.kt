package org.firstinspires.ftc.teamcode.Helpers

import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.Bot
import kotlin.math.max
import kotlin.math.min
import kotlin.math.round
import kotlin.random.Random

object Utils {
/**
 * Turns float to boolean where <0.5 is false and >0.5 is true.
 * Used with gamepad triggers that return float value.
 *
 * @param floatInput range [0,1]
 * @return boolean
 */
@JvmStatic
fun floatToBool(floatInput: Float): Boolean {
    return floatInput > 0.5
}

/**
 * Waits for a specified amount of time using ElapsedTime
 *
 * @param time float time to wait in seconds
 */
@JvmStatic
fun sleep(time: Double) {
    val timer = ElapsedTime()
    timer.reset()
    while (timer.seconds() < time && Bot.opMode.opModeIsActive()) {
        // Wait for seconds
    }
}

/**
 * Increments a double by value and makes sure it doesn't go over limit
 *
 * @param number number to increment
 * @param value value to increment by
 * @param limit maximum value of number
 * @return new number
 */
@JvmStatic
fun increment(number: Double, value: Double, limit: Double): Double {
    var num = number + value
    num = round(num * 1000) / 1000 // Account for floating point error
    return min(limit, num)
}

/**
 * Decrements a double by value and makes sure it doesn't go under limit
 *
 * @param number number to decrement
 * @param value value to decrement by
 * @param limit minimum value of number
 * @return new number
 */
@JvmStatic
fun decrement(number: Double, value: Double, limit: Double): Double {
    var num = number - value
    num = round(num * 1000) / 1000 // Account for floating point error
    return max(limit, num)
}

/**
 * Generates a random integer from 0 to maxRange
 *
 * @param maxRange range of possibilities from 0 to n (non-inclusive)
 * @return a random number
 */
@JvmStatic
fun randNum(maxRange: Int): Int {
    return Random.nextInt(maxRange)
}

@JvmStatic
fun factorial(n: Int): Long {
    return if (n <= 1) 1 else n * factorial(n - 1)
}

@JvmStatic
fun choose(n: Int, k: Int): Long {
    return factorial(n) / (factorial(k) * factorial(n - k))
}

@JvmStatic
fun pathToXY(path: Array<DoubleArray>): Array<DoubleArray> {
    val x = DoubleArray(path.size)
    val y = DoubleArray(path.size)
    for (i in path.indices) {
        x[i] = path[i][0]
        y[i] = path[i][1]
    }
    return arrayOf(x, y)
}

// Linear interpolation
@JvmStatic
fun lerp(p1: Double, p2: Double, t: Double): Double {
    return p1 + (p2 - p1) * t
}

@JvmStatic
fun clamp(value: Double, min: Double, max: Double): Double {
    return max(min, min(max, value))
}
}