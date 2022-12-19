package smartHome

class SmartHome(
    private val bulb: Bulb,
    private val switch: Switch,
    private val warningService: BurnoutWarningService,
    private val smartPhone: SendNotificationRepository,
) {
    fun run() {
        if (switch.isOn()) {
            warningService.incrementSwitchCount()
            bulb.turnOn()
        } else {
            bulb.turnOff()
        }
    }
}