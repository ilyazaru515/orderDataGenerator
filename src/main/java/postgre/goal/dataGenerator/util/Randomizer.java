package postgre.goal.dataGenerator.util;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Random;

@Component
public class Randomizer {
    private final Random random = new Random();

    public int randomInt(int bound) {
        return random.nextInt(bound);
    }

    public String randomString(int length) {
        byte[] bytes = new byte[length];
        for (int i = 0; i< length; i++) {
            bytes[i] = (byte) (randomInt(76) + 47);
        }
        var line =  new String(bytes, StandardCharsets.UTF_8);
        return line.replaceAll(";", "");
    }

    public boolean randomBoolean() {
        return random.nextBoolean();
    }

    public double randomDouble(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }
}
