package pairmatching.domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrewsGenerator {
    public List<String> generate(Selections selections) throws IOException {
        Course course = Course.findCourse(selections);
        return readFile(course.getAddress());
    }

    private List<String> readFile(String address) throws IOException {
        List<String> crews = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(address));
        String line;
        while ((line = reader.readLine()) != null) {
            crews.add(line);
        }
        reader.close();
        return crews;
    }
}
