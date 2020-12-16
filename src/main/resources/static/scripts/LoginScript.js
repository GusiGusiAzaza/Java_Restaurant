async function GetToken()
{
    let username = document.querySelector("#username").value;
    let password = document.querySelector("#password").value;

    let response = await fetch("/api/auth/login",
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json', 'Accept': 'application/json'},
            body: JSON.stringify({
                username: username,
                password: password
            })
        });

    let data = await response.json();

    if(response.status === 200)
    {
        SetCookie(data.token);
        localStorage.clear();
        window.location.href = '/';
    }
    else
    {
        document.querySelector("#result").style.color = "#B40000";
        document.querySelector("#result").innerHTML = data.error;
    }
}

function SetCookie(token)
{
    document.cookie = "jwt=" + token;
}