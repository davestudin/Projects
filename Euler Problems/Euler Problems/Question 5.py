from math import *



def primeDef(num):
	if num<2:
		return False
	if num==2:
		return True
	if num%2==0:
		return False
	for x in range(3,int(sqrt(num))+1,2):
		if num%x==0:
			return False
			
	else:
		return True

def prime(target):
	stop=False
	counter = 0
	p=1
	
	while stop==False:
		p=p+1
		if primeDef(p)==True:
			counter=counter+1
		if counter==target:
			stop=True
			print p
	
prime(10001)	

	