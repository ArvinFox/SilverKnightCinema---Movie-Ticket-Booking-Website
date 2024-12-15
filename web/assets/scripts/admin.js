
// JavaScript for popup screens (modals)
const modal = document.getElementById('popupModal');
const modalContent = document.getElementById('modalContent');
let hasUnsavedChanges = false;

// Function to fetch and display content dynamically
const fetchPopupContent = async (url, id = null, type = null) => {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id: id, type: type })
    });
    const html = await response.text();
    modalContent.innerHTML = `<button class="close-btn" id="closeModal">X</button>` + html;
    document.getElementById('closeModal').onclick = handleModalClose; // Rebind the close button

    setupUnsavedChangesTracking();  // Track any changes made to the modal

  } catch (error) {
    console.error('Error loading popup content:', error);
    modalContent.innerHTML = 'No data available';
  }
};

// Function to open the modal
const openModal = async (url, id = null, type = null) => {
  hasUnsavedChanges = false;
  await fetchPopupContent(url, id, type); // Load content dynamically
  modal.classList.add('show');
  document.body.style.overflow = 'hidden';
};

// Function to handle modal close
const handleModalClose = () => {
  if (hasUnsavedChanges) {
    const userConfirmed = confirm("You have unsaved changes. Do you want to close the modal without saving?");
    if (!userConfirmed) {
      return; // Abort close
    }
  }
  closeModal();
};

// Function to close the modal
const closeModal = () => {
  modal.classList.remove('show');
  document.body.style.overflow = '';
  hasUnsavedChanges = false;
};

// Function to initialize unsaved changes tracking for all fields
const setupUnsavedChangesTracking = () => {
  // Track changes for standard inputs, selects, and textareas
  const inputs = modalContent.querySelectorAll("input, textarea, select");
  inputs.forEach(input => {
    input.addEventListener("input", () => {
      hasUnsavedChanges = true;
    });
  });

  // Track changes for genres checkboxes
  const genreCheckboxes = modalContent.querySelectorAll("input[type='checkbox'][name='genres']");
  genreCheckboxes.forEach(checkbox => {
    checkbox.addEventListener("change", () => {
      hasUnsavedChanges = true;
    });
  });

  // Track changes for cast and crew dynamic rows
  const castCrewContainers = modalContent.querySelectorAll(".cast-crew-row input");
  castCrewContainers.forEach(input => {
    input.addEventListener("input", () => {
      hasUnsavedChanges = true;
    });
  });

  // Listen for dynamically added cast or crew rows
  const castContainer = modalContent.querySelector('#cast-container');
  const crewContainer = modalContent.querySelector('#crew-container');

  [castContainer, crewContainer].forEach(container => {
    if (container) {
      container.addEventListener("input", () => {
        hasUnsavedChanges = true;
      });

      container.addEventListener("click", (event) => {
        if (event.target.classList.contains('remove-btn')) {
          hasUnsavedChanges = true; // Mark changes if a row is removed
        }
      });
    }
  });

  // Additional tracking for dynamically added genres or inputs
  const addGenreListeners = () => {
    const newGenreCheckboxes = modalContent.querySelectorAll("input[type='checkbox'][name='genres']");
    newGenreCheckboxes.forEach(checkbox => {
      if (!checkbox.dataset.tracked) {
        checkbox.addEventListener("change", () => {
          hasUnsavedChanges = true;
        });
        checkbox.dataset.tracked = "true"; // Prevent duplicate listeners
      }
    });
  };

  // Observe DOM for dynamically added elements
  const observer = new MutationObserver(() => {
    addGenreListeners(); // Add listeners for new genre elements
  });

  observer.observe(modalContent, { childList: true, subtree: true });
};

// Event listeners for open and close actions
document.querySelectorAll('#openModal').forEach((link) => {
  link.addEventListener('click', () => {
    const action = link.getAttribute('data-action');
    const type = link.getAttribute('data-type');
    const id = link.getAttribute('data-id');

    let url = "../adminView/";

    if (action === 'add') {
      url = url + 'add' + type + '.jsp';
    } else if (action === 'view') {
      url = "../fetch";
    }

    openModal(url, id, type);
  });
});

