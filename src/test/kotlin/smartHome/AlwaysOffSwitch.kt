package smartHome

class AlwaysOffSwitch : Switch {
    override fun isOn(): Boolean {
        return false
    }
}
