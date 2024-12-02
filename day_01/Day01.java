import java.io.*;
import java.util.*;
import java.lang.Math;

public class Day01 {
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				String[] parts = line.trim().split("\\s+");

				list1.add(Integer.parseInt(parts[0]));
				list2.add(Integer.parseInt(parts[1]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		Collections.sort(list1);
		Collections.sort(list2);

		long ans = 0;
		for (int i = 0; i < list1.size(); ++i) {
			ans += Math.abs(list1.get(i) - list2.get(i));
		}

		System.out.println(ans);
	}
}
