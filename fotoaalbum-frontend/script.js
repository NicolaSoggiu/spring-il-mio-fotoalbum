// script.js
const apiUrl = "http://localhost:8080/api/v1/photos";
const apiContacts = "http://localhost:8080/api/v1/contacts";
const root = document.getElementById("root");

const renderCategories = (categories) => {
  let content;
  if (categories.length === 0) {
    content = "No categories";
  } else {
    content = '<ul class="list-unstyled">';
    categories.forEach((cat) => {
      content += `<li>${cat.name}</li>`;
    });
    content += "</ul>";
  }
  return content;
};

const renderPhoto = (element) => {
  return generateCard(element);
};

const renderPhotoList = (data) => {
  let content;
  if (data.length > 0) {
    content = '<div class="row">';
    data.forEach((element) => {
      content += '<div class="col-3 mb-4">';
      content += renderPhoto(element);
      content += "</div>";
    });
    content += "</div>";
  } else {
    content = '<div class="alert alert-info">The list is empty</div>';
  }
  root.innerHTML = content;
};

const getPhotos = async () => {
  try {
    const response = await axios.get(apiUrl);
    const filterPhoto = response.data.filter((photo) => {
      return photo.visible;
    });
    renderPhotoList(filterPhoto);
    console.log("filterPhoto", filterPhoto);
  } catch (error) {
    root.innerHTML =
      '<div class="alert alert-danger">Error fetching photos. Please try again later.</div>';
  }
};

getPhotos();

document
  .getElementById("messageForm")
  .addEventListener("submit", async function (event) {
    event.preventDefault();
    const emailInput = document.getElementById("email");
    const messageInput = document.getElementById("message");
    const email = emailInput.value;
    const message = messageInput.value;

    try {
      await axios.post(apiContacts, {
        email,
        message,
      });

      emailInput.value = "";
      messageInput.value = "";
      successMessage.style.display = "block";
      this.style.display = "none";
      setTimeout(function () {
        successMessage.style.display = "none";
      }, 5000);
    } catch (error) {
      console.error(error);
    }
  });

document.addEventListener("DOMContentLoaded", function () {
  const searchButton = document.getElementById("searchBtn");
  searchButton.addEventListener("click", searchPhotos);
});

async function searchPhotos() {
  const searchPhoto = document.getElementById("searchBar").value.toLowerCase();

  try {
    const response = await axios.get(apiUrl);
    const filteredPhotos = response.data.filter(
      (photo) =>
        photo.title.toLowerCase().includes(searchPhoto) && photo.visible
    );
    updatePhotoList(filteredPhotos);
  } catch (error) {
    console.error("An unexpected error occurred:", error);
  }
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
