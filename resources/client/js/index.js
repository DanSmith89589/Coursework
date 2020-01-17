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



        });

}

function editFruit(event) {

    const id = event.target.getAttribute("data-id");

    if (id === null) {

        document.getElementById("AddStock").innerHTML = 'Add Stock Item:';

        document.getElementById("StockID").value = '';
        document.getElementById("Brand").value = '';
        document.getElementById("StockName").value = '';
        document.getElementById("Price").value = '';
        document.getElementById("Quantity").value = '';
        document.getElementById("Type").value = '';
        document.getElementById("Exclusive").value = '';

        document.getElementById("listDiv").style.display = 'none';
        document.getElementById("editDiv").style.display = 'block';

    } else {

        fetch('/Stock/lookUp/' + StockID, {method: 'get'}
        ).then(response => response.json()
        ).then(stock => {

            if (stock.hasOwnProperty('error')) {
                alert(stock.error);
            } else {

                document.getElementById("editHeading").innerHTML = 'Editing ' + stock.StockName + ':';

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



