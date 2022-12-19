package smartHome

interface Switch {
    var switchStatus: Boolean
    fun isOn(): Boolean
}