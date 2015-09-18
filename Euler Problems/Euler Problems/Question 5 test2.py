counter = 0; prime = 0
def isprime(n):
    if n < 2:
        return False
    if n == 2:
        return True
    if n%2==0:
    	return False
    for x in range(3,n,2):
        if n % x == 0:
            return False
    return True
while True:
        counter = counter + 1
        x = isprime(counter)
        if x == True:
                prime = prime + 1
        if prime == 10001:
                break
print counter