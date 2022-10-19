package smartHome

class SendNotificationService(private val sendNotificationRepository: SendNotificationRepository) {
    fun sendNotification(message: SendMessage) {
        sendNotificationRepository.wasSent = true
        println(message)
    }

    fun didSend(): Boolean {
        return sendNotificationRepository.wasSent
    }
}