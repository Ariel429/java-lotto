package domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoMachineTest {

    @Test
    void 돈을_입력받으면_구매한_로또_수를_리턴한다() {
        //given
        int givenMoney = 14000;
        LottoMachine lottoMachine = new LottoMachine();
        //when
        int ticketNumber = lottoMachine.getLottoTicktCount(givenMoney);
        //than
        assertThat(ticketNumber).isEqualTo(14);
    }

    @Test
    void _1부터_45사이의_숫자중_중복되지_않는_7개를_뽑아서_오름차순으로_리턴한다() {
        //given
        LottoMachine lottoMachine = new LottoMachine();
        //when
        List<Integer> lottoNumbers = lottoMachine.createRandomNumber();
        //than
        assertThat(lottoNumbers.size()).isEqualTo(6);
        assertThat(lottoNumbers.stream().distinct().count()).isEqualTo(6);

    }

    @Test
    void 부여받은_티켓_개수대로_로또_만들어서_리턴한다() {
        //given
        LottoMachine lottoMachine = new LottoMachine();
        int money = 1000;
        //when
        List<Integer> lottoNumbers = lottoMachine.createRandomNumber();
        List<Lotto> lottos = lottoMachine.buyLottos(money);
        //than
        assertThat(lottos.size()).isEqualTo(1);

    }

}