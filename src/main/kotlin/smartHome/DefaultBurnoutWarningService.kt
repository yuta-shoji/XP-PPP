package smartHome

interface BurnoutWarningService {
    fun isWarning(): Boolean
    fun incrementSwitchCount()
    fun sendNotification(): Message
}

class DefaultBurnoutWarningService(private val smartPhoneRepository: FakeSmartPhoneRepository) : BurnoutWarningService {
    override fun isWarning(): Boolean {
        return smartPhoneRepository.switchOnCount == 5
    }

    override fun incrementSwitchCount() {
        smartPhoneRepository.switchOnCount++
    }

    override fun sendNotification(): Message {
        smartPhoneRepository.sendMessageCount++
        return smartPhoneRepository.sendNotification()
    }
}