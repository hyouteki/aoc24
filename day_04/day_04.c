#include <stdio.h>
#include <stdlib.h>
#include <string.h>

static int ans = 0;
static int m = 0, n = 0;
static char *word = "XMAS";

void foo(char buffer[][256], int x, int y, int dx, int dy, int w) {
	if (w == strlen(word)) {
		ans++;
		return;
	}
	if (x < 0 || m <= x || y < 0 || n <= y) return;
	if (buffer[x][y] == word[w]) {
		foo(buffer, x+dx, y+dy, dx, dy, w+1);
	}
	return;
}

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
			foo(buffer, i, j, -1, -1, 0);
			foo(buffer, i, j, -1,  0, 0);
			foo(buffer, i, j, -1,  1, 0);
			foo(buffer, i, j,  0, -1, 0);
			foo(buffer, i, j,  0,  1, 0);
			foo(buffer, i, j,  1, -1, 0);
			foo(buffer, i, j,  1,  0, 0);
			foo(buffer, i, j,  1,  1, 0);
		}
	}

	printf("%d\n", ans);
	
	fclose(file);
	return 0;
}
