#include <iostream>
#include <fstream>
#include <string>
#include <regex>
#include <unordered_map>
#include <unordered_set>

int main() {
	std::ifstream file("data.txt");
	if (!file) {
		return 1;
	}
	
	std::string line;
	std::unordered_map<int, std::unordered_set<int>> map;
	long ans = 0;
	
	while (std::getline(file, line)) {
		std::smatch matches;
		if (std::regex_match(line, matches, std::regex(R"((\d+)\|(\d+))"))) {
			map[stoi(matches[1].str())].insert(stoi(matches[2].str()));
			continue;
		}

		if (std::regex_match(line, std::regex(R"(\d+(?:,\d+)*)"))) {
			std::vector<int> nums;
			std::regex numpat(R"(\d+)");
			auto begin = std::sregex_iterator(line.begin(), line.end(), numpat);
			for (auto it = begin; it != std::sregex_iterator(); ++it) {
				nums.push_back(std::stoi(it->str()));
			}
			
			bool flag = true;
			for (int i = nums.size()-1; i >= 0; --i) {
				for (int j = i-1; j >= 0; --j) {
					if (map[nums[i]].find(nums[j]) != map[nums[i]].end()) {
						flag = false;
						break;
					}
				}
				if (!flag) {
					break;
				}
			}
			
			if (flag) {
				int i = (nums.size()>>1);
				ans += nums[(nums.size()>>1)];
			}
		} 
	}
	
    std::cout << ans << std::endl;
	file.close();
	return 0;
}
