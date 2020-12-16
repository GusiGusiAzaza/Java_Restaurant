document.querySelector('.cart').onclick = () =>
{
    document.querySelector('.cart-menu').classList.toggle('cart-menu--active');
    showCartData();
}

document.querySelector('#orderButton').onclick = () =>
{
    order();
}

showCartCount();

function addEvent(elem, type, handler)
{
    if(elem.addEventListener) {
        elem.addEventListener(type, handler, false);
    } else {
        elem.attachEvent('on'+type, function(){ handler.call( elem ); });
    }
    return false;
}

function getCartData()
{
    return JSON.parse(localStorage.getItem('cart'));
}

function setCartData(o)
{
    localStorage.setItem('cart', JSON.stringify(o));
    return false;
}

function addToCart(e)
{
    let cartData = getCartData() || {},
        itemId = e.querySelector('input').value,
        itemTitle = e.querySelector('.element__title h3').innerHTML,
        itemPrice = e.querySelector('h2').innerHTML;

    if(cartData.hasOwnProperty(itemId)) // если такой товар уже в корзине, то добавляем +1 к его количеству
        cartData[itemId][2] += 1;
    else // если товара в корзине еще нет, то добавляем в объект
        cartData[itemId] = [itemTitle, itemPrice, 1];

    setCartData(cartData);
    showCartCount();
    showCartData();
}

function showCartCount()
{
    let count = 0;
    let cartData = getCartData();
    if(cartData !==null)
    {
        for(let item in cartData)
        {
            count += cartData[item][2];
        }
    }
    document.querySelector('.cart__count').innerHTML = count;
}


function showCartData()
{
    document.querySelector('.cart__container').innerHTML = '';
    let cartData = getCartData();
    if(cartData !==null)
    {
        for(let item in cartData)
        {
            let itemId = item;
            let itemTitle = cartData[item][0];
            let itemPrice = cartData[item][1];
            let itemCount = cartData[item][2];

            let elDiv = document.createElement('div');
            elDiv.setAttribute('class', 'cart__element');

            let innerDiv = document.createElement('div');
            innerDiv.setAttribute('class', 'cart__element__title');
            let h3 = document.createElement('h3');
            h3.innerHTML = itemTitle + ': ' + itemPrice;

            let innerDiv2 = document.createElement('div');
            innerDiv2.setAttribute('class', 'cart__element__count')
            let buttonPlus = document.createElement('button');
            buttonPlus.setAttribute('class', 'btn btn-primary');
            buttonPlus.innerHTML = '+';
            buttonPlus.onclick = () =>
            {
                addOneToCart(itemId);
            }
            let label = document.createElement('label');
            label.innerHTML = itemCount;
            let buttonMinus = document.createElement('button');
            buttonMinus.setAttribute('class', 'btn btn-primary');
            buttonMinus.innerHTML = '-';
            buttonMinus.onclick = () =>
            {
                removeFromCart(itemId);
            }

            innerDiv.appendChild(h3);
            innerDiv2.appendChild(buttonMinus);
            innerDiv2.appendChild(label);
            innerDiv2.appendChild(buttonPlus);
            elDiv.appendChild(innerDiv);
            elDiv.appendChild(innerDiv2);

            document.querySelector('.cart__container').appendChild(elDiv);
        }
    }
    else
    {
        document.querySelector('.cart__container').innerHTML = 'Cart is empty';
    }
}

function removeFromCart(itemId)
{
    let cartData = getCartData();
    if(cartData.hasOwnProperty(itemId))
    {
        if(cartData[itemId][2] === 1)
        {
            delete cartData[itemId]
        }
        else
        {
            cartData[itemId][2]--;
        }
        setCartData(cartData);
        showCartData();
        showCartCount();
    }
}

function addOneToCart(itemId)
{
    let cartData = getCartData();
    if(cartData.hasOwnProperty(itemId))
    {
        cartData[itemId][2]++;
        setCartData(cartData);
        showCartData();
        showCartCount();
    }

}

async function order()
{
    let cartData = getCartData();

    if(cartData.length === 0 || cartData === null)
    {
        return ;
    }

    foods = [];
    for (item in cartData)
    {
        let foodId = item;
        let count = cartData[item][2];

        let food =
            {
                foodId : foodId,
                count  : count
            };
        foods.push(food);
    }
    let request =
        {
            foods : foods
        };
    let token = GetCookie('jwt');
    let response = await fetch('api/order',
        {
            method: 'POST',
            headers: {'Authorization': 'Bearer_' + token, 'Accept': 'application/json', 'Content-Type': 'application/json'},
            body : JSON.stringify(request)
        });
    if (response.status === 200)
    {
        document.querySelector('.cart__container').innerHTML = 'Your order is being processed';
        localStorage.clear();
        showCartCount();
    }
    else
    {
        window.location.href = '/login';
    }
}

