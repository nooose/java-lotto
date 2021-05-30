package lotto;

import static lotto.domain.LottoRank.*;

import java.util.Map;

import lotto.domain.LottoBuyingRequest;
import lotto.domain.LottoRank;
import lotto.domain.LottoReport;
import lotto.domain.UserLotto;

public class OutputView {

	public static final String LINE_SEPARATOR = "\r\n";
	public static final String LOTTO_TICKET_COUNT_FORMAT = "수동으로 %d개, 자동으로 %d개를 구매했습니다.";
	public static final String EARNING_RESULT_MESSAGE_FORMAT = "총 수익률은 %s입니다.(기준이 1이기 때문에 결과적으로 %s라는 의미임)";
	public static final String STATISTIC_HEADER_MESSAGE =
		LINE_SEPARATOR + "당첨 통계" + LINE_SEPARATOR + "---------";

	private static final String MESSAGE_FORMAT = "%d개 일치 (%s원)- %d개";
	private static final String SECOND_MESSAGE_FORMAT = "%d개 일치, 보너스 볼 일치 (%s원)- %d개";

	private final UserInterface userInterface;

	public OutputView(UserInterface userInterface) {
		this.userInterface = userInterface;
	}

	public void userLottoCountView(LottoBuyingRequest lottoBuyingRequest) {
		userInterface.send(userLottoCountMessage(lottoBuyingRequest));
	}

	String userLottoCountMessage(LottoBuyingRequest lottoBuyingRequest) {
		return String.format(LOTTO_TICKET_COUNT_FORMAT, lottoBuyingRequest.manualLottoCount(),
			lottoBuyingRequest.autoLottoCount());
	}

	public void userLottoNumberListView(UserLotto userLotto) {
		userInterface.send(userLottoNumberListMessage(userLotto));
	}

	public String userLottoNumberListMessage(UserLotto userLotto) {
		return userLotto.toString();
	}

	public void lottoReportView(LottoReport lottoReport) {
		userInterface.send(lottoReportMessage(lottoReport));
	}

	private String lottoReportMessage(LottoReport lottoReport) {
		return STATISTIC_HEADER_MESSAGE + LINE_SEPARATOR
			+ buildLottoRankMessage(lottoReport.lottoRankMap()) + LINE_SEPARATOR
			+ String.format(EARNING_RESULT_MESSAGE_FORMAT, lottoReport.earningRatio(),
			lottoReport.earningResultMessage());
	}

	private String buildLottoRankMessage(Map<LottoRank, Integer> lottoRankMap) {
		return formatMessage(FIFTH, lottoRankMap.getOrDefault(FIFTH, 0)) + LINE_SEPARATOR
			+ formatMessage(FOURTH, lottoRankMap.getOrDefault(FOURTH, 0)) + LINE_SEPARATOR
			+ formatMessage(THIRD, lottoRankMap.getOrDefault(THIRD, 0)) + LINE_SEPARATOR
			+ formatMessage(SECOND, lottoRankMap.getOrDefault(SECOND, 0)) + LINE_SEPARATOR
			+ formatMessage(FIRST, lottoRankMap.getOrDefault(FIRST, 0));
	}

	private String formatMessage(LottoRank lottoRank, Integer lottoCount) {
		if (lottoRank.equals(SECOND)) {
			return String.format(SECOND_MESSAGE_FORMAT, lottoRank.matchCount(), lottoRank.money(), lottoCount);
		}

		return String.format(MESSAGE_FORMAT, lottoRank.matchCount(), lottoRank.money(), lottoCount);
	}

}
