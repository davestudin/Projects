from math import *



def f():
	n=0
	x=0
	triNum=1
	list=[]
	while n<1000:
		n=n+1
		triNum=(n*(n+1))/2
		list.append(triNum)
	for n in list:
		if (1+sqrt(1+8*(n)))/4%1==0 and(1+sqrt(1+24*(n)))/6%1==0 and n!=1:
			print 'The next triangular number that is also a pentagonal and hexagonal number is ',n
			break
f()