const closeModalButton = document.getElementById('closeModal');
if (closeModalButton) {
  closeModalButton.addEventListener('click', handleModalClose);
}

if (modal) {
  modal.addEventListener('mousedown', (event) => {
    if (event.target === modal) {
      modal.dataset.clickOutside = "true"; // Track if the initial click was outside
    } else {
      modal.dataset.clickOutside = "false"; // Initial click was inside
    }
  });

  modal.addEventListener('mouseup', (event) => {
    if (modal.dataset.clickOutside === "true" && event.target === modal) {
      handleModalClose();
    }
  });
}
//


// JavaScript for toggling filtering options
const filterHeader = document.getElementById('filterHeader');
const filterOptions = document.getElementById('filterOptions');
const toggleIcon = document.getElementById('toggleIcon');

if (filterHeader) {
  filterHeader.addEventListener('click', function () {
    filterOptions.classList.toggle('active');
    filterHeader.classList.toggle('collapsed');
  });
}
//


// JavaScript for confirming deletion
function confirmUserDelete(userName, userEmail, userId) {
 return confirm(`Are you sure you want to delete the user "${userName}" (Email: ${userEmail}) [ID: ${userId}]? \nThis action cannot be undone.`);
}

function confirmGuestDelete(guestName, guestEmail, guestId) {
  return confirm(`Are you sure you want to delete the guest "${guestName}" (Email: ${guestEmail}) [ID: ${guestId}]? \nThis action cannot be undone.`);
}

function confirmBookingDelete(bookingId) {
  return confirm(`Are you sure you want to delete this booking [ID: ${bookingId}]? \nThis action cannot be undone.`);
}

function confirmShowtimeDelete(movieTitle, hallName, showtimeId, showDate, showTime) {
  return confirm(
      `Are you sure you want to delete the showtime for the movie "${movieTitle}" [ID: ${showtimeId}]? \n` +
      `  Hall: ${hallName} \n` +
      `  Show Date: ${showDate} \n` +
      `  Show Time: ${showTime} \n` +
      `\nThis action cannot be undone.`
  );
}

function confirmHallDelete(hallName, hallId) {
  return confirm(`Are you sure you want to delete the hall "${hallName}" [ID: ${hallId}]? \nThis action cannot be undone.`);
}

function confirmPromotionDelete(promotionName, promotionId) {
  return confirm(`Are you sure you want to delete the promotion "${promotionName}" [ID: ${promotionId}]? \nThis action cannot be undone.`);
}

function confirmFoodItemDelete(itemName, itemId) {
  return confirm(`Are you sure you want to delete the item "${itemName}" [ID: ${itemId}]? \nThis action cannot be undone.`);
}

function confirmInquiryDelete(inquirySubject, inquiryId) {
  return confirm(`Are you sure you want to delete the inquiry with subject "${inquirySubject}" [ID: ${inquiryId}]? \nThis action cannot be undone.`);
}

async function confirmMovieDelete(movieTitle, movieId) {
  try {
    const showtimes = await fetchShowtimesByMovie(movieId);

    let showtimeDetailsMessage = "";
    if (showtimes && showtimes.length > 0) {
      showtimeDetailsMessage = "\n\nThe following showtimes are associated with this movie and will also be deleted:\n";
      showtimeDetailsMessage += showtimes
        .map((showtime, index) => `  ${index + 1}. ${showtime.hallName} on ${showtime.showDate} at ${showtime.formattedTime} [ID: ${showtime.showtimeId}]`)
        .join("\n");
      showtimeDetailsMessage += "\n";
    }

    const confirmationMessage =
      `Are you sure you want to delete the movie "${movieTitle}" [ID: ${movieId}]?` +
      showtimeDetailsMessage +
      "\nThis action cannot be undone.";

    return confirm(confirmationMessage);

  } catch (error) {
    console.error("Error in fetching showtimes: ", error);
    alert("An error occurred while checking associated showtimes. Please try again.");
    return false;
  }
}

async function fetchShowtimesByMovie(id) {
  try {
    const response = await fetch("../fetch?showtimeMovieId=" + id);
    if (!response.ok) throw new Error("Failed to fetch showtimes by movie");

    const data = await response.json();
    const { showtimes } = data;

    return showtimes;

  } catch (error) {
    console.error("Error in fetching showtimes by movie: ", error);
  }
}

