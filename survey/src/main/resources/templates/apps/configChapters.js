const API_URL1 = 'http://localhost:8080/api/chapter';
const API_URL_SURVEYS = 'http://localhost:8080/api/surveys';


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
        const response = await fetch(API_URL1, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(chapter)
        });

        if (response.ok) {
            alert('Capítulo creado con éxito');
            document.getElementById('createChapterForm').reset();
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
        const response = await fetch(`${API_URL1}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(chapter)
        });

        if (response.ok) {
            alert('Capítulo actualizado con éxito');
            document.getElementById('createChapterForm').reset();
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
        const response = await fetch(`${API_URL1}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Capítulo eliminado con éxito');
            document.getElementById('createChapterForm').reset();
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
        const response = await fetch(API_URL1);
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
// document.getElementById('patchChapterForm').addEventListener('submit', async (e) => {
//     e.preventDefault();
    
//     const id = document.getElementById('patchId').value;
//     const updates = {
//         survey: {
//             id: document.getElementById('patchSurveyId').value
//         },
//         chapter_number: document.getElementById('patchChapterNumber').value,
//         chapter_title: document.getElementById('patchChapterTitle').value
//     };
    
//     try {
//         const response = await fetch(`${API_URL1}/${id}`, {
//             method: 'PATCH',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify(updates)
//         });

//         if (response.ok) {
//             alert('Capítulo parcialmente actualizado con éxito');
//             document.getElementById('createChapterForm').reset();
//             fetchChapters();
//         } else {
//             throw new Error('Error al actualizar el capítulo');
//         }
//     } catch (error) {
//         alert(error.message);
//     }
// });



async function fetchChaptersIn(id){
    try {
        const response = await fetch('http://localhost:8080/api/chapter');
        const chapters = await response.json();
        
        const chapterselect = document.getElementById(id);
        chapterselect.innerHTML = '<option value="">Selecciona una Capitulo</option>'; // Limpiar opciones actuales
        
        chapters.forEach(chapter => {
            const option = document.createElement('option');
            option.value = chapter.id;
            option.textContent = `${chapter.chapter_number} - ${chapter.chapter_title}`;
            chapterselect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener encuestas:', error);
    }
}


// Cargar encuestas y capítulos al inicio
document.addEventListener('DOMContentLoaded', () => {
    fetchSurveys();
    fetchChapters();
    fetchChaptersIn('updateId');
    fetchChaptersIn('deleteChapterId');
    fetchChaptersIn('questionChapterId');
});


