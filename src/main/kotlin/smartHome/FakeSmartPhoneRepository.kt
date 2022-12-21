package smartHome

interface SendNotificationRepository {
    fun sendNotification(): Message
}

class FakeSmartPhoneRepository: SendNotificationRepository {
    var wasSendBulbWillBroken: Boolean = false
    var switchOnCount: Int = 0
    override fun sendNotification(): Message {
        wasSendBulbWillBroken = true
        return Message.BULB_WILL_BROKEN
    }
}
