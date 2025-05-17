document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("vendorForm");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // Prevent default form submission

        const vendorData = {
            vendorName: document.getElementById("vendorName").value,
            contactNumber: document.getElementById("contactNumber").value,
            email: document.getElementById("email").value,
            address: document.getElementById("address").value
        };

        fetch("/api/vendors/add", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(vendorData)
        })
            .then(response => response.text()) // Get response as text (string)
            .then(message => {
                document.getElementById("successMessage").innerText = message;

                // Redirect after delay to allow message display
                setTimeout(() => {
                    window.location.href = "/vendors";
                }, 2000);
            })
            .catch(error => {
                console.error("Error:", error);
                document.getElementById("successMessage").innerText = "Error adding vendor.";
            });
    });
});
