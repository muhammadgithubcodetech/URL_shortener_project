document.addEventListener('DOMContentLoaded', () => {
    const urlForm = document.getElementById('urlForm');
    const longUrlInput = document.getElementById('longUrl');
    const resultDiv = document.getElementById('result');
    const shortUrlAnchor = document.getElementById('shortUrl');
    const copyButton = document.getElementById('copyButton');

    const apiBaseUrl = 'http://localhost:8080';

    urlForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const longUrl = longUrlInput.value.trim();

        if (!longUrl) {
            alert('Please enter a valid URL.');
            return;
        }

        try {
            const response = await fetch(`${apiBaseUrl}/api/shorten`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ longUrl }),  // <-- Send as JSON object now
            });

            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }

            const shortUrl = await response.text();

            // Show the shortened URL
            shortUrlAnchor.href = shortUrl;
            shortUrlAnchor.textContent = shortUrl;
            resultDiv.classList.remove('hidden');
        } catch (error) {
            console.error('Error:', error);
            alert('Error: ' + error.message);
        }
    });

    copyButton.addEventListener('click', async () => {
        try {
            await navigator.clipboard.writeText(shortUrlAnchor.href);
            copyButton.textContent = 'Copied!';
            setTimeout(() => {
                copyButton.textContent = 'Copy';
            }, 2000);
        } catch (error) {
            console.error('Copy failed:', error);
            alert('Failed to copy: ' + error.message);
        }
    });
});
