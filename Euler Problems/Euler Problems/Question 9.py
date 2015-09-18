from math import *


num=10
def pent():
	n=1000
	pentNum=1
	listA=[]
	stop=False
	while n<10000:
		n=n+1		
		pentNum=(n*(3*n-1))/2
		listA.append(pentNum)
	for a in listA:
		for k in listA:
			if a!=0 and k!=0 and a<k:
				if (1+sqrt(1+24*(a+k)))/6%1==0 and (1+sqrt(1+24*(k-a)))/6%1==0:
					print 'The smallest pair of pentagonal numbers whose sum and difference are also pentagonal numbers are ',a, ' and ',k ,'.'
					break


pent()