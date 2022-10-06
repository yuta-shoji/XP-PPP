package smartHome

class SmartHome(
    private val switchable: Switchable,
    private val switch: Switch,
    private val warningService: DefaultWarningService
) {
    fun run() {
        if (switch.isOn()){
            warningService.inclementTurnOnCount()
            switchable.turnOn()
        } else {
            switchable.turnOff()
        }
    }
}