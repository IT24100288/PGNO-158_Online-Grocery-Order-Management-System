function addInvoice() {
    const table = document.getElementById("invoiceTable").getElementsByTagName('tbody')[0];
    const row = table.insertRow();
    row.innerHTML = `
        <td>INV-${Math.floor(Math.random() * 10000)}</td>
        <td><input value="New Customer"/></td>
        <td><input value="0.00"/></td>
        <td>${new Date().toLocaleDateString()}</td>
        <td>
            <button onclick="saveRow(this)">Save</button>
            <button onclick="deleteRow(this)">Delete</button>
        </td>`;
}

function saveRow(btn) {
    const row = btn.parentNode.parentNode;
    const inputs = row.getElementsByTagName("input");

    row.cells[1].innerText = inputs[0].value;
    row.cells[2].innerText = `$${parseFloat(inputs[1].value).toFixed(2)}`;
    btn.style.display = 'none';
}

function deleteRow(btn) {
    const row = btn.parentNode.parentNode;
    row.remove();
}
