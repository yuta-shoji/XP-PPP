package smartHome

interface SendNotificationRepository {
    fun sendNotification(): Message
    var wasSend: Boolean
}

class FakeSmartPhoneRepository: SendNotificationRepository {
    override fun sendNotification(): Message {
        wasSend = true
        return Message.BULB_WILL_BROKEN
    }

    override var wasSend: Boolean = false
}
