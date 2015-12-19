package test;
import java.util.Scanner;

import calculators.Fibonacci;
import calculators.ModFib;
import calculators.PrimeSequence;
import calculators.Sequence;


public class SequenceTester {
	public static final String[] sequenceNames = {"fibonacci", "prime", "modfib"};
	public static final Class[] sequences = {Fibonacci.class, PrimeSequence.class, ModFib.class};

	public static void main(String args[]) throws InstantiationException, IllegalAccessException {
		Scanner in = new Scanner(System.in);

		Class sequenceClass = null;
		String sequenceName = null;
		do {
			System.out.print("Select a sequence: ");
			String input = in.nextLine().toLowerCase();
			for (int i = 0; i < sequenceNames.length; i ++) {
				if (input.equals(sequenceNames[i])) {
					sequenceClass = sequences[i];
					sequenceName = sequenceNames[i];
					break;
				}
			}

			if (sequenceClass == null) {
				System.out.println("Unknown Sequence. Choose from: ");
				for (String name : sequenceNames) {
					System.out.println(name);
				}
			}
		} while (sequenceClass == null);

		Sequence sequence = (Sequence)sequenceClass.newInstance();
		
		while (true) {
			System.out.print("Enter which " + sequenceName + " number or range of numbers you want: ");
			String input = in.nextLine();
			int min = 0;
			int max = 0;
			
			if (input.toLowerCase().equals("q")) {
				System.exit(0);
			}
			
			try {
				min = Integer.parseInt(input);
				max = min;
			} catch (NumberFormatException e) {
				String[] bounds = input.split(":");
				try {
					min = Integer.parseInt(bounds[0]);
					max = Integer.parseInt(bounds[1]);
				} catch (NumberFormatException f) {
					System.out.println("Invalid input. Use one of the following formats:");
					System.out.println("value");
					System.out.println("minVal:maxVal");
					continue;
				}
			}

			long startTime = System.currentTimeMillis();
			for (int i = min; i <= max; i ++) {
				System.out.println(i + ": " + sequence.get(i));
			}
			long endTime = System.currentTimeMillis();

			long time = endTime - startTime;
			if (time < 1000) {
				System.out.println("Time taken: " + time + "ms");
			} else {
				System.out.println("Time taken: " + (time / 1000) + "s");
			}
			
			if (sequence instanceof ModFib) {
				System.out.println("Number of repeating values: " + ((ModFib)sequence).numRepeating());
				System.out.println("Number of non-repeating values: " + ((ModFib)sequence).numNonRepeating());
			}
		}
	}
}
