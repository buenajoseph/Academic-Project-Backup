function handleSubmit () {
    const fname = document.getElementById('fname').value;
    const lname = document.getElementById('lname').value;
    const email = document.getElementById('email').value;
    const address = document.getElementById('address').value;
    const city = document.getElementById('city').value;
    const state = document.getElementById('state').value;
    const zip = document.getElementById('zip').value;
    const username = document.getElementById('username').value;



    sessionStorage.setItem("FNAME", fname);
    sessionStorage.setItem("LNAME", lname);
    sessionStorage.setItem("EMAIL", email);
    sessionStorage.setItem("ADDRESS", address);
    sessionStorage.setItem("CITY", city);
    sessionStorage.setItem("STATE", state);
    sessionStorage.setItem("ZIP", zip);
    sessionStorage.setItem("USERNAME",username);


    return;
}