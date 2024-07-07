# Задание

## Создать электронную очередь.
1. Создать класс TicketNumberGenerator - бин, у которого есть метод createNewNumber(), который возвращает строки вида "Ticket #X", где X - UUID (UUID.randomUUID().toString())

2. Создать класс Ticket (не бин!!!) с полями:
* 2.1 String number - номер тикета
* 2.2. LocalDateTime createdAt - дата создания

3. Класс "Табло" - бин (синглтон), у которого есть поле ticketNumberGenerator
и метод метод newTicket(), который создает объетк класса Ticket с значениями полей:
* 3.1 number - результат вызова TicketNumberGenerator#createNewNumber
* 3.2. createdAt - LocalDateTime.now()
