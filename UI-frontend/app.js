const bodyParser = require('body-parser')
const express = require('express')
const axios = require('axios');
const path = require('path');
const app = express()

const { JSDOM } = require( "jsdom" );
const { window } = new JSDOM( "" );
const $ = require( "jquery" )( window );

const baseUrl = 'http://localhost:8000'

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: true }))
 
// parse application/json
app.use(bodyParser.json())

//serve static folder
app.use(express.static(path.join(__dirname, 'static')));

let userid = 1
let username = null

//-------------------------------------HOME PAGE / SHOW ALL PRODUCTS------------------------------------
app.get('/', function (req, res) {
    if(userid){
        axios.get(baseUrl + '/product/user/' + userid)
        .then((response) => {
            res.render("home.ejs", {products: response.data.body, userid: userid})
        })
        .catch(err => {
            console.log(err)
        })
    }
    else{
        res.render("login.ejs")
    }
})

//-------------------------------------REGISTER ------------------------------------
app.post('/register', (req, res) => {  
    console.log(req.body);
    let data = 'username='+ req.body.username + '&password' + req.body.password
    axios.post(baseUrl + '/user/register', req.body, {headers: {
        'Content-Type': 'application/json',
    }} )
    .then(response => {
        console.dir(response)
        res.send(response)
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    });
});

//-------------------------------------LOGIN------------------------------------
app.post('/login', (req, res) => {
    let data = 'username='+ req.body.username + '&password' + req.body.password
    axios.post(baseUrl + '/user/login', {
        username: req.body.username,
        password: req.body.password
    } )
    .then(response => {
        console.dir(response)
        res.send(response)
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    });
});
   
//-------------------------------------ADD PRODUCT------------------------------------
app.post('/create', (req, res) => {
    axios.post(baseUrl + '/product/create', req.body)
    .then((response) => {
        res.redirect('/')
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    })
});


//-------------------------------------UPDATE PRODUCT GET------------------------------------
app.get('/update/:productid', (req, res) => {
    let productid = req.params.productid
    axios.get(baseUrl + '/product/' + productid)
    .then((response) => {
        console.log(response.data.body)
        res.render("update.ejs", {product: response.data.body})
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    })
})

//-------------------------------------UPDATE PRODUCT POST------------------------------------
app.post('/update/:productid', (req, res) => {
    let productid = req.params.productid
    let url = baseUrl + '/product/' + productid + '/update'
    axios.put(url, req.body)
    .then((response) => {
        // console.log(response.data.body)
        res.redirect('/')
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    })
})

//-------------------------------------DELETE PRODUCT------------------------------------
app.post('delete/:productid', (req, res) => {
    let productid = req.params.productid
    let url = baseUrl + '/product/' + productid + '/update'
    axios.delete(url)
    .then((response) => {
        console.log(response.data.body)
        res.redirect('/')
    })
    .catch(err => {
        console.log(err)
        res.send(err)
    })
});

//-------------------------------------LOGOUT------------------------------------
app.post('/logout', (req, res) => {
    userid = null
    username = null
    res.redirect('/')
});

//-------------------------------------LISTEN PORT 3000------------------------------------
app.listen(3000, () => {
    console.log("Server listening at port 3000");
})