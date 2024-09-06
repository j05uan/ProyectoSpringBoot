const API_URL4 = 'http://localhost:8080/api/question';

// Crear pregunta
document.getElementById('createQuestionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const question = {
        chapter: {
            id: document.getElementById('chapterId').value 
        },
        at: {
            createdAt: new Date().toISOString(),
            updatedAt: new Date().toISOString()
        },
        question_number: document.getElementById('questionNumber').value,
        response_type: document.getElementById('responseType').value,
        comment_question: document.getElementById('commentQuestion').value,
        question_text: document.getElementById('questionText').value
    };
    
    try {
        const response = await fetch(API_URL4, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(question)
        });

        if (response.ok) {
            alert('Pregunta creada con éxito');
            document.getElementById('createQuestionForm').reset();
            fetchQuestions();
        } else {
            throw new Error('Error al crear la pregunta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Actualizar pregunta
document.getElementById('updateQuestionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('updateId').value;
    const question = {
        chapter: {
            id: document.getElementById('updateChapterId').value
        },
        question_number: document.getElementById('updateQuestionNumber').value,
        response_type: document.getElementById('updateResponseType').value,
        comment_question: document.getElementById('updateCommentQuestion').value,
        question_text: document.getElementById('updateQuestionText').value
    };
    
    try {
        const response = await fetch(`${API_URL4}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(question)
        });

        if (response.ok) {
            alert('Pregunta actualizada con éxito');
            document.getElementById('createQuestionForm').reset();
            fetchQuestions();
        } else {
            throw new Error('Error al actualizar la pregunta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Eliminar pregunta
document.getElementById('deleteQuestionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('deleteId').value;
    
    try {
        const response = await fetch(`${API_URL4}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Pregunta eliminada con éxito');
            document.getElementById('createQuestionForm').reset();
            fetchQuestions();
        } else {
            throw new Error('Error al eliminar la pregunta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Obtener todas las preguntas
async function fetchQuestions() {
    try {
        const response = await fetch(API_URL4);
        const questions = await response.json();
        
        const questionList = document.getElementById('questionList');
        questionList.innerHTML = '';
        
        questions.forEach(question => {
            const li = document.createElement('li');
            li.textContent = `ID: ${question.id}, Número: ${question.question_number}, Texto: ${question.question_text}`;
            questionList.appendChild(li);
        });
    } catch (error) {
        console.error('Error al obtener preguntas:', error);
    }
}

// Actualizar pregunta con PATCH
document.getElementById('patchQuestionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('patchId').value;
    const updates = {
        response_type: document.getElementById('patchResponseType').value,
        comment_question: document.getElementById('patchCommentQuestion').value,
        question_text: document.getElementById('patchQuestionText').value
    };
    
    try {
        const response = await fetch(`${API_URL4}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updates)
        });

        if (response.ok) {
            alert('Pregunta parcialmente actualizada con éxito');
            document.getElementById('createQuestionForm').reset();
            fetchQuestions();
        } else {
            throw new Error('Error al actualizar la pregunta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Obtener capítulos para el selector
async function fetchChapters() {
    try {
        const response = await fetch('http://localhost:8080/api/chapters');
        const chapters = await response.json();
        
        const chapterSelect = document.getElementById('chapterId');
        chapterSelect.innerHTML = '<option value="">Selecciona un capítulo</option>'; // Limpiar opciones actuales
        
        chapters.forEach(chapter => {
            const option = document.createElement('option');
            option.value = chapter.id;
            option.textContent = `Número: ${chapter.chapter_number} - Título: ${chapter.chapter_title}`;
            chapterSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener capítulos:', error);
    }
}

// Cargar preguntas y capítulos al inicio
fetchQuestions();
fetchChapters();
