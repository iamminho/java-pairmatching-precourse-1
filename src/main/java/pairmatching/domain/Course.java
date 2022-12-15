package pairmatching.domain;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Course {
    BACKEND("백엔드", "/Users/minho/java-pairmatching-precourse-1/src/main/resources/backend-crew.md"),
    FRONTEND("프론트엔드", "/Users/minho/java-pairmatching-precourse-1/src/main/resources/backend-crew.md");

    private String course;
    private String address;

    Course(String course, String address) {
        this.course = course;
        this.address = address;
    }

    public static Course findCourse(Selections selections) {
        String inputCourse = selections.getCourse();
        Course course = valueOfName(inputCourse);
        return course;
    }

    private static final Map<String, Course> BY_COURSE =
            Stream.of(values()).collect(Collectors.toMap(Course::getCourse, Function.identity()));

    private static Course valueOfName(String course) {
        return BY_COURSE.get(course);
    }

    public String getCourse() {
        return this.course;
    }

    public String getAddress() {
        return this.address;
    }
}


