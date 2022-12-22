package smartHome

interface SendNotificationRepository {
    fun sendNotification(): Message
}

class FakeSmartPhoneRepository: SendNotificationRepository {
    var switchOnCount: Int = 0
    var sendMessageCount: Int = 0
    override fun sendNotification(): Message {
        return Message.BULB_WILL_BROKEN
    }
}
