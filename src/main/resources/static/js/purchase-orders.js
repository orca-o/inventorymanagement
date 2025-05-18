async function fetchPurchaseOrders() {
    try {
        const response = await fetch('/api/purchaseorders');
        const data = await response.json();
        const tbody = document.querySelector('#poTable tbody');
        tbody.innerHTML = '';

        data.forEach(po => {
            const row = document.createElement('tr');

            row.innerHTML = `
                <td>${po.id}</td>
                <td>${po.vendor}</td>
                <td>${po.date}</td>
                <td>${po.item}</td>
                <td>${po.amount}</td>
                <td>${po.quantity}</td>
                <td>${po.status}</td>
                <td>${po.invoice && po.invoice.invoiceNumber ? po.invoice.invoiceNumber : 'N/A'}</td>
                <td>
                    ${po.status === 'pending' ? `<button class="close-btn" onclick="updateStatus(${po.id}, 'closed')">Close</button>` : ''}
                    ${po.status !== 'closed' ? `<button class="cancel-btn" onclick="updateStatus(${po.id}, 'cancelled')">Cancel</button>` : ''}
                </td>
            `;

            tbody.appendChild(row);
        });
    } catch (error) {
        console.error('Error fetching purchase orders:', error);
        alert('Failed to load purchase orders.');
    }
}

async function updateStatus(orderId, newStatus) {
    try {
        const response = await fetch(`/api/purchaseorders/${orderId}/status?status=${newStatus}`, {
            method: 'PUT'
        });

        if (response.ok) {
            alert(`Order ${orderId} status updated to "${newStatus}".`);
            fetchPurchaseOrders();
        } else {
            alert('Failed to update status.');
        }
    } catch (error) {
        console.error('Error updating status:', error);
        alert('Error updating status.');
    }
}

document.addEventListener('DOMContentLoaded', fetchPurchaseOrders);
