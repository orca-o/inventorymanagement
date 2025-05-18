document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8095/api/vendors/get") // GET vendors
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
                    <td>
                        <button onclick="updateVendor(${vendor.id}, '${vendor.vendorName}', '${vendor.contactNumber}', '${vendor.email}', '${vendor.address}')">Update</button>
                        <button onclick="deleteVendor(${vendor.id})" style="color: red;">Delete</button>
                    </td>
                `;

                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching vendors:", error));
});

// Redirect to update page with vendor details
function updateVendor(id, name, contact, email, address) {
    window.location.href = `/update?id=${id}&vendorName=${name}&contactNumber=${contact}&email=${email}&address=${address}`;
}

// Delete vendor by ID
function deleteVendor(id) {
    if (confirm("Are you sure you want to delete this vendor?")) {
        fetch(`http://localhost:8095/api/vendors/delete/${id}`, {
            method: "DELETE"
        })
            .then(response => response.text())
            .then(message => {
                alert(message); // Show delete confirmation
                location.reload(); // Refresh vendor list
            })
            .catch(error => console.error("Error deleting vendor:", error));
    }
}
