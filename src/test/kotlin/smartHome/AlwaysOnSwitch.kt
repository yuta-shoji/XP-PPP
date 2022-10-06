package smartHome

class AlwaysOnSwitch : Switch {
    override fun isOn(): Boolean {
        return true
    }
}