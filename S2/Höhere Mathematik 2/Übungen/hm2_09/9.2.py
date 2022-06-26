import numpy as np
import matplotlib.pyplot as plt
import cmath

v1 = np.array([[-1j],[1]])
v2 = np.array([[1j],[1]])
c1 = 0.5*(1+1j)
c2 = 0.5*(1-1j)

def y(t):
    return c1*cmath.exp(-(1+1j)*t)*v1+c2*cmath.exp((-1+1j)*t)*v2

timesteps = [2**x/100 for x in np.arange(0,30,0.1)]
data = [y(t) for t in timesteps]
datax = np.array([value.item(0) for value in data])
datay = np.array([value.item(1) for value in data])

print(datax, datay)

plt.plot([-1,1,1,-1,-1],[-1,-1,1,1,-1])
plt.plot(datax, datay)


speed = 0.001
pos = np.array([[1],[1]])
sim = list()
sim.append(pos)
def rot(vec):
    return np.array([[-vec.item(1)],[vec.item(0)]])

for i in np.arange(0, 3000):
    v = (rot(pos)-pos)
    vm = np.sqrt(v.item(0)**2+v.item(1)**2)
    vn = v/vm
    pos= pos+ vn*speed
    sim.append(pos)

simx = np.array([value.item(0) for value in sim])
simy = np.array([value.item(1) for value in sim])
plt.plot(simx, simy)

plt.show()
