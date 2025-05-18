document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("vendorForm");

    // Load vendor details from query parameters
    const urlParams = new URLSearchParams(window.location.search);
    document.getElementById("vendorId").value = urlParams.get("id");
    document.getElementById("vendorName").value = urlParams.get("vendorName");
    document.getElementById("contactNumber").value = urlParams.get("contactNumber");
    document.getElementById("email").value = urlParams.get("email");
    document.getElementById("address").value = urlParams.get("address");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent default form submission

        const vendorId = document.getElementById("vendorId").value;
        const vendorData = {
            id: vendorId,  // Include ID in the request body
            vendorName: document.getElementById("vendorName").value,
            contactNumber: document.getElementById("contactNumber").value,
            email: document.getElementById("email").value,
            address: document.getElementById("address").value
        };

        fetch("/api/vendors/update", {  // Changed URL to match POST mapping
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(vendorData)
        })
            .then(async response => {
                const responseText = await response.text(); // Capture response message
                if (!response.ok) {
                    throw new Error(responseText); // Show backend error
                }
                return responseText;
            })
            .then(message => {
                document.getElementById("successMessage").innerText = message;

                // Redirect after delay to allow message display
                setTimeout(() => {
                    window.location.href = "/vendors"; // Redirect to vendor list
                }, 2000);
            })
            .catch(error => {
                console.error("Error:", error);
                document.getElementById("successMessage").innerText = error.message; // Show backend error
            });
    });
});
