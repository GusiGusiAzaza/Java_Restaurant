checkAdminRole();
showOrders();

async function checkAdminRole()
{
    let isAdmin = false;
    let token = GetCookie('jwt');
    if(token === false)
    {
        return false;
    }

    let response = await fetch('api/auth/username',
        {
            headers: {'Authorization': 'Bearer_' + token, 'Accept': 'application/json'}
        });
    if(response.status === 200)
    {
        let data = await response.json();
        data.roles.forEach(role =>
        {
            if (role.name === 'ADMIN') isAdmin = true;
        });
    }
    else
    {
        DeleteCookie('jwt');
        return false;
    }
    if(!isAdmin)
    {
        window.location.href = '/';
        return false;
    }
    return true;
}

async function showOrders()
{

    if(!checkAdminRole())
    {
        return;
    }
    let token = GetCookie('jwt');

    let response = await fetch('api/admin/orders',
        {
            headers: {'Authorization': 'Bearer_' + token, 'Accept': 'application/json'}
        });
    if(response.status === 200)
    {
        document.querySelector('#ordersDiv').innerHTML = "";
        let data = await response.json();
        data.forEach(order =>
        {
            if(!order.delivered) {
                let div = document.createElement('div');
                let userDiv = document.createElement('div');
                userDiv.innerHTML = order.username + " : " + order.phone;
                let foodsDiv = document.createElement('div');
                foodsDiv.setAttribute('class', 'order-foods');

                order.foods.forEach(food => {
                    let foodDiv = document.createElement('div');
                    foodDiv.innerHTML = food.Title + " : " + food.Count;
                    foodsDiv.appendChild(foodDiv);
                });
                div.appendChild(userDiv);
                div.appendChild(foodsDiv);

                let readyButton = document.createElement('button');
                readyButton.innerHTML = 'Ready!'
                readyButton.onclick = () =>
                {
                    orderReady(order.orderId);
                }
                div.appendChild(readyButton);
                document.querySelector('#ordersDiv').appendChild(div);
            }
        })
    }
}

async function orderReady(orderId)
{
    let token = GetCookie('jwt');

    let response = await fetch('api/admin/orders/' + orderId,
        {
            method: "PUT",
            headers: {'Authorization': 'Bearer_' + token, 'Accept': 'application/json'}
        });
    if(response.status === 200)
    {
        showOrders();
    }
}

async function addFood()
{
    let image = document.querySelector('#add_image').value;
    let title = document.querySelector('#add_title').value;
    let price = document.querySelector('#add_price').value;
    let weight = document.querySelector('#add_weight').value;

    let token = GetCookie('jwt');

    let response = await fetch("/api/admin/food",
        {
            method: 'POST',
            headers: {'Authorization': 'Bearer_' + token, 'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                image : image,
                title: title,
                price: price,
                weight: weight
            })
        });
    if(response.status === 200)
    {
        window.location.href = '/admin';
    }
}

async function updateFood()
{
    let id = document.querySelector('#update_id').value;
    let image = document.querySelector('#update_image').value;
    let title = document.querySelector('#update_title').value;
    let price = document.querySelector('#update_price').value;
    let weight = document.querySelector('#update_weight').value;

    let token = GetCookie('jwt');

    let response = await fetch("/api/admin/food",
        {
            method: 'PUT',
            headers: {'Authorization': 'Bearer_' + token, 'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                id : id,
                image : image,
                title: title,
                price: price,
                weight: weight
            })
        });
    if(response.status === 200)
    {
        window.location.href = '/admin';
    }
}

async function deleteFood()
{
    let id = document.querySelector('#delete_id').value;
    let token = GetCookie('jwt');

    let response = await fetch("/api/admin/food/" + id,
        {
            method: 'DELETE',
            headers: {'Authorization': 'Bearer_' + token, 'Content-Type': 'application/json', 'Accept': 'application/json'},
        });
    if(response.status === 200)
    {
        window.location.href = '/admin';
    }
}

function GetCookie(a)
{
    let b = new RegExp(a+'=([^;]){1,}');
    let c = b.exec(document.cookie);
    if(c) c = c[0].split('=');
    else return false;
    return c[1] ? c[1] : false;
}

function DeleteCookie ( cookieName )
{
    var cookieDate = new Date ( );  // Текущая дата и время
    cookieDate.setTime ( cookieDate.getTime() - 1 );
    document.cookie = cookieName += "=; expires=" + cookieDate.toGMTString();
}
