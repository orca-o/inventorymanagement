function loadPage() {
    const mainContent = document.getElementById('mainContent');
    mainContent.innerHTML = '<h3>Loading...</h3>';

    fetch('/')
        .then(response => {
            if (!response.ok) {
                throw new Error('Page not found');
            }
            return response.text();
        })
        .then(data => {
            mainContent.innerHTML = data;
        })
        .catch(error => {
            mainContent.innerHTML = `<p>Error loading page: ${error.message}</p>`;
        });
}
