from math import *

digits = int(raw_input("Number of digits: "))


def fib(dig):
    a, b = 0, 1
    while len(str(a)) < dig:
        a, b = b, a+b
    else:
     	return a

print fib(digits)