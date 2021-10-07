![](VendingHierarchy.png)

<H1>Purchase Menu</H1>
<u><h3>1. FEED MONEY</h3></u>                                                          

     
a. User can continuously add money (up to how much??) <br>
b. Whole Dollar Amounts Only (convert these to BigDecimal)

<br>
<br>

<u><h3>2. SELECT PRODUCT</h3></u>                                            

   
a. Display products (copy and paste this) 
          
b. insert logic within this nest
          
c. if (money < price) {ask for more money}  
          
d. Dispense Product:<br>
        
>All chip items print "Crunch Crunch, Yum!"<br>
        All candy items print "Munch Munch, Yum!"<br>
        All drink items print "Glug Glug, Yum!"<br>
        All gum items print "Chew Chew, Yum!"<br> 
          
e. Write purchase to log.txt (01/01/2016 12:00:00 PM FEED MONEY: $5.00 $5.00) 
                             
f. Return customer to Purchase Menu

g. Update Machine Balance::: if (money > price) {machineBalance+= (custMoney - price)}
<br>
<br>
<br>





<u><h3>3. FINISH TRANSACTION</h3></u>                                             

       
a. giveChange --> print("Here's your change:" + " " + machineBalance)

b. machineBalance-= machineBalance

c. Return customer to Main Menu<br><br>




<h1>Main Menu</h1>


<u><h3>3. Exit</h3></u>
a. Terminate program

<u><h3>4. SALES REPORT</h3></u>                                                       

a. Total sales for each item
<br>-per machine instance (program instance)
      
b. Write each <u><b>item | totalSold</b></u> to txt.file<br>
            Potato Crisps|10<br>
            Stackers|3<br>
            Grain Waves|0
    




















*************************************************************************************************************
                                        TO DO LIST                                                          *
*************************************************************************************************************


--Dispenses item
    //add message

--Main Menu: 1. Display, 2. Purchase, 3. Exit
    //add "3. Exit" option


--Automatically restocked
    //Test this


--(1.) Displays all items and info
    //Add "Sold Out" if inventory == 0


--Add Purchase Menu (Nested--> (2) Purchase)












******************************************************************************************************************
                                        COMPLETED TASKS                                                          *
******************************************************************************************************************
Vending machine stocked via CVS file







