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