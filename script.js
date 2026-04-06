async function solve() {
    const formula = document.getElementById('formula').value;
    const target = document.getElementById('target').value;

    try {
        const response = await fetch(`http://localhost:8080/solve?formula=${encodeURIComponent(formula)}&target=${target}`);
        const data = await response.json();

        const resultsArea = document.querySelector('.main-content main');
        resultsArea.innerHTML = "<h3>WINNING COMBINATIONS</h3>"; 

        if(data.combinations.length === 0) {
            resultsArea.innerHTML += "<p>No combinations found for this target.</p>";
        }

        data.combinations.forEach(combo => {
            resultsArea.innerHTML += `<p style="color: #00ff00; margin: 5px 0;">Found: x=${combo.x}, y=${combo.y}</p>`;
        });
    } catch (error) {
        alert("Check if Java is running! Error: " + error);
    }
}

