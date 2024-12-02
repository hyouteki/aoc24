import java.io.*;
import java.util.*;

public class Day01P2 {
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

		HashMap<Integer, Integer> map1 = new HashMap<>();
		HashMap<Integer, Integer> map2 = new HashMap<>();

		for (Integer num: list1) {
			map1.put(num, map1.getOrDefault(num, 0)+1);
		}

		for (Integer num: list2) {
			map2.put(num, map2.getOrDefault(num, 0)+1);
		}

		long ans = 0;

		for (Integer num: list1) {
			ans += map1.getOrDefault(num, 0) * num * map2.getOrDefault(num, 0);
		}

		System.out.println(ans);		
	}
}
