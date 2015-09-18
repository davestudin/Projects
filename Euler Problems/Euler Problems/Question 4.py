from math import *

x=100

def sumsquares(max):
	n,n1 = 1,1
	
	while n<max+1:
		yield n1
		n = n+1
		n1 = n*n
def squaresum(max):
	k=1
	
	while k<max+1:
		yield k
		k = k+1

print 'The square of the sum of the first 200 natural numbers is ',sum(squaresum(x))*sum(squaresum(x)),'.'
print '\n'
print 'The sum of the square of the first 200 natural numbers is ',sum(sumsquares(x)),'.'
print '\n'
print 'The difference between the sum of the squares of the first 200 natural numbers and the square \nof the sum of the first 200 natural numbers is ',sum(squaresum(x))*sum(squaresum(x))-sum(sumsquares(x)),'.'
