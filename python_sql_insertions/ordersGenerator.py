from random import randrange
i=0
orderList=[]
while i<50:
    customer=str(randrange(10,50))
    product=str(randrange(20,55))
    amount=str(randrange(1,2000))
    orderID=str(randrange(1,10))
    orderList.insert(i,"insert into orders(order_id,amount,customer_id,product_id) values ("+orderID+","+amount+","+customer+","+product+");\n")
    i+=1

file2=open("orders.txt","w")
for p in orderList:
    file2.write(p)
file2.close