// script.js
const apiUrl = "http://localhost:8080/api/v1/photos";
const root = document.getElementById("root");

const renderPhoto = (element) => {
  return `
    <div class="card shadow h-100">
      <img src="${element.url}" class="card-img-top" alt="${element.title}">
      <div class="card-body">
        <h5 class="card-title">${element.title}</h5>
        <p class="card-text">${element.description}</p>
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
    console.log("response", response);
    renderPhotoList(response.data);
  } catch (error) {
    console.error("Error fetching photos:", error);
    root.innerHTML =
      '<div class="alert alert-danger">Error fetching photos. Please try again later.</div>';
  }
};

getPhotos();
