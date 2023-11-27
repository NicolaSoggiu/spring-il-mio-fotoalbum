// script.js
const apiUrl = "http://localhost:8080/api/v1/photos";
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
    </div>`;
};

const renderPhotoList = (data) => {
  let content;
  if (data.length > 0) {
    content = '<div class="row">';
    data.forEach((element) => {
      content += '<div class="col-3">';
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
    renderPhotoList(response.data);
  } catch (error) {
    root.innerHTML =
      '<div class="alert alert-danger">Error fetching photos. Please try again later.</div>';
  }
};

getPhotos();
