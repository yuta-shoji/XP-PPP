package smartHome

class ToggleSwitch(): Switch {
    override var switchStatus: Boolean = false
    override fun isOn(): Boolean {
        switchStatus = !switchStatus
        return switchStatus
    }
}