//get copyright date
const date = new Date();
const year = date.getFullYear();
const spanEl = document.getElementById("copy-year");
spanEl.innerHTML = year;