async function Register()
{
    let username = document.querySelector("#username").value;
    let email = document.querySelector("#email").value;
    let phone = document.querySelector("#phone").value;
    let password = document.querySelector("#password").value;

    if(password !== document.querySelector("#submitPassword").value)
    {
        document.querySelector("#submitPassword").className = "error";
        return;
    }

    let response = await fetch("/api/auth/signup",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                username: username,
                email: email,
                phone: phone,
                password: password
            })
        });

    let data = await response.json();

    document.querySelector("#result").innerHTML = "";
    if(response.status === 201)
    {
        document.querySelector("#result").innerHTML = "You are successfully registered";
        window.location.href = '/login';
    }
    else
    {
        data.errors.forEach(err =>
        {
            document.querySelector("#" + err.field).className = "error";
            document.querySelector("#label-" + err.field).innerHTML = err.message;
        });
    }
}

document.querySelector('#username').onfocus = RemoveClassError;
document.querySelector('#email').onfocus = RemoveClassError;
document.querySelector('#phone').onfocus = RemoveClassError;
document.querySelector('#password').onfocus = RemoveClassError;
document.querySelector('#submitPassword').onfocus = RemoveClassError;

function RemoveClassError()
{
    this.previousSibling.previousSibling.innerHTML = "";
    // удаляем индикатор ошибки, т.к. пользователь хочет ввести данные заново
    this.classList.remove('error');
}