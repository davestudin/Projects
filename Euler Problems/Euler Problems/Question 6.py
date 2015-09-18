from math import *

input = int(raw_input("Enter sum of the desired Pythagorean Triple: "))

def pythag(sum):
	a,b,c=0.0,0.0,0.0
	for a in range (1,sum):
		for b in range(a,sum):
			c=sqrt(a*a+b*b)
			if c%1==0 and a%1==0 and b%1==0:	
				if (a+b+c)==sum:
					return 'The Pythagorean triple with sides that add up to '+ str(sum) + ', is a = ' + str(a) + ', b = ' +str(b) + ', c = ' +str(int(c)) + '.'          
				
	else:
		return 'There is no Pythagorean triple with sides that add up to '+ str(sum)		
				
print pythag(input)