async function confirmGenreDelete(genreName, genreId) {
  try {
    const movies = await fetchMoviesByLanguageOrGenre(genreId, 'genre');

    let movieDetailsMessage = "";
    if (movies && movies.length > 0) {
      movieDetailsMessage = "\n\nThe following movies are associated with this genre and will also be deleted:\n";
      movieDetailsMessage += movies
        .map((movie, index) => `  ${index + 1}. ${movie.title} [ID: ${movie.movieId}]`)
        .join("\n");
      movieDetailsMessage += "\n";
    }

    const confirmationMessage = 
      `Are you sure you want to delete the genre "${genreName}" [ID: ${genreId}]?` +
      movieDetailsMessage +
      "\nThis action cannot be undone.";

    return confirm(confirmationMessage);

  } catch (error) {
    console.error("Error in fetching movies: ", error);
    alert("An error occurred while checking associated movies. Please try again.");
    return false;
  }
}

async function confirmLanguageDelete(languageName, languageId) {
  try {
    const movies = await fetchMoviesByLanguageOrGenre(languageId, 'language');

    let movieDetailsMessage = "";
    if (movies && movies.length > 0) {
      movieDetailsMessage = "\n\nThe following movies are associated with this language and will also be deleted:\n";
      movieDetailsMessage += movies
        .map((movie, index) => `  ${index + 1}. ${movie.title} [ID: ${movie.movieId}]`)
        .join("\n");
      movieDetailsMessage += "\n";
    }

    const confirmationMessage = 
      `Are you sure you want to delete the language "${languageName}" [ID: ${languageId}]?` +
      movieDetailsMessage +
      "\nThis action cannot be undone.";

    return confirm(confirmationMessage);

  } catch (error) {
    console.error("Error in fetching movies: ", error);
    alert("An error occurred while checking associated movies. Please try again.");
    return false;
  }
}

async function fetchMoviesByLanguageOrGenre(id, type) {
  try {
    const response = await fetch("../fetch?" + type + "Id=" + id);
    if (!response.ok) throw new Error("Failed to fetch movies by " + type);

    const data = await response.json();
    const { movies } = data;

    return movies;

  } catch (error) {
    console.error("Error in fetching movies by language or genre: ", error);
  }
}
//


// JavaScript for uploading poster files (separately)
function uploadPoster(type) {
  document.getElementById(`${type}Form`).addEventListener('submit', async function (e) {
    e.preventDefault(); // Prevent the default form submission

    // File upload
    const posterInput = document.getElementById('poster');
    if (posterInput.files.length > 0) {
      const file = posterInput.files[0];
      const formData = new FormData();
      formData.append('poster', file);
      formData.append('type', type);

      const uploadResponse = await fetch('../upload', {
        method: 'POST',
        body: formData
      });

      if (!uploadResponse.ok) {
        alert('File upload failed!');
        return;
      }

      const uploadedFilePath = await uploadResponse.text();
      document.getElementById('posterPath').value = uploadedFilePath;  // Update the value of the file path
    }

    // Submit the rest of the form
    this.submit();
  });
}
//


// JavaScript for adding and removing cast and crew rows
function addCastCrewRow(type) {
  const container = document.getElementById(`${type}-container`);

  const newRow = document.createElement('div');
  newRow.className = 'cast-crew-row';

  if (type === 'cast') {
    newRow.innerHTML = `
      <input type="text" name="castActor[]" placeholder="Actor Name" required>
      <input type="text" name="castCharacter[]" placeholder="Character Name" required>
      <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)">Remove</button>
    `;
  } else if (type === 'crew') {
    newRow.innerHTML = `
      <input type="text" name="crewMember[]" placeholder="Member Name" required>
      <input type="text" name="crewRole[]" placeholder="Role (e.g., Director)" required>
      <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)">Remove</button>
    `;
  }

  container.appendChild(newRow);

  // Enable all remove buttons
  const removeButtons = container.querySelectorAll(".remove-btn");
  removeButtons.forEach(btn => {
    btn.disabled = false;
  });
}

