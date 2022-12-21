package smartHome

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*

class SmartHomeTest {
    private lateinit var smartHome: SmartHome
    private lateinit var smartPhoneRepository: FakeSmartPhoneRepository
    private lateinit var burnoutWarningService: DefaultBurnoutWarningService

    @BeforeEach
    fun setup() {
        smartPhoneRepository = FakeSmartPhoneRepository()
        burnoutWarningService = DefaultBurnoutWarningService(smartPhoneRepository)
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
    fun `スイッチON⇆OFFを５回繰り返したら、スマートホームの電球切れをスマホ通知で警告する`() {
        val spyBulb = SpyBulb()
        val toggleSwitch = ToggleSwitch()
        smartHome = SmartHome(spyBulb, toggleSwitch, burnoutWarningService)

        for (num in 1..8) {
            smartHome.run()
        }
        assertFalse(smartPhoneRepository.wasSendBulbWillBroken)

        smartHome.run()
        assertTrue(smartPhoneRepository.wasSendBulbWillBroken)
    }

    @Test
    fun `電球切れ通知は一回しか送れなないようにする`() {
        val spyBulb = SpyBulb()
        val toggleSwitch = ToggleSwitch()
        smartHome = SmartHome(spyBulb, toggleSwitch, burnoutWarningService)

        for (num in 1..9) {
            smartHome.run()
        }
        assertTrue(smartPhoneRepository.wasSendBulbWillBroken)

        smartHome.run()
        smartHome.run()
        assertFalse(smartPhoneRepository.wasSendBulbWillBroken)
    }

}