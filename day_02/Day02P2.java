import java.io.*;
import java.util.*;

public class Day02P2 {
	private static boolean foo(List<Integer> nums, int start, boolean skip) {
		boolean inc = true, dec = true, inc_skip = skip, dec_skip = skip;
		int inc_last = start-1, dec_last = start-1;

		for (int i = start; i < nums.size(); ++i) {
			if (inc) {
				int diff = nums.get(i) - nums.get(inc_last);
				if (diff < 1 || diff > 3) {
					if (inc_skip) {
						inc_skip = false;
					} else {
						inc = false;
					}
				} else {
					inc_last = i;
				}
			}
			
			if (dec) {
				int diff = nums.get(dec_last) - nums.get(i);
				if (diff < 1 || diff > 3) {
					if (dec_skip) {
						dec_skip = false;
					} else {
						dec = false;
					}
				} else {
					dec_last = i;
				}
			}

			if (!inc && !dec) {
				break;
			}
		}
		return inc || dec;
	}
	public static void main(String[] args) {
		int ans = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader("data.txt"))) {
			String line;
			
			while ((line = reader.readLine()) != null) {
				String[] parts = line.trim().split("\\s+");
				List<Integer> nums = Arrays.stream(parts)
					.map(Integer::parseInt)
					.toList();
				
				if (foo(nums, 1, true) || foo(nums, 2, false)) {
					ans++;
				}
			}

			System.out.println(ans);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