function removeCastCrewRow(type, button) {
  const container = document.getElementById(`${type}-container`);
  const rows = container.querySelectorAll('.cast-crew-row');

  if (rows.length > 1) {
    button.parentElement.remove();
  }

  // Recheck the number of rows after removing
  const remainingRows = container.querySelectorAll('.cast-crew-row');
  if (remainingRows.length === 1) {
    const removeButtons = container.querySelectorAll('.remove-btn');
    removeButtons.forEach(btn => {
      btn.disabled = true;  // Disable the button
    });
  }
}
//


// JavaScript for toggling between view and edit modes
let initialGenreState = [];
let initialCastState = [];
let initialCrewState = [];
let initialLanguage = '';
let initialMovieStatus = '';
let initialPromotionStatus = '';
let initialFoodItemType = '';
let initialHallType = '';
let initialShowtimeHall = '';
let initialShowtimeMovie = '';

const allLanguages = [];
const allGenres = [];
const allFoodItemTypes = [];
const allHallTypes = [];
const allShowtimeHalls = [];
const allShowtimeMovies = [];

async function fetchShowtimeHallsAndMovies() {
  try {
    const response = await fetch('../fetch?showtimeHalls=true&showtimeMovies=true');
    if (!response.ok) throw new Error('Failed to fetch showtime halls and movies');

    const data = await response.json();
    const { showtimeHalls, showtimeMovies } = data;

    allShowtimeHalls.length = 0;
    allShowtimeHalls.push(...showtimeHalls);

    allShowtimeMovies.length = 0;
    allShowtimeMovies.push(...showtimeMovies);

  } catch (error) {
    console.error('Error in fetching data: ', error);
  }
}

async function fetchHallTypes() {
  try {
    const response = await fetch('../fetch?hallTypes=true');
    if (!response.ok) throw new Error('Failed to fetch hall types');

    const data = await response.json();
    const { hallTypes } = data;

    allHallTypes.length = 0;
    allHallTypes.push(...hallTypes);

  } catch (error) {
    console.error('Error in fetching data: ', error);
  }
}

async function fetchFoodItemTypes() {
  try {
    const response = await fetch('../fetch?foodItemTypes=true');
    if (!response.ok) throw new Error('Failed to fetch food item types');

    const data = await response.json();
    const { itemTypes } = data;

    allFoodItemTypes.length = 0;
    allFoodItemTypes.push(...itemTypes);

  } catch (error) {
    console.error('Error in fetching data: ', error);
  }
}

async function fetchLanguagesAndGenres() {
  try {
    const response = await fetch('../fetch?languages=true&genres=true');
    if (!response.ok) throw new Error('Failed to fetch languages and genres');

    const data = await response.json();
    const { languages, genres } = data;

    allLanguages.length = 0;  // Clear any existing data
    allLanguages.push(...languages);  // Add fetched languages

    allGenres.length = 0;
    allGenres.push(...genres);

  } catch (error) {
    console.error('Error fetching data: ', error);
  }
}

async function toggleEditMode(editMode, type = null) {
  // General elements
  const editButton = document.querySelector('.edit-btn');
  const actionButtons = document.querySelector('.action-buttons');
  const elements = document.querySelectorAll('.editable');

  if (editMode) {
    // Enable edit mode
    if (type === 'movie') {
      await fetchLanguagesAndGenres();
      toggleMovieEdit(true);
    }

    if (type === 'hall') {
      await fetchHallTypes();
      toggleHallEdit(true);
    }

    if (type === 'showtime') {
      await fetchShowtimeHallsAndMovies();
      toggleShowtimeEdit(true);
    }

    if (type === 'promotion') {
      togglePromotionEdit(true);
    }

    if (type === 'foodItem') {
      await fetchFoodItemTypes();
      toggleFoodItemEdit(true);
    }

    elements.forEach(el => el.disabled = false);
    editButton.classList.add('hidden');
    actionButtons.classList.remove('hidden');
    actionButtons.style.display = "flex";

  } else {
    // Disable edit mode
    hasUnsavedChanges = false;

    if (type === 'movie') {
      toggleMovieEdit(false);
    }

    if (type === 'hall') {
      toggleHallEdit(false);
    }

    if (type === 'showtime') {
      toggleShowtimeEdit(false);
    }

    if (type === 'promotion') {
      togglePromotionEdit(false);
    }

    if (type === 'foodItem') {
      toggleFoodItemEdit(false);
    }
   
    elements.forEach(el => el.disabled = true);
    editButton.classList.remove('hidden');
    actionButtons.classList.add('hidden');
    actionButtons.style.display = "none";
  }
}

