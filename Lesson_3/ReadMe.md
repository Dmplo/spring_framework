# Задание
1. В объект timesheet в поле createdAt должно подставляться текущее время на стороне сервера
2. Создать отдельный контроллер для проектов (поле Timesheet.project)
* 2.1 Создать класс Project с полями id, name
* 2.2 Создать CRUD-контроллер для класса Project, сервис и репозиторий
* 2.3 В ресурсе Timesheet поле project изменить на projectId
* 2.4 При создании Timesheet проверять, что project с идентификатором projectId существует

3. Создать ресурс /projects/{id}/timesheets - загрузить таймашиты для конкретного проекта
4. Создать ресурс /timesheets?createdAtAfter=2024-07-04
5. Создать ресурс /timesheets?createdAtBefore=2024-07-04