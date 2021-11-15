package lab1Algo;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] datSet = new int[1000];
		for (int i = 0; i < 1000; i++) {
			datSet[i] = rand.nextInt(100);
			System.out.print(" " + datSet[i]);
		}
		
	}

}
