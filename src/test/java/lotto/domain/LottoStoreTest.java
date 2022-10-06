package lotto.domain;

import lotto.dto.LottoRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStoreTest {

    @DisplayName("로또를 수동과 자동으로 구매할 수 있다.")
    @Test
    void buy() {
        LottoStore lottoStore = new LottoStore();
        LottoRequestDto request = new LottoRequestDto(5000, List.of("1, 2, 3, 4, 5, 6"));

        Lottos lottos = lottoStore.buy(request);

        List<LottoNumber> lottoNumbers = List.of(LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6));
        assertThat(lottos.values()).contains(new ManualLotto(lottoNumbers));
        assertThat(lottos.quantity()).isEqualTo(5);
    }
}