document.getElementById('createForm').addEventListener('submit', async function (event) {
      event.preventDefault();

      const form = event.target;

      const animalData = {
        gender: form.gender.value,
        estimatedAge: parseInt(form.estimatedAge.value),
        estimatedSize: parseFloat(form.estimatedSize.value),
        estimatedWeight: parseFloat(form.estimatedWeight.value),

        latinDesignation: form.latinDesignation.value,
        designation: form.designation.value,
        description: form.description.value,
        family: form.family.value,
        diet: form.diet.value,
        reproductiveStrategy: form.reproductiveStrategy.value,
        activeCycle: form.activeCycle.value,
        socialStructure: form.socialStructure.value,
        lifeSpan: form.lifeSpan.value,

        locationShortTitle: form.locationShortTitle.value,
        locationDescription: form.locationDescription.value,
        date: form.date.value,
        time: form.time.value
      };

      try {
        // Tierdaten senden
        const response = await fetch('/animal-full', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(animalData)
        });

        if (!response.ok) {
          const errorText = await response.text();
          throw new Error("Fehler bei der Erstellung: " + errorText);
        }

        // Falls Bild vorhanden: separat hochladen
        const imageFile = form.image.files[0];
        if (imageFile) {
          const animals = await fetch('/animal');
          const allAnimals = await animals.json();
          const latestAnimal = allAnimals[allAnimals.length - 1];
          const animalId = latestAnimal.id;

          const imageForm = new FormData();
          imageForm.append('file', imageFile);

          const imgResponse = await fetch(`/animal/${animalId}/image`, {
            method: 'POST',
            body: imageForm
          });

          if (!imgResponse.ok) {
            throw new Error("Tier erstellt, aber Bild-Upload fehlgeschlagen.");
          }
        }

        alert("✅ Tier erfolgreich erstellt!");
        form.reset();
      } catch (err) {
        alert("❌ " + err.message);
      }
    });