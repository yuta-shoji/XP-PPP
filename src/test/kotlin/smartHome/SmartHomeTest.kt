package smartHome

import org.junit.jupiter.api.Test

class SmartHomeTest {

    // STEP1
    // テスト：Tono
    // 実装：Misaki

    @Test
    fun `スイッチONの状態の時は、バルブがONになる`() {
        // ARRANGE
        // バルブの状態を監視するスパイを作る
        // テストが実施できるように、常にONになる スイッチのスタブを作る


        // ACTION：スイッチを入れる


        // ASSERT
        // バルブONする。OFFはしない
    }

    @Test
    fun `スイッチOFFの時は、バルブがOFFになる`() {
        // ARRANGE
        // バルブの状態を監視するスパイを作る（上と同じ）
        // テストが実施できるように、常にOFFになる スイッチのスタブを作る


        // ACTION：スイッチを入れる


        // ASSERT
        // バルブONしないで、OFFだけする
    }
}