function toggleShowtimeEdit(editMode) {
  const hallContainer = document.getElementById('hall-container');
  const movieContainer = document.getElementById('movie-container');

  if (editMode) {
    initialShowtimeHall = hallContainer.querySelector("input").value;
    initialShowtimeMovie = movieContainer.querySelector("input").value;

    hallContainer.innerHTML = "";
    const hallSelect = document.createElement("select");
    hallSelect.classList.add("form-control");
    hallSelect.id = "hall";
    hallSelect.name = "hall";
    hallSelect.required = true;

    allShowtimeHalls.forEach(hall => {
      const option = document.createElement("option");
      option.value = hall.hallId;
      option.textContent = hall.name + " - " + hall.location;

      if (hall.name + " - " + hall.location === initialShowtimeHall) {
        option.selected = true;
      }

      hallSelect.appendChild(option);
    });

    hallContainer.appendChild(hallSelect);

    movieContainer.innerHTML = "";
    const movieSelect = document.createElement("select");
    movieSelect.classList.add("form-control");
    movieSelect.id = "movie";
    movieSelect.name = "movie";
    movieSelect.required = true;

    allShowtimeMovies.forEach(movie => {
      const option = document.createElement("option");
      option.value = movie.movieId;
      option.textContent = movie.title;

      if (movie.title === initialShowtimeMovie) {
        option.selected = true;
      }

      movieSelect.appendChild(option);
    });

    movieContainer.appendChild(movieSelect);

  } else {
    hallContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialShowtimeHall}" disabled>
    `;
    movieContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialShowtimeMovie}" disabled>
    `;
  }
}

function toggleHallEdit(editMode) {
  const hallTypeContainer = document.getElementById('hall-type-container');

  if (editMode) {
    initialHallType = hallTypeContainer.querySelector("input").value;

    hallTypeContainer.innerHTML = "";
    const select = document.createElement("select");
    select.classList.add("form-control");
    select.id = "hallType";
    select.name = "hallType";
    select.required = true;

    allHallTypes.forEach(hallType => {
      const option = document.createElement("option");
      option.value = hallType;
      option.textContent = hallType;

      if (hallType === initialHallType) {
        option.selected = true;
      }

      select.appendChild(option);
    });

    hallTypeContainer.appendChild(select);

  } else {
    hallTypeContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialHallType}" disabled>
    `;
  }
}

function toggleFoodItemEdit(editMode) {
  const itemTypeContainer = document.getElementById('item-type-container');

  if (editMode) {
    initialFoodItemType = itemTypeContainer.querySelector("input").value;

    itemTypeContainer.innerHTML = "";
    const select = document.createElement("select");
    select.classList.add("form-control");
    select.id = "itemType";
    select.name = "itemType";
    select.required = true;

    allFoodItemTypes.forEach(itemType => {
      const option = document.createElement("option");
      option.value = itemType;
      option.textContent = itemType;

      if (itemType === initialFoodItemType) {
        option.selected = true;
      }

      select.appendChild(option);
    });

    itemTypeContainer.appendChild(select);

  } else {
    itemTypeContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialFoodItemType}" disabled>
    `;
  }
}

