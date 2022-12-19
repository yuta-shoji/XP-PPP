package smartHome

class AlwaysOffSwitch: Switch {
    override var switchStatus: Boolean = false
    override fun isOn(): Boolean {
        return false
    }
}
