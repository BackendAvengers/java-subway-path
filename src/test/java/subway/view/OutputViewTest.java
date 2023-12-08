package subway.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import subway.dto.RouteSearchResultDto;
import subway.stub.StubConsoleWriter;

class OutputViewTest {
    private final StubConsoleWriter writer = new StubConsoleWriter();
    private final OutputView outputView = new OutputView(writer);

    @Test
    void 경로검색결과를_올바르게_출력한다() {
        //given
        RouteSearchResultDto routeSearchResultDto = new RouteSearchResultDto(List.of("교대역", "강남역", "양재역"), 4, 11);
        //when
        outputView.outputRouteSearchResult(routeSearchResultDto);
        //then
        assertThat(writer.getOutput()).isEqualTo("""
                [INFO] ---
                [INFO] 총 거리: 4km
                [INFO] 총 소요 시간: 11분
                [INFO] ---
                [INFO] 교대역
                [INFO] 강남역
                [INFO] 양재역
                """);
    }
}