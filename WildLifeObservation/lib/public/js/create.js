let currentGenusId = null; //Merkt sich aktuelle GenusID


//Formular wird vorbereitet und Tier wird erstellt
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

  if (currentGenusId) {
    animalData.genusId = currentGenusId;
  }

  try {
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
    currentGenusId = null;
  } catch (err) {
    alert("❌ " + err.message);
  }
});

//sucht nach bestehnder Gattung anhand lateinischem Namen und befüllt alle Felder automatisch wenn Tier besteht
async function searchGenus() {
  const latinName = document.getElementById("latinDesignation").value.trim().toLowerCase();
  if (!latinName) {
    alert("Bitte lateinischen Namen eingeben.");
    return;
  }

  try {
    const res = await fetch('/genus');
    const allGenus = await res.json();

    const found = allGenus.find(g => g.latinDesignation.toLowerCase() === latinName);

    if (found) {
      currentGenusId = found.id;
      const form = document.getElementById("createForm");
      form.designation.value = found.designation;
      form.description.value = found.description;
      form.family.value = found.family;
      form.diet.value = found.diet;
      form.reproductiveStrategy.value = found.reproductiveStrategy;
      form.activeCycle.value = found.activeCycle;
      form.socialStructure.value = found.socialStructure;
      form.lifeSpan.value = found.lifeSpan;
      alert("✅ Vorhandene Gattung gefunden und Felder ausgefüllt.");
    } else {
      currentGenusId = null;
      alert("⚠️ Keine bestehende Gattung mit diesem lateinischen Namen gefunden.");
    }
  } catch (err) {
    alert("❌ Fehler bei der Genus-Suche.");
  }
}


//sucht nach bestehnder Location anhand short title und befüllt Felder autoatisch wenn Location besteht 
async function searchLocation() {
  const title = document.getElementById("locationShortTitle").value.trim().toLowerCase();
  if (!title) {
    alert("Bitte Ortstitel eingeben.");
    return;
  }

  try {
    const res = await fetch('/location');
    const allLocations = await res.json();

    const found = allLocations.find(loc => loc.shortTitle.toLowerCase() === title);

    if (found) {
      currentLocationId = found.lNr;
      document.getElementById("locationDescription").value = found.description;
      alert("✅ Ort gefunden und Beschreibung ausgefüllt.");
    } else {
      currentLocationId = null;
      alert("⚠️ Kein Ort mit diesem Titel gefunden. Es wird eine neue Location erstellt.");
    }
  } catch (err) {
    console.error("Fehler bei fetch oder Verarbeitung:", err);
    alert("❌ Fehler bei Ortssuche.");
  }
}

