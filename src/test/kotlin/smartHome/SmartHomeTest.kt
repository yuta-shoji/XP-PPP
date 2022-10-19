package smartHome

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SmartHomeTest {
    private lateinit var spyBulb: SpyBulb

    @BeforeEach
    fun setUp() {
        // バルブ(光る・光らない)の ON/OFF操作を のぞき見するSpy
        spyBulb = SpyBulb()
    }

    @Test
    fun `スイッチONの状態の時は、バルブがONになる`() {
        // ARRANGE
        // テストが実施できるように、常にONになる スイッチのスタブを作る
        val stubSwitch = AlwaysOnSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // 電球切れ予告通知用スマホを生成する
        // 電球切れ予告通知用スマホを生成する
        val sendNotificationRepository = FakeSendNotificationRepository()
        val smartPhone = SendNotificationService(sendNotificationRepository)

        // ACTION:スイッチを入れる
        SmartHome(spyBulb, stubSwitch, warningService, smartPhone).run()

        // ASSERT
        // バルブONする。OFFはしない
        assertTrue(spyBulb.turnOnWasCalled)
        assertFalse(spyBulb.turnOffWasCalled)
    }

    @Test
    fun `スイッチOFFの時は、バルブがOFFになる`() {
        // ARRANGE
        // テストが実施できるように、常にOFFになる スイッチのスタブを作る
        val stubSwitch = AlwaysOffSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // 電球切れ予告通知用スマホを生成する
        // 電球切れ予告通知用スマホを生成する
        val sendNotificationRepository = FakeSendNotificationRepository()
        val smartPhone = SendNotificationService(sendNotificationRepository)

        // ACTION:スイッチを入れる
        SmartHome(spyBulb, stubSwitch, warningService, smartPhone).run()

        // ASSERT
        // バルブONしないで、OFFだけする
        assertFalse(spyBulb.turnOnWasCalled)
        assertTrue(spyBulb.turnOffWasCalled)
    }

    @Test
    fun `スイッチONを５回実行したら、電球切れ警告発動`() {
        // ARRANGE
        // ON/OFF可能なスイッチのスタブを作る
        val stubSwitch = ToggleSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // 電球切れ予告通知用スマホを生成する
        // 電球切れ予告通知用スマホを生成する
        val sendNotificationRepository = FakeSendNotificationRepository()
        val smartPhone = SendNotificationService(sendNotificationRepository)

        // スマートホームを初期化する
        val smartHome = SmartHome(spyBulb, stubSwitch, warningService, smartPhone)

        // ACTION:
        for (num in 1..8) {
            smartHome.run()
        }

        // ASSERT
        // 電球切れ警告が出ていないことを確認
        assertFalse(warningService.isWarning())

        smartHome.run()

        // ASSERT
        // 電球切れ警告が出ていることを確認
        assertTrue(warningService.isWarning())
    }

    @Test
    fun `電球切れ警告が発動したら、スマホ通知する`() {
        // ARRANGE
        // テストが実施できるように、常にOFFになる スイッチのスタブを作る
        val stubSwitch = ToggleSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // 電球切れ予告通知用スマホを生成する
        // 電球切れ予告通知用スマホを生成する
        val sendNotificationRepository = FakeSendNotificationRepository()
        val smartPhone = SendNotificationService(sendNotificationRepository)

        // スマートホームを初期化する
        val smartHome = SmartHome(spyBulb, stubSwitch, warningService, smartPhone)

        // ACTION:
        for (num in 1..8) {
            smartHome.run()
        }

        // スマホ通知がないことを確認
        assertFalse(smartPhone.didSend())

        smartHome.run()

        // スマホ通知きたことを確認
        assertTrue(smartPhone.didSend())
    }

    @Test
    fun `スマホ通知は一回しか送らない`() {
        // ARRANGE
        // テストが実施できるように、常にOFFになる スイッチのスタブを作る
        val stubSwitch = ToggleSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // 電球切れ予告通知用スマホを生成する
        val sendNotificationRepository = FakeSendNotificationRepository()
        val smartPhone = SendNotificationService(sendNotificationRepository)

        // スマートホームを初期化する
        val smartHome = SmartHome(spyBulb, stubSwitch, warningService, smartPhone)

        // ACTION:
        for (num in 1..8) {
            smartHome.run()
        }

        // スマホ通知こないことを確認}
        assertFalse(smartPhone.didSend())

        // スマホ通知きたことを確認
        smartHome.run()
        assertTrue(smartPhone.didSend())

        // スマホ通知こないことを確認
        smartHome.run()
        assertTrue(smartPhone.didSend())
    }
}