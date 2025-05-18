document.addEventListener("DOMContentLoaded", () => {
    const vendorSelect = document.getElementById("vendor");

    // Fetch vendors from the corrected endpoint
    fetch('/api/vendors/get')
        .then(response => {
            if (!response.ok) throw new Error("Failed to fetch vendors");
            return response.json();
        })
        .then(vendors => {
            vendorSelect.innerHTML = '<option value="">-- Select Vendor --</option>';

            vendors.forEach(vendor => {
                const option = document.createElement("option");
                option.value = vendor.vendorName;         // value to be submitted
                option.textContent = vendor.vendorName;   // visible text
                vendorSelect.appendChild(option);
            });
        })
        .catch(err => {
            vendorSelect.innerHTML = '<option value="">Error loading vendors</option>';
            console.error("Vendor fetch error:", err);
        });

    // Handle form submission
    document.getElementById("purchaseOrderForm").addEventListener("submit", function (event) {
        event.preventDefault();

        // 👇 Get today's date in yyyy-mm-dd format
        const today = new Date().toISOString().split("T")[0];

        const purchaseOrder = {
            vendor: vendorSelect.value,
            item: document.getElementById("item").value,
            quantity: parseInt(document.getElementById("quantity").value),
            amount: parseFloat(document.getElementById("amount").value),
            date: today,           // 👈 Add current date here
            status: "pending"      // optional, backend may set this too
        };

        fetch('/api/purchaseorders/add', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(purchaseOrder)
        })
            .then(response => {
                if (!response.ok) throw new Error("Failed to submit purchase order");
                return response.json();
            })
            .then(data => {
                document.getElementById("responseMessage").textContent = "Purchase order submitted successfully!";
                document.getElementById("purchaseOrderForm").reset();
            })
            .catch(error => {
                document.getElementById("responseMessage").textContent = "Error: " + error.message;
            });
    });
});
