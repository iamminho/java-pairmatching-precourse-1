package pairmatching.domain;

import java.util.Arrays;
import java.util.List;

public class Selections {
    private final String COMMAND_FORM_WITHOUT_MISSION = "^[가-힣]+, 레벨[1-5]$";
    private final String COMMAND_FORM = "^[가-힣]+, 레벨[1-5]$, [가-힣]+";
    private final String INVALID_FORM = "[ERROR] [과정, 레벨, 미션]형태로 작성해 주시기 바랍니다.";
    private final String INVALID_PROCESS = "[ERROR] 백엔드 혹은 프론트엔드로만 입력해주시기 바랍니다.";
    private final String INVALID_MISSION = "[ERROR] 해당 레벨에 없는 미션입니다.";
    private final String BACKEND = "백엔드";
    private final String FRONTEND = "프론트엔드";
    private final String SEPARATOR = ", ";
    private final int PROCESS_INDEX = 0;
    private final int LEVEL_INDEX = 1;
    private final int MISSION_INDEX = 2;
    private final int GET_MISSION = 3;
    private String course;
    private String level;
    private String mission;

    public Selections(String command) {
        validateForm(command);
        validateCommand(command);
    }

    private void validateForm(String command) {
        if (command.matches(COMMAND_FORM_WITHOUT_MISSION) && command.matches(COMMAND_FORM)) {
            throw new IllegalArgumentException(INVALID_FORM);
        }
    }

    private void validateCommand(String command) {
        List<String> commands = Arrays.asList(command.split(SEPARATOR));
        validateProcess(commands.get(PROCESS_INDEX));
        String level = commands.get(LEVEL_INDEX);
        this.level = level;
        if (commands.size() == GET_MISSION) {
            String mission = commands.get(MISSION_INDEX);
            validateMission(level, mission);
        }
    }

    private void validateProcess(String process) {
        if (!process.equals(BACKEND) && !process.equals(FRONTEND)) {
            throw new IllegalArgumentException(INVALID_PROCESS);
        }
        this.course = process;
    }

    private void validateMission(String level, String mission) {
        if (!Level.isInclude(level, mission)) {
            throw new IllegalArgumentException(INVALID_MISSION);
        }
        this.mission = mission;
    }

    public String getCourse() {
        return this.course;
    }

    public String getLevel() {
        return this.level;
    }

    public String getMission() {
        return this.mission;
    }
}
