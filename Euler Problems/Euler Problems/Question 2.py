from math import *

max = 2000000


def fibonacci(max):
    a, b = 0, 1
    while a < max:
        yield a
        a, b = b, a+b

sum=0
for n in fibonacci(max):
    if n%2==0:
    	sum = sum + n
    
print sum