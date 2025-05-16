const items = [
    { id: 1, name: "Spicy seasoned seafood noodles", price: 2.29, quantity: 2, note: "Please, just a little bit spicy only.", img: "path/to/image1.jpg" },
    { id: 2, name: "Salted pasta with mushrooms", price: 2.69, quantity: 1, note: "", img: "path/to/image2.jpg" },
    { id: 3, name: "Spicy instant noodle", price: 3.49, quantity: 3, note: "", img: "path/to/image3.jpg" },
    { id: 4, name: "Healthy noodle with mushrooms", price: 3.29, quantity: 1, note: "", img: "path/to/image4.jpg" }
];

function renderItems() {
    const container = document.getElementById("items-container");
    container.innerHTML = "";

    let subtotal = 0;

    items.forEach((item, index) => {
        const itemTotal = (item.price * item.quantity).toFixed(2);
        subtotal += parseFloat(itemTotal);

        container.innerHTML += `
      <div class="item-card flex items-center justify-between">
        <img src="${item.img}" alt="${item.name}" class="item-img">
        <div class="flex-1 ml-4">
          <div>${item.name}</div>
          <div class="text-sm text-gray-400">$${item.price} x ${item.quantity} = $${itemTotal}</div>
          <input class="mt-1 text-black w-full rounded p-1" type="text" placeholder="Order Note..." value="${item.note}">
        </div>
        <div class="flex flex-col items-end">
          <div class="flex mb-2">
            <button onclick="updateQty(${index}, -1)" class="btn mr-1">-</button>
            <div class="mx-1">${item.quantity}</div>
            <button onclick="updateQty(${index}, 1)" class="btn ml-1">+</button>
          </div>
          <button onclick="removeItem(${index})" class="btn">Delete</button>
        </div>
      </div>
    `;
    });

    document.getElementById("subtotal").innerText = subtotal.toFixed(2);
}

function updateQty(index, change) {
    if (items[index].quantity + change > 0) {
        items[index].quantity += change;
        renderItems();
    }
}

function removeItem(index) {
    items.splice(index, 1);
    renderItems();
}


function goToPayment() {
    const subtotal = document.getElementById("subtotal").innerText;
    localStorage.setItem("subtotal", subtotal);
    window.location.href = "payment.jsp";
}


renderItems();
