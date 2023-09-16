# There are 2 bugs in this code
# 1st Bug:
#       when n<20 but greater than 10 , Finding factorial have product of number
#       between 1 to number(n-10) both including but given code had excluded the number
#       for the product
# 2nd Bug:
#       when n>20 we have to return the sum the numbers from 1 to number(n-20) that can
#       be archived by using the formula n*(n+1)/2 further we can get it by ((n*n)+n)/2 but the given expression
#       don't have the correct implementation for this

def compute(n):
    if n < 10:
        out = n ** 2
    elif n < 20:
        out = 1
        for i in range(1, n - 9):  # Fixed the range upper bound from n-10 to n-9
            out *= i
    else:
        lim = n - 20
        out = lim * lim
        out = out + lim  # Changed '-' to '+'
        out = out / 2
    #     out=out // 2 can be used to get the result in Integer form
    print(out)


n:int = int(input("Enter an integer: "))
compute(n)