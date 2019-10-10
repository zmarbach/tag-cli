package com.improving.tagcli;

import com.improving.tagcli.database.DatabaseClient;
import com.improving.tagcli.models.Emote;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {

	private final DatabaseClient databaseClient;

	public TagCliApplication(DatabaseClient databaseClient) {

		this.databaseClient = databaseClient;
	}

	public static void main(String[] args) {

		SpringApplication.run(TagCliApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		String input;
		String prompt;
		String text;

		boolean loop = true;
		while (loop) {
			System.out.println("Do you want to (1) Add something or (2) Read something?");
			input = scanner.nextLine();
			if (input.equals("1")) {
				System.out.println("Do you want to add an (1) Emote or (2) Weapon?");
				input = scanner.nextLine();
				if (input.equals("1")) {
					System.out.println("Enter Emote Prompt: ");
					prompt = scanner.nextLine();
					System.out.println("Enter Emote Text: ");
					text = scanner.nextLine();
					Emote emote = new Emote(prompt, text);
					databaseClient.insertEmoteIntoTable(emote);
				} else if (input.equals("2")) {
					System.out.println("Not implemented yet");
				} else {
					System.out.println("huh?");
				}
			} else if (input.equals("2")) {
				System.out.println("Not implemented yet");
			} else {
				System.out.println("huh?");
			}
		}
	}
}
