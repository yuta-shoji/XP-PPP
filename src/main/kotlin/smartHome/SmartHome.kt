package smartHome

class SmartHome(
    private val bulb: Bulb,
    private val switch: Switch,
    private val warningService: DefaultWarningService,
    private val smartPhone: SendNotificationService,
) {
    fun run() {
        if (switch.isOn()) {
            warningService.inclementTurnOnCount()
            if (warningService.isWarning() && !smartPhone.didSend()) {
                smartPhone.sendNotification(SendMessage.BULB_WILL_BROKEN)
            }
            bulb.turnOn()
        } else {
            bulb.turnOff()
        }
    }
}