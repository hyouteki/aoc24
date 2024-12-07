#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define N 256
static size_t m, n;
static char buffer[N][N];
static long ans = 0;

static int confs[][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
#define correct(x, y) (0 <= x && x < m && 0 <= y && y < n)
static size_t update[] = {3, 2, 0, 1};

int bar(int x, int y, size_t conf) {
	if (!correct(x, y)) return 0;
	int dx = confs[conf][0], dy = confs[conf][1];
	int nx = x+dx, ny = y+dy;
	return (buffer[x][y] == '#' || bar(nx, ny, conf));   
}

void foo(int x, int y, size_t conf) {
	if (!correct(x, y)) return;
	if (buffer[x][y] == '.') {
		buffer[x][y] = 'X';
	}
	int dx = confs[conf][0], dy = confs[conf][1];
	int nx = x+dx, ny = y+dy;
	if (!correct(nx, ny)) {
		return;
	}
	if (buffer[nx][ny] == '#') {
		foo(x, y, update[conf]);
		return;
	}
	int ux = confs[update[conf]][0], uy = confs[update[conf]][1];
	int mx = x+dx, my = y+dy;
	if (bar(mx, my, conf)) {
		printf("%d %d\n", x, y);
		ans++;
	}
	foo(nx, ny, conf);
}

int main() {
	FILE *file = fopen("example.txt", "r");
	if (!file) return 1;

	while (fgets(buffer[m], sizeof(buffer[m]), file)) {
		m++;
	}
	n = sizeof(buffer[0]);

	for (int i = 0; i < m; ++i) {
		for (int j = 0; j < n; ++j) {
			int conf = -1;
			if (buffer[i][j] == '^') conf = 1;
			if (buffer[i][j] == '<') conf = 3;
			if (buffer[i][j] == '>') conf = 2;
			if (buffer[i][j] == 'v') conf = 0;

			if (conf == -1) continue;
			buffer[i][j] = '.';
			foo(i, j, conf);
		}
	}

	printf("%d\n", ans);
	fclose(file);
	return 0;
}
