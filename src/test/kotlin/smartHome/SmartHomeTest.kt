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
        //スマホを作る
        val smartPhone = FakeSmartPhoneRepository()
        //スマートホームを作る
        smartHome = SmartHome(spyBulb, alwaysOnSwitch, burnoutWarningService, smartPhone)


        //スイッチを入れる
        smartHome.run()


        //評価する
        assertTrue(spyBulb.turnOn_wasCalled)
    }

    @Test
    fun `スイッチOFFの時は、バルブがオフになる`() {
        val spyBulb = SpyBulb()
        val alwaysOffSwitch = AlwaysOffSwitch()
        val smartPhone = FakeSmartPhoneRepository()
        smartHome = SmartHome(spyBulb, alwaysOffSwitch, burnoutWarningService, smartPhone)


        smartHome.run()


        assertTrue(spyBulb.turnOff_wasCalld)
    }

    @Test
    fun `スイッチON⇆OFFを５回繰り返したら、スマートホームの電球切れをスマホ通知で警告する`() {
        val spyBulb = SpyBulb()
        val toggleSwitch = ToggleSwitch()
        val smartPhone = FakeSmartPhoneRepository()
        smartHome = SmartHome(spyBulb, toggleSwitch, burnoutWarningService, smartPhone)

        for (num in 1..8) {
            smartHome.run()
        }
        assertFalse(burnoutWarningService.isWarning())

        smartHome.run()
        assertTrue(burnoutWarningService.isWarning())
    }

}