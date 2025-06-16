async function loadAnimals() {
  try {
    const response = await fetch("/animal");
    const animals = await response.json();

    const tbody = document.getElementById("animalTableBody");

    for (const animal of animals) {
      const res = await fetch(`/animal-full/${animal.id}`);
      if (!res.ok) continue;
      const full = await res.json();

      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${animal.id}</td>
        <td>${full.designation || "-"}</td>
        <td>${full.latinDesignation || "-"}</td>
        <td>${animal.gender}</td>
        <td>${animal.estimatedAge}</td>
        <td>${animal.estimatedSize}</td>
        <td>${animal.estimatedWeight}</td>
        <td>${full.locationShortTitle || "-"}</td>
        <td>${full.date || "-"}</td>
        <td class="action-buttons">
          <a href="edit-loader.html?id=${animal.id}" title="Bearbeiten">✎</a>
        </td>
        <td class="action-buttons">
          <button onclick="deleteAnimal(${animal.id})" title="Löschen">🗑️</button>
        </td>
      `;
      tbody.appendChild(row);
    }
  } catch (err) {
    console.error("❌ Fehler beim Laden der Tabelle:", err);
    alert("⚠️ Fehler beim Laden der Tierdaten.");
  }
}

async function deleteAnimal(id) {
  const confirmDelete = confirm(`Möchtest du Tier mit ID ${id} wirklich löschen?`);
  if (!confirmDelete) return;

  try {
    const res = await fetch(`/animal-full/${id}`, {
      method: "DELETE"
    });

    if (res.ok) {
      alert("✅ Tier gelöscht.");
      location.reload();
    } else {
      const msg = await res.text();
      alert("❌ Fehler beim Löschen: " + msg);
    }
  } catch (err) {
    alert("❌ Netzwerkfehler: " + err.message);
  }
}

loadAnimals();
