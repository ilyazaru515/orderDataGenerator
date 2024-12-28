package postgre.goal.dataGenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import postgre.goal.dataGenerator.service.SqlInsertsGenerator;

import java.io.IOException;

@SpringBootTest
class DataGeneratorApplicationTests {
	@Autowired
	SqlInsertsGenerator sqlInsertsGenerator;

	@Test
	void generateInserts() throws IOException {
		sqlInsertsGenerator.generateSqlInserts();
	}

}
