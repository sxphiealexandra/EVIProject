let allAnimalsFull = []; //Zwischenspeicher für alle vollständigen Tierdaten

//holt alle Tiere und ergänzt Details zu Tieren anhand von animal-full (zugehöriger genus und location)
async function loadAnimals() {
  try {
    const base = await fetch("/animal");
    const animals = await base.json();

    for (const animal of animals) {
      const res = await fetch(`/animal-full/${animal.id}`);
      if (!res.ok) continue;
      const full = await res.json();
      full.id = animal.id;
      allAnimalsFull.push(full);
    }

    renderTable(allAnimalsFull);
  } catch (err) {
    alert("Fehler beim Laden der Daten.");
    console.error(err);
  }
}

//baut tabellenteilen für jedes Tier auf
function renderTable(data) {
  const tbody = document.getElementById("animalTableBody");
  tbody.innerHTML = "";

  for (const a of data) {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${a.id}</td>
	  <td>${a.designation ?? "-"}</td>
	  <td>${a.latinDesignation ?? "-"}</td>
	  <td>${a.gender ?? "-"}</td>
	  <td>${a.estimatedAge ?? "-"}</td>
	  <td>${a.estimatedSize ?? "-"}</td>
	  <td>${a.estimatedWeight ?? "-"}</td>
      <td>${a.locationShortTitle ?? "-"}</td>
      <td>${a.locationDescription ?? "-"}</td>
      <td>${a.date ?? "-"}</td>
      <td>${a.time ?? "-"}</td>
      <td><a href="edit-loader.html?id=${a.id}">✎</a></td>
      <td><button onclick="deleteAnimal(${a.id})">🗑️</button></td>
    `;
    tbody.appendChild(row);
  }
}

//filterfunktion - filtert nach gewähltem Feld (id, deutsche bezeichnung, lat. desig., alter, usw) und Suchbegriff
function applyFilter() {
  const field = document.getElementById("filterField").value;
  const term = document.getElementById("searchInput").value.trim().toLowerCase();

  const filtered = allAnimalsFull.filter(a => {
    const val = (a[field] ?? "").toString().toLowerCase();
    return val.includes(term);
  });

  renderTable(filtered);
}

//durch Eingabetaste Suche/Filter auslösen
document.getElementById("searchInput").addEventListener("keypress", function (e) {
  if (e.key === "Enter") applyFilter();
});

//löscht Tier aus Tabelle mit Bestätigung (geht auf der Seite über Mülleimer Button)
async function deleteAnimal(id) {
  if (!confirm(`Tier mit ID ${id} wirklich löschen?`)) return;

  try {
    const res = await fetch(`/animal-full/${id}`, { method: "DELETE" });
    if (res.ok) {
      alert("✅ Tier gelöscht.");
      location.reload();
    } else {
      alert("❌ Fehler beim Löschen.");
    }
  } catch (err) {
    alert("❌ Netzwerkfehler: " + err.message);
  }
}

loadAnimals(); //Lädt Daten beim ersten Laden der Seite
