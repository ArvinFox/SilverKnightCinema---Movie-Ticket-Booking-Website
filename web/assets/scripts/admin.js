
// JavaScript for popup screens (modals)
const modal = document.getElementById('popupModal');
const modalContent = document.getElementById('modalContent');
let hasUnsavedChanges = false;

// Function to fetch and display content dynamically
const fetchPopupContent = async (url, id = null) => {
  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ id: id})
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
const openModal = async (url, id = null) => {
  hasUnsavedChanges = false;
  await fetchPopupContent(url, id); // Load content dynamically
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
      url = url + 'view' + type + '.jsp';
    }

    openModal(url, id);
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
  filterHeader.addEventListener('click', function() {
    filterOptions.classList.toggle('active');
    filterHeader.classList.toggle('collapsed');
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

// Sample language data
const allLanguages = [
  { id: 1, name: "English" },
  { id: 2, name: "Spanish" },
  { id: 3, name: "French" },
  { id: 4, name: "German" },
  { id: 5, name: "Hindi" },
  { id: 6, name: "Japanese" },
  { id: 7, name: "Korean" },
  { id: 8, name: "Sinhala" }
];

// Sample genres data
const allGenres = [
  { id: 1, name: "Action" },
  { id: 2, name: "Adventure" },
  { id: 3, name: "Comedy" },
  { id: 4, name: "Drama" },
  { id: 5, name: "Sci-Fi" },
  { id: 6, name: "Horror" },
  { id: 7, name: "Thriller" },
  { id: 8, name: "Crime" },
  { id: 9, name: "Fantasy" },
  { id: 10, name: "Romance" },
  { id: 11, name: "Mystery" },
  { id: 12, name: "Family" },
  { id: 13, name: "Sport" },
  { id: 14, name: "History" },
  { id: 15, name: "Documentary" }
];

function toggleEditMode(editMode) {
  // General elements
  const trailerLink = document.getElementById('trailerLink');
  const editButton = document.querySelector('.edit-btn');
  const actionButtons = document.querySelector('.action-buttons');
  const elements = document.querySelectorAll('.editable');

  if (editMode) {
    // Enable edit mode
    toggleLanguageEdit(true);
    toggleGenresEdit(true);
    toggleCastCrewEdit('cast', true);
    toggleCastCrewEdit('crew', true);

    trailerLink.classList.add('hidden');
    elements.forEach(el => el.disabled = false);
    editButton.classList.add('hidden');
    actionButtons.classList.remove('hidden');
    actionButtons.style.display = "flex";

  } else {
    // Disable edit mode
    hasUnsavedChanges = false;
    toggleLanguageEdit(false);
    toggleGenresEdit(false);
    toggleCastCrewEdit('cast', false);
    toggleCastCrewEdit('crew', false);

    trailerLink.classList.remove('hidden');
    elements.forEach(el => el.disabled = true);
    editButton.classList.remove('hidden');
    actionButtons.classList.add('hidden');
    actionButtons.style.display = "none";
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
      option.value = language.id;
      option.textContent = language.name;

      if (language.name === initialLanguage) {
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
      genreId: tag.dataset.genreId,
      checked: !tag.hasAttribute("hidden")
    }));

    genreContainer.classList.remove("genres");
    genreContainer.classList.add("checkbox-group");
    genreContainer.innerHTML = "";

    // Render all genres with checkboxes
    allGenres.forEach(genre => {
      const isSelected = initialGenreState.some(state => state.genreId === `genre-${genre.name.toLowerCase().replace(/\s+/g, "-")}` && state.checked);

      const checkbox = document.createElement("input");
      checkbox.type = "checkbox";
      checkbox.id = `genre-${genre.name.toLowerCase().replace(/\s+/g, "-")}`;
      checkbox.name = "genres";
      checkbox.value = genre.id;
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
      .filter(state => state.checked)
      .forEach(state => {
        const genre = allGenres.find(g => `genre-${g.name.toLowerCase().replace(/\s+/g, "-")}` === state.genreId);
        if (genre) {
          const genreTag = document.createElement("span");
          genreTag.className = "genre-tag";
          genreTag.dataset.genreId = `genre-${genre.name.toLowerCase().replace(/\s+/g, "-")}`;
          genreTag.textContent = genre.name;
          genreContainer.appendChild(genreTag);
        }
      });
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
          <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)">Remove</button>
        `
        : `
          <input type="text" name="crewMember[]" value="${member.name}" placeholder="Member Name">
          <input type="text" name="crewRole[]" value="${member.role}" placeholder="Role (e.g., Director)">
          <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)">Remove</button>
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

function resetChanges() {
  // Reset the entire form
  document.getElementById('editForm').reset();
  hasUnsavedChanges = false;

  // Reset each section
  resetGenres();
  resetCastCrew('cast');
  resetCastCrew('crew');
}

function resetGenres() {
  const genreContainer = document.getElementById('genre-container');
  genreContainer.innerHTML = ""; // Clear existing genres

  // Display all genres
  allGenres.forEach(genre => {
    const isSelected = initialGenreState.some(state => state.genreId === `genre-${genre.name.toLowerCase().replace(/\s+/g, "-")}` && state.checked);

    const checkbox = document.createElement("input");
    checkbox.type = "checkbox";
    checkbox.id = `genre-${genre.name.toLowerCase().replace(/\s+/g, "-")}`;
    checkbox.name = "genres";
    checkbox.value = genre.id;
    checkbox.checked = isSelected;

    const label = document.createElement("label");
    label.setAttribute("for", checkbox.id);
    label.textContent = genre.name;

    genreContainer.appendChild(checkbox);
    genreContainer.appendChild(label);
  });
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
        <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('cast', this)">Remove</button>
      `
      : `
        <input type="text" name="crewMember[]" value="${member.name}" placeholder="Member Name">
        <input type="text" name="crewRole[]" value="${member.role}" placeholder="Role (e.g., Director)">
        <button type="button" class="action-btn remove-btn" onclick="removeCastCrewRow('crew', this)">Remove</button>
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


