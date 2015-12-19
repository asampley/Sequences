package test;

import java.util.Scanner;

import calculators.PrimeSequence;

public class IsPrime {
	public static void main(String[] args) {
		PrimeSequence sequence = new PrimeSequence();
		Scanner in = new Scanner(System.in);

		while (true) {
			System.out.print("Enter the number to check if it is prime: ");

			String input = in.nextLine();

			if (input.toLowerCase().equals("q")) {
				break;
			}

			Long possiblePrime;
			try {
				possiblePrime = Long.parseLong(input);
			} catch (NumberFormatException e) {
				System.err.println("Invalid Input");
				continue;
			}

			System.out.println(sequence.isPrime(possiblePrime));
		}
	}
}
