//For FAQ Popup Answers

document.querySelectorAll('.faq-question').forEach(question => {
    question.addEventListener('click', () => {
        const answer = question.nextElementSibling;
        const arrow = question.querySelector('.arrow');

        // Check if the answer is currently expanded
        if (answer.style.height && answer.style.height !== '0px') {
            // Collapse the answer
            answer.style.height = '0';
            answer.style.paddingTop = '0';
            answer.style.paddingBottom = '0';
            arrow.innerHTML = '&#9660;'; // Down arrow
        } else {
            // Expand the answer
            answer.style.height = `${answer.scrollHeight}px`;
            answer.style.paddingTop = '0';
            answer.style.paddingBottom = '0';
            arrow.innerHTML = '&#9650;'; // Up arrow
        }
    });
});

// Handle tab switching
const tabs = document.querySelectorAll('.tab-btn');
const contents = document.querySelectorAll('.faq-content');

tabs.forEach(tab => {
    tab.addEventListener('click', () => {
        // Remove active class from all tabs
        tabs.forEach(t => t.classList.remove('active'));
        // Hide all FAQ contents
        contents.forEach(content => content.classList.add('hidden'));

        // Add active class to clicked tab and show corresponding content
        tab.classList.add('active');
        const target = document.getElementById(tab.dataset.tab);
        target.classList.remove('hidden');
    });
});

