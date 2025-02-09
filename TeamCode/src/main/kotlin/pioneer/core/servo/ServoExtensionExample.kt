package pioneer.core.servo

class ServoExtensionExample(minPos: Double, maxPos: Double, hardwareMap: HardwareMap, servoName: String) : Servo(minPos, maxPos, hardwareMap, servoName) {

    // Custom states for the servo (applies to position-controlled mode)
    enum class State(val position: Double) {
        OPEN(1.0),
        CLOSE(0.0),
        HALF(0.5),
        CUSTOM_OTHER(0.25)
    }

    // Override setPosition to use custom states for position-controlled servos
    override fun setPosition(state: State) {
        super.setPosition(state.position)
    }
}