function togglePromotionEdit(editMode) {
  const statusContainer = document.getElementById('status-container');

  if (editMode) {
    initialPromotionStatus = statusContainer.querySelector("input").value;

    statusContainer.innerHTML = "";
    const select = document.createElement("select");
    select.classList.add("form-control");
    select.id = "status";
    select.name = "status";
    select.required = true;

    const activeOption = document.createElement("option");
    activeOption.value = "active";
    activeOption.textContent = "Active";

    if ("Active" === initialPromotionStatus) {
      activeOption.selected = true;
    }

    select.appendChild(activeOption);

    const inactiveOption = document.createElement("option");
    inactiveOption.value = "inactive";
    inactiveOption.textContent = "Inactive";

    if ("Inactive" === initialPromotionStatus) {
      inactiveOption.selected = true;
    }

    select.appendChild(inactiveOption);

    statusContainer.appendChild(select);

  } else {
    statusContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialPromotionStatus}" disabled>
    `;
  }
}

function toggleMovieEdit(editMode) {
  const trailerLink = document.getElementById('trailerLink');

  if (editMode) {
    toggleLanguageEdit(true);
    toggleGenresEdit(true);
    toggleMovieStatusEdit(true);
    toggleCastCrewEdit('cast', true);
    toggleCastCrewEdit('crew', true);

    trailerLink.classList.add('hidden');

  } else {
    toggleLanguageEdit(false);
    toggleGenresEdit(false);
    toggleMovieStatusEdit(false);
    toggleCastCrewEdit('cast', false);
    toggleCastCrewEdit('crew', false);

    trailerLink.classList.remove('hidden');
  }
}

function toggleLanguageEdit(editMode) {
  const languageContainer = document.getElementById('language-container');

  if (editMode) {
    initialLanguage = languageContainer.querySelector("input").value;

    languageContainer.innerHTML = "";
    const select = document.createElement("select");
    select.classList.add("form-control");
    select.id = "language";
    select.name = "language";
    select.required = true;

    allLanguages.forEach(language => {
      const option = document.createElement("option");
      option.value = language.languageId;
      option.textContent = language.language;

      if (language.language === initialLanguage) {
        option.selected = true;
      }

      select.appendChild(option);
    });

    languageContainer.appendChild(select);

  } else {
    languageContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialLanguage}" disabled>
    `;
  }
}

function toggleGenresEdit(editMode) {
  const genreContainer = document.getElementById("genre-container");
  const genreTags = genreContainer.querySelectorAll(".genre-tag");

  if (editMode) {
    // Save initial genre state
    initialGenreState = Array.from(genreTags).map(tag => ({
      genreId: tag.getAttribute('data-genre-id')
    }));

    genreContainer.classList.remove("genres");
    genreContainer.classList.add("checkbox-group");
    genreContainer.innerHTML = "";

    // Render all genres with checkboxes
    allGenres.forEach(genre => {
      const isSelected = initialGenreState.some(state => state.genreId === `genre-${genre.genreId}`);

      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.id = `genre-${genre.genreId}`;
      checkbox.name = "genres";
      checkbox.value = genre.genreId;
      checkbox.checked = isSelected;

      const label = document.createElement("label");
      label.setAttribute("for", checkbox.id);
      label.textContent = genre.name;

      genreContainer.appendChild(checkbox);
      genreContainer.appendChild(label);
    });

  } else {
    genreContainer.classList.remove("checkbox-group");
    genreContainer.classList.add("genres");
    genreContainer.innerHTML = "";

    // Render selected genres only
    initialGenreState
      .forEach(state => {
        const genre = allGenres.find(g => `genre-${g.genreId}` === state.genreId);
        if (genre) {
          const genreTag = document.createElement("span");
          genreTag.className = "genre-tag";
          genreTag.dataset.genreId = `genre-${genre.genreId}`;
          genreTag.textContent = genre.name;
          genreContainer.appendChild(genreTag);
        }
      });
  }
}

function toggleMovieStatusEdit(editMode) {
  const statusContainer = document.getElementById('status-container');

  if (editMode) {
    initialMovieStatus = statusContainer.querySelector("input").value;

    statusContainer.innerHTML = '';
    const select = document.createElement("select");
    select.classList.add("form-control");
    select.id = "status";
    select.name = "status";
    select.required = true;

    const nowShowingOption = document.createElement("option");
    nowShowingOption.value = "Now Showing";
    nowShowingOption.textContent = "Now Showing";

    if ("Now Showing" === initialMovieStatus) {
      nowShowingOption.selected = true;
    }

    select.appendChild(nowShowingOption);

    const comingSoonOption = document.createElement("option");
    comingSoonOption.value = "Coming Soon";
    comingSoonOption.textContent = "Coming Soon";

    if ("Coming Soon" === initialMovieStatus) {
      comingSoonOption.selected = true;
    }

    select.appendChild(comingSoonOption);

    statusContainer.appendChild(select);

  } else {
    statusContainer.innerHTML = `
      <input type="text" class="form-control editable" value="${initialMovieStatus}" disabled>
    `;
  }
}

