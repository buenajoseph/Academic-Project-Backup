const products = [{
        name: 'home',
        link: "../index.html"
    },
    {
        name: 'account',
        link: "../account.html"

    },
    {
        name: 'company information',
        link: "../companyinfo.html"

    },
    {
        name: 'faq',
        link: "../faq.html"

    },
    {
        name: 'login help',
        link: "../login-help.html"

    },
    {
        name: 'login',
        link: "../login.html"

    },
    {
        name: 'manga',
        link: "../manga.html"

    },
    {
        name: 'text books',
        link: "../textbooks.html"

    },
    {
        name: 'toys',
        link: "../toys.html"

    },
    {
        name: 'american dream',
        link: "AmericanDream.html"

    },
    {
        name: 'izuku midoriya',
        link: "IzukuMidoriya.html"

    },
    {
        name: 'mcflarlane',
        link: "McFarlane.html"

    },
    {
        name: 'dead pool pikachu',
        link: "deadpoolpikachu.html"

    },
    {
        name: 'doggy coin',
        link: "doggycoin.html"

    },
    {
        name: 'hero academia vol 1',
        link: "heroacademiavol1.html"

    },
    {
        name: 'intro to java',
        link: "introtojava.html"

    },
    {
        name: 'intro to physical anthropology',
        link: "introtophysicalanthropology.html"

    },
    {
        name: 'intro to sociology',
        link: "introtosociology.html"

    },
    {
        name: 'intro to python',
        link: "python.html"

    },
    {
        name: 'white collar crime',
        link: "whitecollarcrimebook.html"

    },
    {
        name: 'website playground',
        link: "websiteplayground.html"

    }
];

const list = document.getElementById('list');

function setList(group) {
    clearList();
    for (const person of group) {
        var item = document.createElement('a');
        item.href = person.link;
        item.classList.add('list-group-item');
        const text = document.createTextNode(person.name);
        item.appendChild(text);
        list.appendChild(item);


    }
    if (group.length === 0) {
        setNoResults();
    }
}


function clearList() {
    while (list.firstChild) {
        list.removeChild(list.firstChild);
    }


}

function setNoResults() {
    const item = document.createElement('li');
    item.classList.add('list-group-item');
    const text = document.createTextNode("No results found");
    item.appendChild(text);
    list.appendChild(item);
}

function getRelevancy(value, searchTerm) {
    if (value === searchTerm) {
        return 2;
    } else if (value.startsWith(searchTerm)) {
        return 1;
    } else if (value.includes(searchTerm)) {
        return 0;
    }


}


const searchInput = document.getElementById('search');

searchInput.addEventListener('input', (event) => {
    let value = event.target.value;
    if (value && value.trim().length > 0) {
        value = value.trim().toLowerCase();
        setList(products.filter(person => {
            return person.name.includes(value);
        }).sort((personA, personB) => {
            return getRelevancy(personB.name, value) - getRelevancy(personA.name, value);

        }));

    } else {
        clearList();
    }
});