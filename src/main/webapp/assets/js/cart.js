document.addEventListener("DOMContentLoaded", () => {

    //add to cart request
    document.querySelectorAll('.js-add-to-cart').forEach(addToCart => {
        addToCart.addEventListener('click', async (event) => {
            const productId = event.currentTarget.dataset.id;
            try{
                const response = await fetch(`CartServlet?id=${productId}&action=add`, {
                    method: "GET",
                })

                if (response.ok) {
                    location.reload();
                }else{
                    alert("Error adding item to cart!");
                }

            }catch(err){
                console.error(err.message);
            }
        });
    });

    //buy now request
    document.querySelectorAll('.js-buy-now-btn').forEach( buyNow => {
        buyNow.addEventListener('click', async (event) => {
            const productId = event.currentTarget.dataset.id;
            try{
                const response = await fetch(`CartServlet?id=${productId}&action=add`, {
                    method: "GET",
                })

                if (response.ok) {
                    location.href = "cart.jsp";
                }else{
                    console.error("Error adding item to cart!");
                }

            }catch(err){
                console.error(err.message);
            }
        });
    });

    //remove item cart request
    document.querySelectorAll('.js-remove-item-btn').forEach( removeItem => {
        removeItem.addEventListener('click', async (event) => {
            const productId = event.currentTarget.dataset.id;
            try{
                const response = await fetch(`CartServlet?id=${productId}&action=remove`, {
                    method: "GET",
                });
                if (response.ok) {
                    location.reload();
                }
            }catch(err){
                console.error(err.message);
            }
        });
    });

    //increase cart item request
    document.querySelectorAll('.js-item-increase').forEach( increaseItem => {
        increaseItem.addEventListener('click', async (event) => {
            const productId = event.currentTarget.dataset.id;
            try{
                const response = await fetch(`CartServlet?id=${productId}&action=increase`, {
                    method: "GET",
                });
                if (response.ok) {
                    location.reload();
                }
            }catch(err){
                console.error(err.message);
            }
        });
    });

    //decrease cart item request
    document.querySelectorAll('.js-item-decrease').forEach( decreaseItem => {
        decreaseItem.addEventListener('click', async (event) => {
            const productId = event.currentTarget.dataset.id;
            try{
                const response = await fetch(`CartServlet?id=${productId}&action=decrease`, {
                    method: "GET",
                });
                if (response.ok) {
                    location.reload();
                }
            }catch(err){
                console.error(err.message);
            }
        });
    });
})

