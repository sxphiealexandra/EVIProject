async function redirectToEditLoader() {
        const id = document.getElementById("animalId").value.trim();
        const errorBox = document.getElementById("errorBox");
        errorBox.style.display = "none";
        errorBox.textContent = "";

        if (!id || parseInt(id) <= 0) {
          errorBox.style.display = "block";
          errorBox.textContent = "❗ Bitte eine gültige ID eingeben.";
          return;
        }

        try {
          const res = await fetch(`/animal-full/${id}`);
          console.log("📦 Statuscode:", res.status);

          if (!res.ok) {
            if (res.status === 404) {
              errorBox.style.display = "block";
              errorBox.textContent = "❌ Tier mit dieser ID existiert nicht.";
            } else {
              errorBox.style.display = "block";
              errorBox.textContent = `⚠️ Serverfehler (Status ${res.status})`;
            }
            return;
          }

          const data = await res.json();
          if (data && data.gender !== undefined && data.gender !== null) {
            window.location.href = `edit-loader.html?id=${id}`;
          } else {
            errorBox.style.display = "block";
            errorBox.textContent = "⚠️ Datenformat ungültig.";
          }
		  
        } catch (e) {
          console.error("💥 Fehler beim Abrufen:", e);
          errorBox.style.display = "block";
          errorBox.textContent = "❌ Netzwerkfehler beim Abrufen der Tierdaten.";
        }
      }