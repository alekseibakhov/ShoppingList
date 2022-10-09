1) перед запуском проекта небходимо его собрать через mvn clean install
2) открыть SOAP UI и указать урл для текущего проекта http://localhost:8082/ws/purchase.wsdl
3) sql скрипты находятся в папке src\main\resources\postgresql\init.sql

 схема xsd
1. описание:
<my:purchaseName> обязательный тэг
<my:customer> обязательный тэг, указывается покупатель (все поля обязательны к заполнению)
<my:purchaseList>   необязательный тэг, указывается список покупок, 
                    тэгов может быть много (на каждый товар указывается отдельный тэг),
                    в каждом отображается наименование товара, количество купленного товара и общая сумма покупки данного товара
                    данные поля являются обязательными при указании к.л. покупки)
<my:purchase_date> обязательный тэг, указывается в формате YYYY-MM-DD
                    
1.1. согласно требованиям, установил ограничение на наименование товаров. 
Принимаются только следующие:
   1.1.1.  Телевизор
   1.1.2.   Смартфон
   1.1.3.   Соковыжималка
   1.1.4. Наушники
   1.1.5. Клавиатура
<my:purchase_date> дата покупки
2. пример запроса:
   <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:my="http://www.my.com">
       <soapenv:Header/>
           <soapenv:Body>
           <my:postRequest>
           <my:purchaseName>Покупка1</my:purchaseName>
           <my:customer>
               <my:name>Алексей</my:name>
               <my:lastname>Бахов</my:lastname>
               <my:age>28</my:age>
           </my:customer>
           <my:purchaseList>
               <my:name>Телевизор</my:name>
               <my:count>1</my:count>
               <my:cost>54</my:cost>
           </my:purchaseList>
           <my:purchase_date>2020-10-20</my:purchase_date>
           </my:postRequest>
       </soapenv:Body>
   </soapenv:Envelope>
3. примеры ответов:
   а) в случае успешного сохранения и обработки:
       <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
       <SOAP-ENV:Header/>
       <SOAP-ENV:Body>
       <ns2:postResponse xmlns:ns2="http://www.my.com">
       <ns2:response>Покупка выполнена успешно!</ns2:response>
       </ns2:postResponse>
       </SOAP-ENV:Body>
       </SOAP-ENV:Envelope>
   б) в случае, если указано некорректное название товара в списке покупок:
       <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
       <SOAP-ENV:Header/>
       <SOAP-ENV:Body>
       <ns2:postResponse xmlns:ns2="http://www.my.com">
       <ns2:response>Не указано наименование товара в списке покупок</ns2:response>
       </ns2:postResponse>
       </SOAP-ENV:Body>
       </SOAP-ENV:Envelope>
4. Описание таблиц
1) в табл buyer лежат покупатели 
2) в табл goods лежат товары (наименование и цена за штуку)
3) в табл shopping_list лежат покупки (а) связь с покупателями и товарами лежит через их id, 
б) отфильтровать одну общую покупку одного покупателя можно через поле name, у всех строк это поле будет идентичным
в) в поле cost указывается общая сумма покупки одного товара одним покупателем за одну покупку
г) в таблице buyer поле price хранится стоимость 1 товара за 1шт, а в таблице shopping_list поле cost хранится
общая сумма покупки 1 покупателем одного товара)