package pairmatching.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Level {
    LEVEL1("레벨1", Arrays.asList("자동차경주", "로또", "숫자야구게임")),
    LEVEL2("레벨2", Arrays.asList("장바구니", "결제", "지하철노선도")),
    LEVEL3("레벨3", null),
    LEVEL4("레벨4", Arrays.asList("성능개선", "배포")),
    LEVEL5("레벨5", null);

    private String level;
    private List<String> missions;

    Level(String level, List<String> missions) {
        this.level = level;
        this.missions = missions;
    }

    public String getLevel() {
        return this.level;
    }

    public List<String> getMissions() {
        return this.missions;
    }

    private static final Map<String, Level> BY_LEVEL =
            Stream.of(values()).collect(Collectors.toMap(Level::getLevel, Function.identity()));

    public static Level valueOfLevel(String level) {
        return BY_LEVEL.get(level);
    }

    public static boolean isInclude(String level, String mission) {
        List<String> targetMissions = valueOfLevel(level).missions;
        return targetMissions.contains(mission);
    }
}



