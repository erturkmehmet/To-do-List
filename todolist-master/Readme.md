Spring Boot - TODO-LIST Application - REST

1- TodoListController ****

Create Todolist  -- localhost:8080/todos

curl -X POST \
  http://localhost:8080/todos \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 74412735-3d54-183c-b2b5-a22aa4b06fa9' \
  -d '{ 
    "name": "TestItem123",
    "description": "test",
    "status": "uncompleted",
    "deadline": "2018-01-01T00:00:00.000+0000"
}'

List All Todolist  -- localhost:8080/todos

curl -X GET \
  http://localhost:8080/todos \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 520072a9-f6f0-887f-39e9-4534c2e364b0' \
  -d '{ 
    "name": "TestItem123",
    "description": "test",
    "status": "uncompleted",
    "deadline": "2018-01-01T00:00:00.000+0000"
}'

Get a TodoList By Id

curl -X GET \
  http://localhost:8080/todos/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: b05be6ee-0ec7-8e4e-646a-3c179ff9cc0a' \
  -d '{ 
    "name": "TestItem123",
    "description": "test",
    "status": "uncompleted",
    "deadline": "2018-01-01T00:00:00.000+0000"
}'

Delete Todolist  -- localhost:8080/todos/{id}

curl -X DELETE \
  http://localhost:8080/todos/1 \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: a205509e-4381-f50a-dba9-b7804caa2d6a' \
  -d '{ 
    "name": "TestItem1234"
}'

2- ItemController ****

Create Item  -- localhost:8080/todos/{id}/items

curl -X POST \
  http://localhost:8080/todos/1/items \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: 6d80ac93-fd0f-c1fb-4df0-31f69a42aa93' \
  -d '{ 
    "name": "TestItem123",
    "description": "test",
    "status": "uncompleted",
    "deadline": "2018-01-01T00:00:00.000+0000"
}'

Get TodoItems -- localhost:8080/todos/1/items

3- User Controller

To Create User  -- localhost:8080/user

To Get a User 	--	localhost:8080/user/1

