document.addEventListener("DOMContentLoaded", () => {
    const productsContainer = document.getElementById("products-container");
    const cartContainer = document.getElementById("cart-container");

    let cart = [];

    // Load products from backend
    fetch('/api/products')
        .then(res => res.json())
        .then(products => {
            products.forEach(product => {
                const productDiv = document.createElement("div");
                productDiv.className = "product";

                productDiv.innerHTML = `
                    <span><strong>${product.name}</strong> - $${product.price} (In stock: ${product.quantity})</span>
                    <input type="number" id="qty-${product.id}" min="1" max="${product.quantity}" value="1">
                    <button onclick="addToCart(${product.id}, '${product.name}', ${product.price}, ${product.quantity})">Add to Cart</button>
                `;

                productsContainer.appendChild(productDiv);
            });
        });

    window.addToCart = function (id, name, price, maxQty) {
        const qty = parseInt(document.getElementById(`qty-${id}`).value);
        if (qty < 1 || qty > maxQty) {
            alert("Invalid quantity");
            return;
        }

        const existing = cart.find(item => item.id === id);
        if (existing) {
            existing.quantity += qty;
        } else {
            cart.push({ id, name, price, quantity: qty });
        }

        renderCart();
    };

    function renderCart() {
        cartContainer.innerHTML = "";

        if (cart.length === 0) {
            cartContainer.innerHTML = "<p>Your cart is empty.</p>";
            return;
        }

        cart.forEach(item => {
            const cartItem = document.createElement("div");
            cartItem.className = "cart-item";
            cartItem.innerHTML = `
                <span>${item.name}</span>
                <span>Qty: ${item.quantity}</span>
                <span>Total: $${(item.quantity * item.price).toFixed(2)}</span>
            `;
            cartContainer.appendChild(cartItem);
        });
    }
});
