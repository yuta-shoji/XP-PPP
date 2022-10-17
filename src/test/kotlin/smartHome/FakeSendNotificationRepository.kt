package smartHome

class FakeSendNotificationRepository: SendNotificationRepository {
    override var wasSent: Boolean = false

    override fun sendNotification(message: SendMessage): SendMessage {
        wasSent = true
        return message
    }
}