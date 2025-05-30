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
            .then(async response => {
                const responseText = await response.text(); // Capture response message
                if (!response.ok) {
                    throw new Error(responseText); // Use backend error message
                }
                return responseText;
            })
            .then(message => {
                document.getElementById("successMessage").innerText = message;

                // Redirect after delay to allow message display
                setTimeout(() => {
                    window.location.href = "/vendors";
                }, 2000);
            })
            .catch(error => {
                console.error("Error:", error);
                document.getElementById("successMessage").innerText = error.message; // Show backend error
                form.reset(); // Clear the form on error
            });
    });
});
