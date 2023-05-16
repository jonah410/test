document.getElementById("footprint-form").addEventListener("submit", (event) => {
    event.preventDefault();

    const glycerin = parseFloat(document.getElementById("glycerin").value);
    const dehydratorTime = parseFloat(document.getElementById("dehydrator-time").value);
    const starch = parseFloat(document.getElementById("starch").value);
    const guarGum = parseFloat(document.getElementById("guar-gum").value);
    const carrageenan = parseFloat(document.getElementById("carrageenan").value);
    
    const glycerinFP = 4.5;
    const dehydratorFP = 2.8;
    const starchFP = 2;
    const guarGumFP = 3.1;
    const carrageenanFP = 2.1;
    const fossilFuelFootprint = 2.5; // Using an average value for fossil fuel-based plastic
    
    const creAlgaeFootprint = (
        glycerin * glycerinFP / 1000 +
        dehydratorTime * dehydratorFP / 24 +
        starch * starchFP / 1000 +
        guarGum * guarGumFP / 1000 +
        carrageenan * carrageenanFP / 1000
    ).toFixed(2);
    
    document.getElementById("cre-algae-footprint").textContent = creAlgaeFootprint;
    document.getElementById("fossil-fuel-footprint").textContent = fossilFuelFootprint;
    
    document.getElementById("results").classList.remove("hidden");

    // Display a random fun fact
    const funFacts = [
        "Did you know? CreAlgae sources its algae from Boneyard Creek, a local waterway that runs through the heart of Urbana-Champaign.",
        "Did you know? More than 380 million tonnes of plastic waste is produced annually. With 22% being mismanaged, a small 9% of plastic waste is actually properly recycled. CreAlgae's plastic is biodegradable, ensuring that it will never end up becoming harmful waste.",
        "Did you know? Per annum, there are 8 million pieces of plastic that make their way into America's bodies of water. By providing a biodegradable bioplastic, CreAlgae ensures that our plastic will never be among them.",
        "Did you know? Eutrophication is the process by which algae absorbs minerals from the surrounding water. During this process, algae blooms expand; the larger they get, the more of a risk they pose to the health of the surrounding agriculture.",
        // Add more fun facts here
    ];

    const randomIndex = Math.floor(Math.random() * funFacts.length);
    const randomFunFact = funFacts[randomIndex];
    const funFactElement = document.getElementById("fun-fact");
    funFactElement.textContent = randomFunFact;
});

    

