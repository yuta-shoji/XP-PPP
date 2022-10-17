package smartHome

interface SendNotificationRepository {
    var wasSent: Boolean
    fun sendNotification(message: SendMessage): SendMessage
}