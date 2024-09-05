const API_URL = 'http://localhost:8080/api/chapters';

// Crear capítulo
document.getElementById('createChapterForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const chapter = {
        survey: {
            id: document.getElementById('surveyId').value 
        },
        at: {
            createdAt: new Date().toISOString(),
            updatedAt: new Date().toISOString()
        },
        chapter_number: document.getElementById('chapterNumber').value,
        chapter_title: document.getElementById('chapterTitle').value
    };
    
    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(chapter)
        });

        if (response.ok) {
            alert('Capítulo creado con éxito');
            fetchChapters();
        } else {
            throw new Error('Error al crear el capítulo');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Actualizar capítulo
document.getElementById('updateChapterForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('updateId').value;
    const chapter = {
        survey: {
            id: document.getElementById('updateSurveyId').value
        },
        chapter_number: document.getElementById('updateChapterNumber').value,
        chapter_title: document.getElementById('updateChapterTitle').value
    };
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(chapter)
        });

        if (response.ok) {
            alert('Capítulo actualizado con éxito');
            fetchChapters();
        } else {
            throw new Error('Error al actualizar el capítulo');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Eliminar capítulo
document.getElementById('deleteChapterForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('deleteId').value;
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Capítulo eliminado con éxito');
            fetchChapters();
        } else {
            throw new Error('Error al eliminar el capítulo');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Obtener todos los capítulos
async function fetchChapters() {
    try {
        const response = await fetch(API_URL);
        const chapters = await response.json();
        
        const chapterList = document.getElementById('chapterList');
        chapterList.innerHTML = '';
        
        chapters.forEach(chapter => {
            const li = document.createElement('li');
            li.textContent = `ID: ${chapter.id}, Número: ${chapter.chapter_number}, Título: ${chapter.chapter_title}`;
            chapterList.appendChild(li);
        });
    } catch (error) {
        console.error('Error al obtener capítulos:', error);
    }
}

// Actualizar capítulo con PATCH
document.getElementById('patchChapterForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('patchId').value;
    const updates = {
        survey: {
            id: document.getElementById('patchSurveyId').value
        },
        chapter_number: document.getElementById('patchChapterNumber').value,
        chapter_title: document.getElementById('patchChapterTitle').value
    };
    
    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updates)
        });

        if (response.ok) {
            alert('Capítulo parcialmente actualizado con éxito');
            fetchChapters();
        } else {
            throw new Error('Error al actualizar el capítulo');
        }
    } catch (error) {
        alert(error.message);
    }
});


async function fetchSurveys() {
    try {
        const response = await fetch('http://localhost:8080/api/surveys');
        const surveys = await response.json();
        
        const surveySelect = document.getElementById('surveyId');
        surveySelect.innerHTML = '<option value="">Selecciona una encuesta</option>'; // Limpiar opciones actuales
        
        surveys.forEach(survey => {
            const option = document.createElement('option');
            option.value = survey.id;
            option.textContent = `${survey.name} - ${survey.description}`;
            surveySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener encuestas:', error);
    }
}




// Cargar capítulos al inicio
fetchChapters();


