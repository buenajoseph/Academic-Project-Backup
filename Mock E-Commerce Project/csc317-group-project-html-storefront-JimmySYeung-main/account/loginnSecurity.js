window.addEventListener('load', () => {



    const fname = sessionStorage.getItem('FNAME');
    const lname = sessionStorage.getItem('LNAME');
    const email = sessionStorage.getItem('EMAIL');
    const address = sessionStorage.getItem('ADDRESS');
    const city = sessionStorage.getItem('CITY');
    const state = sessionStorage.getItem('STATEW');
    const zip = sessionStorage.getItem('ZIP');
    const username = sessionStorage.getItem('USERNAME');

    document.getElementById('result-fname').innerHTML = fname;
    document.getElementById('result-lname').innerHTML = lname;
    document.getElementById('result-email').innerHTML = email;
    document.getElementById('result-address').innerHTML = address;
    document.getElementById('result-city').innerHTML = city;
    document.getElementById('result-state').innerHTML = state;
    document.getElementById('result-zip').innerHTML = zip;
    document.getElementById('result-username').innerHTML = username;


})
