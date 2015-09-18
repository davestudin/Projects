from math import *




def f():
	n=2
	while n%20!=0 or n%19!=0 or n%18!=0 or n%17!=0 or n%16!=0 or n%14!=0 or n%13!=0 or n%11!=0:
		n=n+2
	return n
		
		
print f()