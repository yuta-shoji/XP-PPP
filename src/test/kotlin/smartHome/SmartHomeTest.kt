package smartHome

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class SmartHomeTest {
    private lateinit var smartHome: SmartHome
    private lateinit var burnoutWarningRepository: BurnoutWarningRepository
    private lateinit var burnoutWarningService: DefaultBurnoutWarningService

    @BeforeEach
    fun setup() {
        burnoutWarningRepository = BurnoutWarningRepository()
        burnoutWarningService = DefaultBurnoutWarningService(burnoutWarningRepository)
    }

    @Test
    fun `スイッチONの時は、バルブがONになる`() {
        //バルブを作る
        val spyBulb = SpyBulb()
        //常時Onのスイッチを作る
        val alwaysOnSwitch = AlwaysOnSwitch()
        //スマートホームを作る
        smartHome = SmartHome(spyBulb, alwaysOnSwitch, burnoutWarningService)


        //スイッチを入れる
        smartHome.run()


        //評価する
        assertTrue(spyBulb.turnOn_wasCalled)
    }

    @Test
    fun `スイッチOFFの時は、バルブがオフになる`() {
        val spyBulb = SpyBulb()
        val alwaysOffSwitch = AlwaysOffSwitch()
        smartHome = SmartHome(spyBulb, alwaysOffSwitch, burnoutWarningService)


        smartHome.run()


        assertTrue(spyBulb.turnOff_wasCalld)
    }

    @Test
    fun `スイッチON⇆OFFを５回繰り返したら、スマートホームの電球切れを警告する`() {
        val spyBulb = SpyBulb()
        val toggleSwitch = ToggleSwitch()
        smartHome = SmartHome(spyBulb, toggleSwitch, burnoutWarningService)

        for (num in 1..8) {
            smartHome.run()
        }
        assertFalse(burnoutWarningService.isWarning())

        smartHome.run()
        assertTrue(burnoutWarningService.isWarning())
    }
}