package smartHome

class SmartHome(
    private val bulb: Bulb,
    private val switch: Switch,
    private val warningService: BurnoutWarningService,
) {
    fun run() {
        if (switch.isOn()) {
            warningService.incrementSwitchCount()
            if (warningService.isWarning()) {
                println(warningService.sendNotification())
            }
            bulb.turnOn()
        } else {
            bulb.turnOff()
        }
    }
}