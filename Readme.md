перед запуском проекта небходимо его собрать через mvn clean install
открыть SOAP UI и указать урл для текущего проекта http://localhost:8082/ws/purchase.wsdl
sql скрипты находятся в папке src\main\resources\postgresql\init.sql

касательно схемы xsd
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:my="http://www.my.com">

<my:purchaseName> обязательный тэг
<my:customer> обязательный тэг, указывается покупатель
<my:purchaseList>   необязательный тэг, указывается список покупок, 
                    тэгов может быть много (на каждый товар указывается отдельный тэг),
                    в каждом отображается наименование товара, количество купленного товара и общая сумма покупки данного товара

<my:purchase_date> дата покупки