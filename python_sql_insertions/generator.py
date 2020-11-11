import sys
import random
from random import randrange
import csv
import lorem

file1 = open("adr.txt", "r")
i=0
c=0
insert=[]
adresses=[]
emailDomain=["@gmail.com","@hotmail.com","@windowslive.com","@yahoo.com","@outlook.com"]
while(True):
	#read next line
	line = file1.readline()
	#if line is empty, you are done with all lines in the file
	if not line:
		break

	x=line.strip().replace('"','')
	y=x.split(",")
	adresses.insert(c,y[0])
	c+=1
 
file1.close
#get file object
f = open("names.txt", "r")
i=0
insert=[]
emailDomain=["@gmail.com","@hotmail.com","@windowslive.com","@yahoo.com","@outlook.com"]
while(True):
	#read next line
	line = f.readline()
	#if line is empty, you are done with all lines in the file
	if not line:
		break

	x=line.strip().split(" ")
	username=x[0][:3]+x[1][:3]
	email=username+emailDomain[randrange(5)]
	phone='+3069'+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))
	insert.insert(i,"insert into customers (username,email,cus_password,customer_name,customer_lastname,adress,phone) values ('"+username+"','"+email+"','kappakeepo123','"+x[0]+"','"+x[1]+"','"+adresses[i]+"','"+phone+"');\n")
	print(insert[i])
	i+=1
	



#close file
f.close

pCounter=0
prCounter=0
pList=[]
prList=[]
file2=open("customerTable.txt","w")
for p in insert:
    file2.write(p)
file2.close

with open('processor.csv',newline='') as csvfile:
    spamreader=csv.reader(csvfile,delimiter=',',quotechar='|')
    for row in spamreader:
        if pCounter<50:
            if row[11]=='model':
                continue
            if row[11] !='':
                if "amd" in row[18]:
                    pList.insert(pCounter,"Amd "+row[11])
                else:
                    pList.insert(pCounter,"Intel "+row[11])
            else:
                continue
        pCounter+=1

for itm in pList:
    barcode='520'+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))+str(randrange(9))
    prList.insert(prCounter,"insert into products(product_name,description,amount,price,barcode) values ('"+itm+"','"+lorem.sentence()+"',"+str(randrange(100))+","+str(round(random.uniform(0,100),2))+","+barcode+");\n")
    prCounter+=1

file3=open("productTable.txt","w")
for productI in prList:
    file3.write(productI)
file3.close