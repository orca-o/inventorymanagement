document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8095/api/vendors/get") // Updated endpoint to GET vendors
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("vendorTable");

            data.forEach(vendor => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${vendor.id}</td>
                    <td>${vendor.vendorName}</td>
                    <td>${vendor.contactNumber}</td>
                    <td>${vendor.email}</td>
                    <td>${vendor.address}</td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching vendors:", error));
});
