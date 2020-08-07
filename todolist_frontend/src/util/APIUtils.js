import { API_BASE_URL } from '../constants';

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    })  

    

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                if(response.status == 415)
                {
                    return json;
                }
                return Promise.reject(json);
            }           
            return json;
        })
    );
};

export function getCurrentUser() {
    
    return request({
        url: API_BASE_URL + "/users/1",
        method: 'GET'
    });
}

export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/users/1",
        method: 'GET',       
    });
}

export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/users",
        method: 'POST',  
        body: JSON.stringify(signupRequest)   
    });
}

export function getTodoLists(options) {
    return request({
        url: API_BASE_URL + "/todos",
        method: 'GET',       
    });
  } 

  export function deleteTodoList(options, listId) {
    return request({
        url: API_BASE_URL + "/todos/" + listId,
        method: 'DELETE',       
    }); 

    }

    export function addTodoList(options, listId, itemName) {
        let newItem = {
            name: itemName
        }
        return request({
            url: API_BASE_URL + "/todos",
            method: 'POST',
            body: JSON.stringify(newItem)    
        });
      }    

export function getTodoItems(options, listId) {
    return request({
        url: API_BASE_URL + "/todos/" + listId + "/items",
        method: 'GET',       
    });
  }  

export function deleteTodoItem(options, listId, itemId) {
    return request({
        url: API_BASE_URL + "/todos/" + listId + "/items/" + itemId,
        method: 'DELETE',       
    }); 
  }  

    export function addTodoItem(options, listId, itemName) {
        let newItem = {
            name: itemName,
            description: "test",
            status: "uncompleted",
            deadline: "2018-01-01T00:00:00.000+0000" 
        }
        return request({
            url: API_BASE_URL + "/todos/" + listId + "/items",
            method: 'POST',
            body: JSON.stringify(newItem)     
        });
      }   