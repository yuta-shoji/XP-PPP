package smartHome

interface BurnoutWarningService {
    fun isWarning(): Boolean
    fun incrementSwitchCount()
}

class DefaultBurnoutWarningService(private val burnoutWarningRepository: BurnoutWarningRepository): BurnoutWarningService {
    override fun isWarning(): Boolean {
        return (burnoutWarningRepository.switchOnCount >= 5)
    }
    override fun incrementSwitchCount() {
        burnoutWarningRepository.switchOnCount++
    }
}