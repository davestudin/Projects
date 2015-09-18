from math import *

def comb():
	n,r=1,1
	pairs=0
	combination = factorial(n)/(factorial(r)*(factorial(n-r)))
	for n in range(1,101):
		for r in range(1,n):
			if factorial(n)/(factorial(r)*(factorial(n-r)))>100000:
				pairs = pairs +1
	print 'There are ',pairs, 'pairs of n and r where n and r are positive integers less than 100 and C(n,r) is greater than a million.'
	


comb()			
