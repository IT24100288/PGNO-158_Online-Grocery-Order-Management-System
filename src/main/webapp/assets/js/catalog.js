const activePage = window.location.href;
const btnEl = document.querySelectorAll(".cat-item a");
btnEl.forEach(btnEl => {
    if(btnEl.href.includes(`${activePage}`)){
        btnEl.classList.add("active");
    }
});

