def approx(n):
    sum = 0
    for i in range(0, n):
        sum+=n/((n+i)*(n+i))
    return sum

for i in range(0, 1000000+1, 10000):
    print(i, approx(i))
