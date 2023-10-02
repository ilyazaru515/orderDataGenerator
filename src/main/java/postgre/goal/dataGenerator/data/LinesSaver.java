package postgre.goal.dataGenerator.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class LinesSaver {
    private final FileReader fileReader;
    private final List<String> mansNames = new ArrayList<>();
    private final List<String> womansNames = new ArrayList<>();
    private final List<String> mails = new ArrayList<>();
    private final List<String> cities = new ArrayList<>();
    private final List<String> streets = new ArrayList<>();
    private final List<String> individualBusinessmans = new ArrayList<>();
    private final List<String> schedules = new ArrayList<>();

    @PostConstruct
    void readFiles() throws IOException {
        mansNames.addAll(fileReader.readFile("/files/MansNames.txt"));
        womansNames.addAll(fileReader.readFile("/files/WomansNames.txt"));
        mails.addAll(fileReader.readFile("files/mails.txt"));
        cities.addAll(fileReader.readFile("files/cities.txt"));
        streets.addAll(fileReader.readFile("files/streets.txt"));
        individualBusinessmans.addAll(fileReader.readFile("files/individualBusinessman.txt"));
        schedules.addAll(fileReader.readFile("files/schedules.txt"));
    }
}
