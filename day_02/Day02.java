import java.io.*;
import java.util.*;

public class Day02 {
	public static void main(String[] args) {
		int ans = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.trim().split("\\s+");
				List<Integer> nums = Arrays.stream(parts)
					.map(Integer::parseInt)
					.toList();
				
				boolean inc = nums.get(0) < nums.get(1);
				boolean flag = true;
				for (int i = 1; i < nums.size(); ++i) {
					int diff = (inc? 1: -1) * (nums.get(i) - nums.get(i-1));
					if (diff < 1 || diff > 3) {
						flag = false;
						break;
					}
				}

				if (flag) {
					ans++;
				}
			}

			System.out.println(ans);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
