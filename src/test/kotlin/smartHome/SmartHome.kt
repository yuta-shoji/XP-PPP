package smartHome

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SmartHomeTest {
    @Test
    fun `スイッチONの状態の時は、バルブがONになる`() {
        // ARRANGE
        // バルブ(光る・光らない)の ON/OFF操作を のぞき見するSpy
        val spyBulb = SpyBulb()

        // テストが実施できるように、常にONになる スイッチのスタブを作る
        val stubSwitch = AlwaysOnSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // ACTION:スイッチを入れる
        SmartHome(spyBulb, stubSwitch, warningService).run()

        // ASSERT
        // バルブONする。OFFはしない
        assertTrue(spyBulb.turnOnWasCalled)
        assertFalse(spyBulb.turnOffWasCalled)
    }

    @Test
    fun `スイッチOFFの時は、バルブがOFFになる`() {
        // ARRANGE
        // バルブの状態を監視するスパイを作る(上と同じ)
        val spyBulb = SpyBulb()

        // テストが実施できるように、常にOFFになる スイッチのスタブを作る
        val stubSwitch = AlwaysOffSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // ACTION:スイッチを入れる
        SmartHome(spyBulb, stubSwitch, warningService).run()

        // ASSERT
        // バルブONしないで、OFFだけする
        assertFalse(spyBulb.turnOnWasCalled)
        assertTrue(spyBulb.turnOffWasCalled)
    }

    @Test
    fun `スイッチONを５回実行したら、電球切れ警告発動`() {
        // ARRANGE
        // バルブの状態を監視するスパイを作る(上と同じ)
        val spyBulb = SpyBulb()

        // テストが実施できるように、常にOFFになる スイッチのスタブを作る
        val stubSwitch = AlwaysOnSwitch()

        // 電球切れ警告用のRepositoryを生成する
        val bulbBurnoutWarningRepository = BulbBurnoutWarningRepository()

        // 電球切れ警告用のServiceを生成する
        val warningService = DefaultWarningService(bulbBurnoutWarningRepository)

        // スマートホームを初期化する
        val smartHome = SmartHome(spyBulb, stubSwitch, warningService)

        // ACTION:
        for (num in 1..4) {
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
}