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
    private final List<String> mansSurnames = new ArrayList<>();
    private final List<String> mansPatronymics = new ArrayList<>();
    private final List<String> womenNames = new ArrayList<>();
    private final List<String> womenSurnames = new ArrayList<>();
    private final List<String> womenPatronymics = new ArrayList<>();
    private final List<String> goods = new ArrayList<>();

    @PostConstruct
    void readFiles() throws IOException {
        mansNames.addAll(fileReader.readFile("/files/MansNames.txt"));
        mansSurnames.addAll(fileReader.readFile("/files/MansSurnames.txt"));
        mansPatronymics.addAll(fileReader.readFile("/files/MansPatronymics.txt"));
        womenNames.addAll(fileReader.readFile("/files/WomenNames.txt"));
        womenSurnames.addAll(fileReader.readFile("/files/WomenSurnames.txt"));
        womenPatronymics.addAll(fileReader.readFile("/files/WomenPatronymics.txt"));
        goods.addAll(fileReader.readFile("files/goods.txt"));
    }
}
