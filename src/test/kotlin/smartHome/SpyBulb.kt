package smartHome

class SpyBulb : Bulb {
    var turnOnWasCalled: Boolean = false
    var turnOffWasCalled: Boolean = false

    override fun turnOn() {
        turnOnWasCalled = true
    }

    override fun turnOff() {
        turnOffWasCalled = true
    }

}