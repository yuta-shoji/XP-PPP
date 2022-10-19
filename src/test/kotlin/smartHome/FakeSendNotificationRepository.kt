package smartHome

class FakeSendNotificationRepository: SendNotificationRepository {
    override var wasSent: Boolean = false
}