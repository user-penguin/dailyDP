# dailyDP
&gt;^.^&lt;

Changes 4.08.18
1) слил таблицы employee и account_data, новый скрипт для базы administration скинул в telegram
2) поправил запросы в DBRequests
3) написал методы в DBRequests: putEmployee и getEmployeeId
4) в контейнере припилил два метода, один добавляет в базу сотрудника, получает его id_employee,
добавляет этот id_employee в экземпляр Employee, после чего второй метод добавляет этот экземпляр 
Employee в контейнер
5) соответственно внёс пару правок в сервер и интерфейсы
6) запилил метод в контроллере, который засылает данные из формочки "новый сотрудник" на сервак
7) больше всего ебли было с фронтом, потому что скилухи нет вот вообще, но теперь я весь остальной фронт изи накатаю,
потому что основные шаблоны уже отработаны за последние сутки


//todo:
1) нужна какая-то синхронизация между таблицами, иначе пиздец.
Добавляем эмплои, тип учётки, этот сотрудник сразу должен появляться в соответствующих таблицах
2) добавить врзможность редактировать информации о сотруднике, то есть сразу вытекает запрос к контейнеру,
к БД и тд, плюс формочку во фронте соответствующую наваять
3) разобраться с паролями, потому что следующими шагами будет разрабатываться окружение менеджера,
а это другой тип учётки, нужно будет разделять как-то UI
4) хз чё ещё, пока и этого дофига, мб потом допишу
