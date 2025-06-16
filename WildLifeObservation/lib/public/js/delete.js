document.getElementById("deleteForm").addEventListener("submit", async function(event) {
      event.preventDefault();

      const id = document.getElementById("id").value.trim();
      const messageBox = document.getElementById("message");
      messageBox.textContent = "";
      messageBox.className = "message";

      if (!id || parseInt(id) <= 0) {
        messageBox.textContent = "❗ Ungültige Tier-ID.";
        messageBox.classList.add("error");
        return;
      }

      try {
        const response = await fetch(`/animal-full/${id}`, {
          method: "DELETE"
        });

        if (response.ok) {
          messageBox.textContent = "✅ Tier inklusive Beobachtungen, Ort & Gattung erfolgreich gelöscht.";
          messageBox.classList.add("success");
        } else if (response.status === 404) {
          const text = await response.text();
          messageBox.textContent = `❌ ${text}`;
          messageBox.classList.add("error");
        } else {
          messageBox.textContent = `⚠️ Fehler beim Löschen (Status ${response.status})`;
          messageBox.classList.add("error");
        }
      } catch (err) {
        messageBox.textContent = "❌ Netzwerkfehler: " + err.message;
        messageBox.classList.add("error");
      }
    });