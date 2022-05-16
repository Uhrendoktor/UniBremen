tut = [[],[],[]];
students = [
    [False, True, True],
    [True, True, False],
    [False, True, True],
    [True, True, False],
    [False, True, False]
]
MAX = 2

def sort(s):
    if s==len(students):
        return True
    for i in range(0, len(tut)):
        if students[s][i] and len(tut[i]) < MAX:
            tut[i].append(s)
            if not sort(s+1):
                tut[i].remove(s)
            else:
                return True
    return False

def sortAll():
    return sort(0)

def printTut():
    msg = "+|0|1|2\n-------\n"
    for i in range(0, len(tut)):
        msg+= " |"+"|".join([str(a) for a in tut[i]])+"\n"
    print(msg)

sortAll()
printTut()
