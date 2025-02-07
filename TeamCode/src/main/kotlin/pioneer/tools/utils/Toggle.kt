package pioneer.tools.utils

/**
 * A class that toggles a boolean state when a button is pressed.
 */
class Toggle(private var state: Boolean) {
    private var prevState: Boolean = false
    private var justChangedFlag: Boolean = false

    /**
     * Toggles the state if the button is pressed.
     * @param button the button state
     */
    fun toggle(button: Boolean) {
        justChangedFlag = false
        if (button && !prevState) {
            state = !state
            justChangedFlag = true
        }
        prevState = button
    }

    /**
     * Sets the state.
     * @param bool the new state
     */
    fun set(bool: Boolean) {
        if (bool != state) {
            state = bool
            justChangedFlag = true
        } else {
            justChangedFlag = false
        }
    }

    /**
     * Gets the current state.
     * @return the current state
     */
    fun get(): Boolean {
        return state
    }

    /**
     * Checks if the state just changed.
     * @return true if the state just changed, false otherwise
     */
    fun justChanged(): Boolean {
        return justChangedFlag
    }
}