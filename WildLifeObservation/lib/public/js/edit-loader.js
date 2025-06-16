const id = new URLSearchParams(window.location.search).get("id");

    async function fetchAnimal() {
      const res = await fetch(`/animal-full/${id}`);
      if (!res.ok) {
        alert("❌ Tierdaten konnten nicht geladen werden.");
        return;
      }
      const data = await res.json();
      for (const key in data) {
        const field = document.querySelector(`[name="${key}"]`);
        if (field) field.value = data[key];
      }

      // Bild laden
      const imgRes = await fetch(`/animal/${id}/image`);
      if (imgRes.ok) {
        const blob = await imgRes.blob();
        const url = URL.createObjectURL(blob);
        document.getElementById("currentImage").src = url;
      }
    }

    function previewImage(event) {
      const file = event.target.files[0];
      const preview = document.getElementById("previewImage");
      if (file) {
        const reader = new FileReader();
        reader.onload = e => {
          preview.src = e.target.result;
          preview.style.display = "block";
        };
        reader.readAsDataURL(file);
      } else {
        preview.src = "";
        preview.style.display = "none";
      }
    }

    async function deleteImage() {
      const confirmDelete = confirm("Bist du sicher, dass du das Bild löschen möchtest?");
      if (!confirmDelete) return;

      try {
        const res = await fetch(`/animal/${id}/image`, { method: "DELETE" });
        if (res.ok) {
          document.getElementById("currentImage").src = "";
          alert("✅ Bild wurde gelöscht.");
        } else {
          alert("❌ Fehler beim Löschen des Bildes.");
        }
      } catch (err) {
        alert("❌ Fehler: " + err.message);
      }
	  
	  console.log("Sende DELETE an /animal/" + id + "/image");

	  const res = await fetch(`/animal/${id}/image`, { method: "DELETE" });
	  console.log("Statuscode: ", res.status);

    }

    document.getElementById("editForm").addEventListener("submit", async function (e) {
      e.preventDefault();
      const form = e.target;

      const body = {};
      new FormData(form).forEach((value, key) => {
        if (key !== "image") {
          body[key] = value;
        }
      });

      body.estimatedAge = parseInt(body.estimatedAge);
      body.estimatedSize = parseFloat(body.estimatedSize);
      body.estimatedWeight = parseFloat(body.estimatedWeight);

      try {
        const res = await fetch(`/animal-full/${id}`, {
          method: "PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(body)
        });

        if (!res.ok) throw new Error(await res.text());

        const imageFile = form.image.files[0];
        if (imageFile) {
          const imageForm = new FormData();
          imageForm.append("file", imageFile);

          const imgRes = await fetch(`/animal/${id}/image`, {
            method: "POST",
            body: imageForm
          });

          if (!imgRes.ok) {
            alert("✅ Tierdaten aktualisiert, aber Bild-Upload fehlgeschlagen.");
            return;
          }
        }

        alert("✅ Tier erfolgreich aktualisiert.");
      } catch (err) {
        alert("❌ Fehler: " + err.message);
      }
    });

    fetchAnimal();