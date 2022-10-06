package smartHome

interface warningService {
    fun isWarning(): Boolean
    fun inclementTurnOnCount()
}