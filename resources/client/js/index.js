function pageLoad() {

    let stockHTML = '<table id="table2" border="1" bordercolor="black" >' +  //w 519 l 115
        '<tr>' +
        '<th>Id</th>' +
        '<th>Brand</th>' +
        '<th>Name</th>' +
        '<th>Price</th>' +
        '<th>Quantity</th>' +
        '<th>Type</th>' +
        '<th>Exclusive</th>' +
        '</tr>';

    fetch('/stock/list', {method: 'get'}).then(response => response.json()).then(items => {

        for (let stock of items) {
            debugger;
            stockHTML += `<tr>` +
                `<td>${stock.StockID}</td>` +
                `<td>${stock.Brand}</td>` +
                `<td>${stock.StockName}</td>` +
                `<td>${stock.Price}</td>` +
                `<td>${stock.Quantity}</td>` +
                `<td>${stock.Type}</td>` +
                `<td>${stock.Exclusive}</td>` +


                `</tr>`;
        }

            stockHTML += '</table>';

            document.getElementById("listDiv").innerHTML = stockHTML;

        let editButtons = document.getElementsByClassName("AStockButton");
        for (let button of editButtons) {
            button.addEventListener("click", editStock);
        }

        let deleteButtons = document.getElementsByClassName("deleteStockButton");
        for (let button of deleteButtons) {
            button.addEventListener("click", deleteStock);
        }

        document.getElementById("saveButton").addEventListener("click", saveEditStock);
        document.getElementById("cancelButton").addEventListener("click", cancelEditStock);

    });

}

function editStock(event) {
    debugger
    const StockID = event.target.getAttribute("data-StockID");

    if (StockID === null) {

        document.getElementById("editHeading").innerHTML = 'Add Stock Item:';

        document.getElementById("stockID").value = '';
        document.getElementById("brand").value = '';
        document.getElementById("stockName").value = '';
        document.getElementById("price").value = '';
        document.getElementById("quantity").value = '';
        document.getElementById("type").value = '';
        document.getElementById("exclusive").value = '';

        document.getElementById("listDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    } else {

        fetch('/Stock/lookUp/' + StockID, {method: 'get'}
        ).then(response => response.json()
        ).then(stock => {

            if (stock.hasOwnProperty('error')) {
                alert(stock.error);
            } else {

                document.getElementById("editHeading").innerHTML = 'Editing ' + stock.Brand + ':';

                document.getElementById("StockID").value = stock.StockID;
                document.getElementById("Brand").value = stock.Brand;
                document.getElementById("StockName").value = stock.StockName;
                document.getElementById("Price").value = stock.Price;
                document.getElementById("Quantity").value = stock.Quantity;
                document.getElementById("Type").value = stock.Type;
                document.getElementById("Exclusive").value = stock.Exclusive;

                document.getElementById("listDiv").style.display = 'none';
                document.getElementById("editDiv").style.display = 'block';

            }
        });
    }
}

function saveEditStock(event) {

    event.preventDefault();

    if (document.getElementById("brand").value.trim() === '') {
        alert("Please provide a brand.");
        return;
    }

    if (document.getElementById("stockName").value.trim() === '') {
        alert("Please provide a stock name.");
        return;
    }

    if (document.getElementById("price").value.trim() === '') {
        alert("Please provide a price.");
        return;
    }

    if (document.getElementById("quantity").value.trim() === '') {
        alert("Please provide a quantity.");
        return;
    }

    if (document.getElementById("type").value.trim() === '') {
        alert("Please provide a Stock Type(Footwear/Clothing).");
        return;
    }

    if (document.getElementById("exclusive").value.trim() === '') {
    alert("Please provide information about whether the stock is exclusive.");
    return;
}
const StockID = document.getElementById("stockID").value;
const form = document.getElementById("stockForm");
const formData = new FormData(form);

let apiPath = '';
if (StockID === '') {
    apiPath = '/stock/add';
} else{
    apiPath = '/stock/updatePrice';
}

fetch(apiPath, {method: 'post', body: formData}
).then(response => response.json()
).then(responseData => {

    if (responseData.hasOwnProperty('error')) {
        alert(responseData.error);
    } else {
        document.getElementById("listDiv").style.display = 'block';
        document.getElementById("editDiv").style.display = 'none';
        pageLoad();
    }
});
}

function cancelEditStock(event) {

    event.preventDefault();

    document.getElementById("listDiv").style.display = 'block';
    document.getElementById("editDiv").style.display = 'none';

}


function deleteStock(event) {

    const ok = confirm("Are you sure?");

    if (ok === true) {

        let StockID = event.target.getAttribute("data-StockID");
        let formData = new FormData();
        formData.append("StockID", StockID);

        fetch('/stock/delete', {method: 'post', body: formData}
        ).then(response => response.json()
        ).then(responseData => {

                if (responseData.hasOwnProperty('error')) {
                    alert(responseData.error);
                } else {
                    pageLoad();
                }
            }
        );
    }
}


