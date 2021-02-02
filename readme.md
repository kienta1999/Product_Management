# Instruction

ProductManagerRestfulAPI is a RESTfull API built by Spring boot  

## User  
### Base URL: localhost:8000/user  
### Endpoint  
- GET / : list all users  
- POST /register : Register an user to DB  
    Request Body:   
    {  
        username: String,  
        password: String  
    }  
- POST /login : log user in   
    Request Body:   
    {  
        username: String,  
        password: String  
    }  

## Product
### Base URL: localhost:8000/product 
### Endpoint  
- GET / : list all product  
- GET /user/{userid}: list all product of user with userid   
- GET /{productid} : list the product with productid  
- POST "/product/create" : Create a new product   
Request Body:   
    {  
        userid: Integer,
        name: String,  
        price: Integer  
    }  
- PUT "/product/{productid}/update" : update the product with productid  
Request Body:   
    {  
        userid: Integer,
        name: String,  
        price: Integer  
    }  
- DELETE "/product/{productid}/delete" : delete the product with productid  