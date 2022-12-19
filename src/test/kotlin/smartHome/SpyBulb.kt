package smartHome

class SpyBulb: Bulb {
    var turnOn_wasCalled = false

    override fun turnOn() {
        turnOn_wasCalled = true
    }

    var turnOff_wasCalld = false

    override fun turnOff() {
        turnOff_wasCalld = true
    }
}
