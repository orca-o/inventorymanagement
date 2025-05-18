document.addEventListener("DOMContentLoaded", () => {
    const productTableBody = document.querySelector("#productTable tbody");

    // Fetch products from the API
    fetch('/api/products')
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch products");
            return response.json();
        })
        .then(products => {
            // Clear any previous content in the table
            productTableBody.innerHTML = '';

            // Loop through the products and insert them into the table
            products.forEach(product => {
                const row = document.createElement('tr');

                // Create table cells for each product's data
                const nameCell = document.createElement('td');
                nameCell.textContent = product.name;

                const descriptionCell = document.createElement('td');
                descriptionCell.textContent = product.description;

                const quantityCell = document.createElement('td');
                quantityCell.textContent = product.quantity;

                const priceCell = document.createElement('td');
                priceCell.textContent = `$${product.price.toFixed(2)}`;

                // Append the cells to the row
                row.appendChild(nameCell);
                row.appendChild(descriptionCell);
                row.appendChild(quantityCell);
                row.appendChild(priceCell);

                // Append the row to the table body
                productTableBody.appendChild(row);
            });
        })
        .catch(err => {
            const errorRow = document.createElement('tr');
            const errorCell = document.createElement('td');
            errorCell.colSpan = 4;
            errorCell.textContent = 'Error loading products: ' + err.message;
            errorRow.appendChild(errorCell);
            productTableBody.appendChild(errorRow);
            console.error("Product fetch error:", err);
        });
});
