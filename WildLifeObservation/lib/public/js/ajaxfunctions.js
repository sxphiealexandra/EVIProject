// === Tier mit allen Details laden (Animal + Genus + Observation + Location) ===
async function fetchAnimalById(id) {
  const response = await fetch(`/animal-full/${id}`); // ✅ angepasst
  if (!response.ok) {
    throw new Error(`Tier mit ID ${id} nicht gefunden`);
  }
  return await response.json();
}

// === Tier mit allen Details aktualisieren ===
async function updateAnimal(id, animalData) {
  const response = await fetch(`/animal-full/${id}`, { // ✅ angepasst
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

// === Tier mit Genus, Location, Observation erstellen ===
async function createAnimal(animalData) {
  const response = await fetch('/animal-full', { // ✅ angepasst
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

// === Tier inkl. abhängiger Daten löschen ===
async function deleteAnimal(id) {
  const response = await fetch(`/animal-full/${id}`, { // ✅ angepasst
    method: 'DELETE'
  });

  if (!response.ok) {
    throw new Error("Tier konnte nicht gelöscht werden");
  }
}