function toggleCastCrewEdit(type, editMode) {
  const container = document.getElementById(`${type}-container`);
  const table = document.getElementById(`${type}-table`);
  const isCast = type === 'cast';

  if (editMode) {
    const rows = table.querySelectorAll("tbody tr");
    const stateKey = isCast ? 'initialCastState' : 'initialCrewState';

    // Save the initial state
    window[stateKey] = Array.from(rows).map(row => {
      const cells = row.querySelectorAll("td");
      return isCast
        ? { actor: cells[0]?.textContent, character: cells[1]?.textContent }
        : { name: cells[0]?.textContent, role: cells[1]?.textContent };
    });

    container.innerHTML = "";
    window[stateKey].forEach(member => {
      const row = document.createElement("div");
      row.classList.add("cast-crew-row");

      row.innerHTML = isCast
        ? `
          <input type="text" name="castActor[]" value="${member.actor}" placeholder="Actor Name">
          <input type="text" name="castCharacter[]" value="${member.character}" placeholder="Character Name">
          <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)" ${window[stateKey].length === 1 ? "disabled" : ""}>Remove</button>
        `
        : `
          <input type="text" name="crewMember[]" value="${member.name}" placeholder="Member Name">
          <input type="text" name="crewRole[]" value="${member.role}" placeholder="Role (e.g., Director)">
          <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)" ${window[stateKey].length === 1 ? "disabled" : ""}>Remove</button>
        `;

      container.appendChild(row);
    });

    const addButton = document.createElement("button");
    addButton.type = "button";
    addButton.textContent = isCast ? "Add Cast Member" : "Add Crew Member";
    addButton.classList.add(isCast ? "add-cast-button" : "add-crew-button");
    addButton.onclick = () => addCastCrewRow(type);
    addButton.style.marginBottom = "8px";
    container.insertAdjacentElement("afterend", addButton);

  } else {
    const stateKey = isCast ? 'initialCastState' : 'initialCrewState';

    container.innerHTML = "";
    const tableHtmlStart = `
      <table id="${type}-table">
        <thead>
          <tr>
            ${isCast ? "<th>Actor</th><th>Character</th>" : "<th>Name</th><th>Role</th>"}
          </tr>
        </thead>
        <tbody>
    `;
    const tableHtmlEnd = `
        </tbody>
      </table>
    `;

    let tableRows = '';
    window[stateKey].forEach(member => {
      tableRows += isCast
        ? `<tr><td>${member.actor}</td><td>${member.character}</td></tr>`
        : `<tr><td>${member.name}</td><td>${member.role}</td></tr>`;
    });

    document.querySelector('.add-cast-button').style.display = "none";
    document.querySelector('.add-crew-button').style.display = "none";
    container.innerHTML = tableHtmlStart + tableRows + tableHtmlEnd;
  }
}

function resetChanges(type = null) {
  // Reset the entire form
  document.getElementById('editForm').reset();
  hasUnsavedChanges = false;

  // Reset each section
  if (type === 'movie') {
    resetMovie();
  }

  if (type === 'hall') {
    resetHall();
  }

  if (type === 'showtime') {
    resetShowtime();
  }

  if (type === 'promotion') {
    resetPromotion();
  }

  if (type === 'foodItem') {
    resetFoodItem();
  }
}

function resetShowtime() {
  const hallContainer = document.getElementById('hall-container');
  const hallSelectElement = hallContainer.querySelector("select");

  const initialHallOption = Array.from(hallSelectElement.options).find(option => option.textContent === initialShowtimeHall);
  if (initialHallOption) {
    initialHallOption.selected = true;
  }

  const movieContainer = document.getElementById('movie-container');
  const movieSelectElement = movieContainer.querySelector("select");

  const initialMovieOption = Array.from(movieSelectElement.options).find(option => option.textContent === initialShowtimeMovie);
  if (initialMovieOption) {
    initialMovieOption.selected = true;
  }
}

