To change the file:
 1. Add the file to the root directory (where account.csv is present)
 2. Update the value for fileName in AccountTransactionServiceTest

Run the file AccountTransactionServiceTest to test the below 4 functions:

function **findTotalClosingBalance(csvFileName, date)** <br/>
_Parameters_
<br/>
String csvFileName - name of file which contains account transaction <br/>
String date - a date object in format "yyyy-MM-dd"
<br/>
_Returns_ 
<br/>
String - the total closing balance of all accounts on that date.

function **findAverageMonthlyBalance(csvFileName, accountId, month)** <br/>
_Parameters_
<br/>
String csvFileName - name of file which contains account transaction <br/>
String accountId - account id of user
int month - month as integer (1 - Jan, 2 - Feb and so on..)
<br/>
`Formula = (Sum that account's closing balance for each day throughout that
month)/(number of days in that month)
`
<br/>
_Returns_ 
<br/>
String - the average monthly balance for given account for given month.


function **findLowestMonthlyBalanceAccount(csvFileName, month)** <br/>
_Parameters_
<br/>
String csvFileName - name of file which contains account transaction <br/>
int month - month as integer (1 - Jan, 2 - Feb and so on..)
<br/>
`Formula = find the average monthly balance for all accounts for that month and return the
 lowest ones amongst it
`
<br/>
_Returns_ 
<br/>
List<String> - returns the account Id(s) that has the lowest average monthly balance for given month.

function **findTopSpender(csvFileName, fromDate, toDate)** <br/>
_Parameters_
<br/>
String csvFileName - name of file which contains account transaction <br/>
String fromDate - start date of interval in format "yyyy-MM-dd"
String toDate - end date of interval in format "yyyy-MM-dd"
<br/>
_Returns_ 
<br/>
List<String> - the account Id(s) that has the maximum debit amount in the given date interval
