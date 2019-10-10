package com.improving.tagcli;

import com.improving.tagcli.database.EmoteDAO;
import com.improving.tagcli.database.ItemDAO;
import com.improving.tagcli.models.Emote;
import com.improving.tagcli.models.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class TagCliApplication implements CommandLineRunner {
	private final EmoteDAO emoteDAO;
	private final ItemDAO itemDAO;

	public TagCliApplication(EmoteDAO emoteDAO, ItemDAO itemDAO) {
		this.emoteDAO = emoteDAO;
		this.itemDAO = itemDAO;
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
				System.out.println("Do you want to add an (1) Emote or (2) Item?");
				input = scanner.nextLine();
				if (input.equals("1")) {
					System.out.println("Enter Emote Prompt: ");
					prompt = scanner.nextLine();
					System.out.println("Enter Emote Text: ");
					text = scanner.nextLine();
					Emote emote = new Emote(prompt, text);
					emoteDAO.insertEmote(emote);
				} else if (input.equals("2")) {
					System.out.println("Enter Item Name: ");
					String n = scanner.nextLine();
					System.out.println("Enter Item Weight: ");
					int w = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter Item Value: ");
					int v = scanner.nextInt();
					scanner.nextLine();
					System.out.println("Enter true if item is burnable, false if it is not: ");
					boolean b = scanner.nextBoolean();
					scanner.nextLine();
					System.out.println("Enter some text about the Item's MagicQuality: ");
					String mc = scanner.nextLine();
					Item item = new Item(n, w, v, b , mc);
					itemDAO.insertItem(item);

				} else {
					System.out.println("huh?");
				}
			}

			else if (input.equals("2")) {
				System.out.println("Do you want to read an (1) Emote or (2) Item?");
				input = scanner.nextLine();
				if (input.equals("1")) {
					System.out.println("Which emote do you want?");
					input = scanner.nextLine();
					Emote emote = emoteDAO.findEmoteByName(input);
					System.out.println("Here ya go: " + emote.getText());
				} else if (input.equals("2")) {
					System.out.println("Which item do you want?");
					input = scanner.nextLine();
					Item item = itemDAO.findItemByName(input);
					System.out.println("Here ya go: "
							+ "Weight: " + item.getWeight() + ", "
							+ "Value: " + item.getValue() + ", "
							+ "Burnable?: " + item.isBurnable() + ", "
							+ "MagicQuality: " + item.getMagicQuality());
				} else {
						System.out.println("huh?");
					}
				}
			else {
				System.out.println("Please enter valid input.");
			}
		}
	}
}
