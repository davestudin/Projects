


list=[[3],[7,4],[2,4,6],[8,5,9,3]]

def listNum(list):
	length=len(list)
	path=[]
	
	a=list[0]
	b=list[1]
	
	for i in range(0,length):
		a=list[i]
		max1=0
		count=0
		for n in a:
			count=count+1
			if n>max1:
				max1=n
				position=count
				path.append(max1)
		
	print path
		
listNum(list)		
		
		






