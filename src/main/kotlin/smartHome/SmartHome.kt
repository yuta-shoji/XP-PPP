package smartHome

class SmartHome(
    private val bulb: Bulb,
    private val switch: Switch,
    private val warningService: BurnoutWarningService,
) {
    fun run() {
        if (switch.isOn()) {
            bulb.turnOn()
            warningService.incrementSwitchCount()
            if (warningService.isWarning()) {
                println(warningService.sendNotification())
            }
        } else {
            bulb.turnOff()
            warningService.deleteNotification()
        }
    }
}