function resetHall() {
  const hallTypeContainer = document.getElementById('hall-type-container');
  const selectElement = hallTypeContainer.querySelector("select");

  const initialHallTypeOption = Array.from(selectElement.options).find(option => option.textContent === initialHallType);
  if (initialHallTypeOption) {
    initialHallTypeOption.selected = true;
  }
}

function resetFoodItem() {
  const itemTypeContainer = document.getElementById('item-type-container');
  const selectElement = itemTypeContainer.querySelector("select");

  const initialFoodItemTypeOption = Array.from(selectElement.options).find(option => option.textContent === initialFoodItemType);
  if (initialFoodItemTypeOption) {
    initialFoodItemTypeOption.selected = true;
  }
}

function resetPromotion() {
  const statusContainer = document.getElementById('status-container');
  const selectElement = statusContainer.querySelector("select");

  const initialStatusOption = Array.from(selectElement.options).find(option => option.textContent === initialPromotionStatus);
  if (initialStatusOption) {
    initialStatusOption.selected = true;
  }
}

function resetMovie() {
  resetLanguage();
  resetGenres();
  resetMovieStatus();
  resetCastCrew('cast');
  resetCastCrew('crew');
}

function resetLanguage() {
  const languageContainer = document.getElementById('language-container');
  const selectElement = languageContainer.querySelector("select");

  const initialLanguageOption = Array.from(selectElement.options).find(option => option.textContent === initialLanguage);
  if (initialLanguageOption) {
    initialLanguageOption.selected = true;
  }
}

function resetGenres() {
  const genreContainer = document.getElementById('genre-container');
  genreContainer.innerHTML = ""; // Clear existing genres

  // Display all genres
  allGenres.forEach(genre => {
    const isSelected = initialGenreState.some(state => state.genreId === `genre-${genre.genreId}`);

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.id = `genre-${genre.genreId}`;
    checkbox.name = "genres";
    checkbox.value = genre.genreId;
    checkbox.checked = isSelected;

    const label = document.createElement("label");
    label.setAttribute("for", checkbox.id);
    label.textContent = genre.name;

    genreContainer.appendChild(checkbox);
    genreContainer.appendChild(label);
  });
}

function resetMovieStatus() {
  const statusContainer = document.getElementById('status-container');
  const selectElement = statusContainer.querySelector("select");

  const initialStatusOption = Array.from(selectElement.options).find(option => option.textContent === initialMovieStatus);
  if (initialStatusOption) {
    initialStatusOption.selected = true;
  }
}

function resetCastCrew(type) {
  const container = document.getElementById(`${type}-container`);
  const stateKey = type === 'cast' ? 'initialCastState' : 'initialCrewState';

  container.innerHTML = "";

  // Recreate rows for cast or crew
  window[stateKey].forEach(member => {
    const row = document.createElement("div");
    row.classList.add("cast-crew-row");

    row.innerHTML = type === 'cast'
      ? `
        <input type="text" name="castActor[]" value="${member.actor}" placeholder="Actor Name">
        <input type="text" name="castCharacter[]" value="${member.character}" placeholder="Character Name">
        <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)" ${window[stateKey].length === 1 ? "disabled" : ""}>Remove</button>
      `
      : `
        <input type="text" name="crewMember[]" value="${member.name}" placeholder="Member Name">
        <input type="text" name="crewRole[]" value="${member.role}" placeholder="Role (e.g., Director)">
        <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)" ${window[stateKey].length === 1 ? "disabled" : ""}>Remove</button>
      `;

    container.appendChild(row);
  });
}
//


// JavaScript for toggling admin sidebar
const toggleSidebarButton = document.getElementById('toggleSidebar');
const adminSidebar = document.getElementById('adminSidebar');
const mainContent = document.getElementById('mainContent');

if (toggleSidebarButton) {
  toggleSidebarButton.addEventListener('click', function () {
    adminSidebar.classList.toggle('collapsed');
    mainContent.classList.toggle('expanded');
  });
}
//
