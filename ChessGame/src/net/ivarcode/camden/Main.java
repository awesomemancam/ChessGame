package net.ivarcode.camden;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		System.out.println("Welcome to Cam's Chess Engine.");
		System.out.println("Type 'HELP' or 'h' for help.");

		while (true) {
			System.out.print("CHESS> ");
			String input = scanner.nextLine();
			if (input == "\n") {
				System.out.print("ayy");
				GameTypeOne game = new GameTypeOne();
			}
		}

	}

}
