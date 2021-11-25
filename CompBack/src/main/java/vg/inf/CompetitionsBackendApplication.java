package vg.inf;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompetitionsBackendApplication {
	public static String EXCEL_FILE_INPUT = "competitions.xlsx";
	public static String EXCEL_FILE_OUTPUT = "competitions.xlsx";

	public static void main(String[] args) throws IOException {
		// EXCEL_FILE = args[0];

		File f = new File(EXCEL_FILE_INPUT);
		if (!f.exists())
			f.createNewFile();
		SpringApplication.run(CompetitionsBackendApplication.class, args);
	}

}
