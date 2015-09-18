from math import *

max=1000

def func1(max):
    a=0
    while a<max:
    	yield a
    	a = a+3
   
def func2(max):
    b,c=0,0
    while b<max:
    	yield b
    	b = b+5

diff=0
for n in func2(max):
	if n%3==0 and n%5==0:
		diff = diff +n
	
		  
c = sum(func1(max))
d = sum(func2(max))    
        
        
print  c+d-diff