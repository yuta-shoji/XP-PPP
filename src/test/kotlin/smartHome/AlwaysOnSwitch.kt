package smartHome

class AlwaysOnSwitch: Switch {
    override var switchStatus: Boolean = false
    override fun isOn(): Boolean {
        return true
    }
}