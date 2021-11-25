package vg.inf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CompetitionsBackendApplication {
	public static String EXCEL_FILE_INPUT = "competitions.xlsx";
	public static String EXCEL_FILE_OUTPUT = "competitions.xlsx";

	public static void main(String[] args) {

		// EXCEL_FILE = args[0];
		SpringApplication.run(CompetitionsBackendApplication.class, args);
	}

}
