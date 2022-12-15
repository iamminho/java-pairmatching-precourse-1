package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MatchingMachine {
    private final int START_INDEX = 0;
    private final int EXTENT_BEYOND = 2;
    private final int ODD_EXTENT_BEYOND = 3;
    private CrewsGenerator crewsGenerator;

    public MatchingMachine() {
        this.crewsGenerator = new CrewsGenerator();
    }

    public LinkedHashMap<String, List<String>> match(Selections selections) throws IOException {
        List<String> crews = getCrews(selections);
        return createCrewMap(shuffleCrews(crews));
    }

    private List<String> getCrews(Selections selections) throws IOException {
        return crewsGenerator.generate(selections);
    }

    private List<String> shuffleCrews(List<String> crews) {
        return Randoms.shuffle(crews);
    }

    private LinkedHashMap<String, List<String>> createCrewMap(List<String> crews) {
        List<List<String>> groupedCrews = groupCrews(crews);
        return pairCrews(groupedCrews);
    }

    private List<List<String>> groupCrews(List<String> crews) {
        List<List<String>> result = new ArrayList<>();
        for (int i = START_INDEX; i < crews.size(); i += EXTENT_BEYOND) {
            if (i == crews.size() - ODD_EXTENT_BEYOND) {
                result.add(crews.subList(i, i + ODD_EXTENT_BEYOND));
                break;
            }
            result.add(crews.subList(i, i + EXTENT_BEYOND));
        }
        return result;
    }

    private LinkedHashMap<String, List<String>> pairCrews(List<List<String>> groupedCrews) {
        LinkedHashMap<String, List<String>> map = new LinkedHashMap<>();
        groupedCrews.stream()
                .forEach(crews -> inputCrews(map, crews));
        return map;
    }

    private void inputCrews(LinkedHashMap<String, List<String>> map, List<String> crews) {
        crews.stream()
                .forEach(crew -> map.put(crew, crews));
    }
}
