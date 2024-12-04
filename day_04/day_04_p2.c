#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static int ans = 0;
static int m = 0, n = 0;

int foo(char buffer[][256], int x, int y, char w) {
	if (x < 0 || m <= x || y < 0 || n <= y) return 0;
	return buffer[x][y] == w;
}

static char conf[][5] = {"MSMS", "SMSM", "SSMM", "MMSS"};
static int dx[] = {-1, -1,  1, 1};
static int dy[] = {-1,  1, -1, 1};

int main() {
	FILE *file = fopen("data.txt", "r");
	if (file == NULL) {
		return 1;
	}

	char buffer[256][256];
	while (fgets(buffer[m], sizeof(buffer[m]), file)) {
		m++;
	}
	
	n = strlen(buffer[0]);

	for (size_t i = 0; i < m; ++i) {
		for (size_t j = 0; j < n; ++j) {
			if (buffer[i][j] != 'A') {
				continue;
			}
			for (int k = 0; k < 4; ++k) {
				int flag = 1;
				for (int l = 0; l < 4; ++l) {
					if (!foo(buffer, i+dx[l], j+dy[l], conf[k][l])) {
						flag = 0;
						break;
					}	
				}
				if (flag) {
					ans++;
					break;
				}
			}
		}
	}

	printf("%d\n", ans);
	
	fclose(file);
	return 0;
}
