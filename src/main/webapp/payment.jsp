<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Payment</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/paystyle.css">
</head>
<body class="bg-[#1F1D2B] text-white font-sans p-6">
<div class="max-w-md mx-auto bg-[#252836] rounded-2xl p-6 shadow-lg">
    <h2 class="text-xl font-bold mb-1">Payment</h2>
    <p class="text-sm text-gray-400 mb-4">3 payment method available</p>

    <!-- Payment Method -->
    <div class="mb-4">
        <label class="block text-sm font-medium mb-2">Payment Method</label>
        <div class="grid grid-cols-3 gap-2">
            <button class="tab-btn active-tab" onclick="selectTab(this)">Credit Card</button>
            <button class="tab-btn" onclick="selectTab(this)">Debit Card</button>
            <button class="tab-btn" onclick="selectTab(this)">Cash</button>
        </div>
    </div>

    <form action="conform.jsp" method="post">
        <input type="hidden" name="paymentMethod" id="paymentMethod" value="Credit Card">
        <input type="hidden" name="subtotal" value="21.03">
        <div class="mb-3">
            <label class="text-sm block mb-1">Cardholder Name</label>
            <input type="text" name="cardholder" placeholder="Levi Ackerman" class="form-input">
        </div>

        <div class="mb-3">
            <label class="text-sm block mb-1">Card Number</label>
            <input type="text" name="cardnumber" placeholder="2564 1421 0897 1244" class="form-input">
        </div>

        <div class="grid grid-cols-2 gap-4 mb-3">
            <div>
                <label class="text-sm block mb-1">Expiration Date</label>
                <input type="text" name="expiry" placeholder="02/2022" class="form-input">
            </div>
            <div>
                <label class="text-sm block mb-1">CVV</label>
                <input type="password" name="cvv" placeholder="•••" class="form-input">
            </div>
        </div>

        <div class="grid grid-cols-2 gap-4 mb-3">
            <div>
                <label class="text-sm block mb-1">Order Type</label>
                <select name="ordertype" class="form-input">
                    <option>Take Away</option>
                    <option>Delivery</option>
                </select>
            </div>
            <div>
                <label class="text-sm block mb-1">Order No.</label>
                <input type="text" name="table" placeholder="140" class="form-input">
            </div>
        </div>
        <div class="mt-6">
            <label class="text-sm block mb-1">Sub Total </label>
            <input type="text" name="subtotalField" placeholder="$****" class="form-input">
            <div class="text-lg font-semibold">Subtotal: <span id="payment-subtotal">$0</span></div>
        </div>

        <!-- Buttons -->
        <div class="flex justify-between mt-6">
            <button type="button" onclick="history.back()" class="px-4 py-2 border border-[#EA7C69] text-[#EA7C69] rounded-lg hover:bg-[#2d2d3a]">
                Cancel
            </button>
            <button type="submit" class="px-4 py-2 border border-[#EA7C69] text-[#EA7C69] rounded-lg hover:bg-[#2d2d3a]">
                Confirm Payment
            </button>


        </div>
    </form>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const subtotal = localStorage.getItem("subtotal");
        if (subtotal) {
            document.getElementById("payment-subtotal").innerText = `$${subtotal}`;
        }
    });
</script>
<script src="js/payment.js"></script>
</body>
</html>
