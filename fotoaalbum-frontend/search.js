document.addEventListener("DOMContentLoaded", function () {
  const searchButton = document.getElementById("searchBtn");
  searchButton.addEventListener("click", searchPhotos);
});

function searchPhotos() {
  const searchPhoto = document.getElementById("searchBar").value.toLowerCase();
  fetch(`http://localhost:8080/api/v1/photos`)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Failed to fetch photos");
      }
      return response.json();
    })
    .then((data) => {
      const filteredPhotos = data.filter((photo) =>
        photo.title.toLowerCase().includes(searchPhoto)
      );
      updatePhotoList(filteredPhotos);
    })
    .catch((error) => {
      console.error("An unexpected error occurred:", error);
    });
}

function updatePhotoList(data) {
  const root = document.getElementById("root");

  if (root) {
    let content = '<div class="row">';
    data.forEach((element) => {
      content += '<div class="col-3 mb-4">';
      content += generateCard(element);
      content += "</div>";
    });
    content += "</div>";
    root.innerHTML = content;
  } else {
    console.error("Root element not found");
  }
}

function generateCard(element) {
  return `
      <div class="card shadow h-100">
        <img src="${
          element.url
        }" class="card-img-top" style="height: 250px;" alt="${element.title}">
        <div class="card-body">
          <h5 class="card-title">${element.title}</h5>
          <p class="card-text">${element.description}</p>
          <div class="card-footer">${renderCategories(element.categories)}</div>
        </div>
      </div>
    `;
}
