def fib(n):
    m = [[0 for x in range(int(n/2+1))] for y in range(n)]
    m[0] = [1 for x in range(int(n/2+1))];
    for y in range(0, n):
        m[y][0] = 1
    for x in range(1,int(n/2)):
        for y in range(1, n):
            m[y][x] = m[y-1][x] + m[y][x-1]
    return m

m = fib(20)
for a in m:
    print(a)
