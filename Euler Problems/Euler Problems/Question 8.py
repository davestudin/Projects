from math import *


def pythag(max):
	mode=0
	a,b,c=0.0,0.0,0.0
	peri=0
	list=[]
	
	if max%2!=0:
		max=max+1	
	for a in range (1,max/2):
		for b in range(a,max/2):
			c=sqrt(a*a+b*b)	
			if c%1==0:
				peri=int(a+b+c)
				list.append(peri)			
	for n in list:
		count = 0
		for k in list:
			if n==k:
				count= count+1
		if count>mode:
			mode= count
			elem=n
	print 'The perimeter that repeats the most is ',elem, ' and it repeats ',mode, ' times'
		
pythag(1000)