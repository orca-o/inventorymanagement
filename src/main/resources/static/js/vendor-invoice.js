document.addEventListener("DOMContentLoaded", () => {
    fetch('/api/invoices')
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to load invoices.");
            }
            return response.json();
        })
        .then(invoices => {
            const tbody = document.querySelector("#invoiceTable tbody");
            invoices.forEach(invoice => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${invoice.id}</td>
                    <td>${invoice.date}</td>
                    <td>$${invoice.amount.toFixed(2)}</td>
                    <td>${invoice.status}</td>
                    <td>${invoice.invoiceNumber}</td>
                `;

                tbody.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Error fetching invoices:", error);
            const tbody = document.querySelector("#invoiceTable tbody");
            tbody.innerHTML = `
                <tr>
                    <td colspan="5" style="color: red; text-align: center;">Unable to fetch invoice data.</td>
                </tr>
            `;
        });
});
