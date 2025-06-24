//Holt Tier mit allen Details laden (Animal + Genus + Observation + Location)
async function fetchAnimalById(id) {
  const response = await fetch(`/animal-full/${id}`); 
  if (!response.ok) {
    throw new Error(`Tier mit ID ${id} nicht gefunden`);
  }
  return await response.json();
}

// Tier mit allen Details aktualisieren
async function updateAnimal(id, animalData) {
  const response = await fetch(`/animal-full/${id}`, { 
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(animalData)
  });

  if (!response.ok) {
    throw new Error("Tier konnte nicht aktualisiert werden");
  }
}

// Tier mit Genus, Location, Observation erstellen
async function createAnimal(animalData) {
  const response = await fetch('/animal-full', { 
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(animalData)
  });

  if (!response.ok) {
    throw new Error("Tier konnte nicht erstellt werden");
  }
}

// Tier inkl. abhängiger Daten löschen
async function deleteAnimal(id) {
  const response = await fetch(`/animal-full/${id}`, { 
    method: 'DELETE'
  });

  if (!response.ok) {
    throw new Error("Tier konnte nicht gelöscht werden");
  }

}

//Lädt Bild hoch, dass zu einem Tier (Tiereintrag) gehört
async function uploadImage(animalId, file) {
  const formData = new FormData();
  formData.append("file", file);

  const response = await fetch(`/animal/${animalId}/image`, {
    method: "POST",
    body: formData
  });

  if (!response.ok) {
    throw new Error("Bild konnte nicht hochgeladen werden");
  }
}

//Löscht Bild eines Tiers
async function deleteImage(animalId) {
  const response = await fetch(`/animal/${animalId}/image`, {
    method: "DELETE"
  });

  if (!response.ok) {
    throw new Error("Bild konnte nicht gelöscht werden");
  }
}


