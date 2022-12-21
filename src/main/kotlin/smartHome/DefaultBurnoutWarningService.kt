package smartHome

interface BurnoutWarningService {
    fun isWarning(): Boolean
    fun incrementSwitchCount()
    fun sendNotification(): Message
    fun didSend(): Boolean
}

class DefaultBurnoutWarningService(private val smartPhoneRepository: FakeSmartPhoneRepository): BurnoutWarningService {
    override fun isWarning(): Boolean {
        return (smartPhoneRepository.switchOnCount == 5)
    }

    override fun incrementSwitchCount() {
        smartPhoneRepository.switchOnCount++
    }

    override fun sendNotification(): Message {
        smartPhoneRepository.wasSendBulbWillBroken = true
        return smartPhoneRepository.sendNotification()
    }

    override fun didSend(): Boolean {
        return smartPhoneRepository.wasSendBulbWillBroken
    }

}