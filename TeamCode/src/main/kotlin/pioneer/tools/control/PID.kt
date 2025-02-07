package pioneer.tools.control

/**
 * PID controller class
 */
class PID(private val kP: Double, private val kI: Double, private val kD: Double, initialError: Double = 0.0) {
    private var sumError: Double = 0.0
    private var prevError: Double = initialError

    /**
     * Calculate the move value based on the current and target values
     *
     * @param current Current value
     * @param target Target value
     * @param normalizeError Used for dealing with wrapping angles such as -pi to pi
     * @return Move value
     */
    fun calculate(current: Double, target: Double, normalizeError: Boolean = false): Double {
        var error = target - current

        if (normalizeError) {
            error = AngleUtils.normalizeRadians(error)
        }

        val derivative = error - prevError
        sumError += error
        prevError = error

        return kP * error + kI * sumError + kD * derivative
    }
q
    /**
     * Get the previous error value
     * @return Previous error value
     */
    fun getError(): Double {
        return prevError
    }
}