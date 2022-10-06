package smartHome

class DefaultWarningService(private val bulbBurnoutWarningRepository: BulbBurnoutWarningRepository) : warningService {
    override fun isWarning(): Boolean {
        return bulbBurnoutWarningRepository.turnOnCount > 4
    }

    override fun inclementTurnOnCount() {
        bulbBurnoutWarningRepository.turnOnCount += 1
    }
}
