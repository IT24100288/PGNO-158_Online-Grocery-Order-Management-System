function selectTab(btn) {
    const tabs = document.querySelectorAll(".tab-btn");
    tabs.forEach(tab => tab.classList.remove("active-tab"));
    btn.classList.add("active-tab");

    document.getElementById("paymentMethod").value = btn.innerText.trim();
}
function selectTab(btn) {
    document.querySelectorAll('.tab-btn').forEach(b => b.classList.remove('active-tab'));
    btn.classList.add('active-tab');
    document.getElementById('paymentMethod').value = btn.innerText;
}
