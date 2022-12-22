package smartHome

class AlwaysOnSwitch: Switch {
    override var switchStatus: Boolean = true
    override fun isOn(): Boolean {
        return true
    }
}