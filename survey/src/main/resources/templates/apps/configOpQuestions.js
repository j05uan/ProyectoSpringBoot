const API_URL2 = 'http://localhost:8080/api/response_options';

// Crear opción de respuesta
document.getElementById('createResponseOptionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const responseOption = {
        option_value: document.getElementById('optionValue').value,
        categories: {
            id: document.getElementById('categoryId').value
        },
        at: {
            createdAt: new Date().toISOString(),
            updatedAt: new Date().toISOString()
        },
        responseOptions: {
            id: document.getElementById('parentResponseId').value || null
        },
        question: {
            id: document.getElementById('questionId').value
        },
        comment_response: document.getElementById('commentResponse').value,
        option_text: document.getElementById('optionText').value
    };
    
    try {
        const response = await fetch(API_URL2, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(responseOption)
        });

        if (response.ok) {
            alert('Opción de respuesta creada con éxito');
            fetchResponseOptions();
        } else {
            throw new Error('Error al crear la opción de respuesta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Actualizar opción de respuesta
document.getElementById('updateResponseOptionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('updateId').value;
    const responseOption = {
        option_value: document.getElementById('updateOptionValue').value,
        categories: {
            id: document.getElementById('updateCategoryId').value
        },
        responseOptions: {
            id: document.getElementById('updateParentResponseId').value || null
        },
        question: {
            id: document.getElementById('updateQuestionId').value
        },
        comment_response: document.getElementById('updateCommentResponse').value,
        option_text: document.getElementById('updateOptionText').value
    };
    
    try {
        const response = await fetch(`${API_URL2}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(responseOption)
        });

        if (response.ok) {
            alert('Opción de respuesta actualizada con éxito');
            fetchResponseOptions();
        } else {
            throw new Error('Error al actualizar la opción de respuesta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Eliminar opción de respuesta
document.getElementById('deleteResponseOptionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('deleteId').value;
    
    try {
        const response = await fetch(`${API_URL2}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            alert('Opción de respuesta eliminada con éxito');
            fetchResponseOptions();
        } else {
            throw new Error('Error al eliminar la opción de respuesta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Obtener todas las opciones de respuesta
async function fetchResponseOptions() {
    try {
        const response = await fetch(API_URL2);
        const responseOptions = await response.json();
        
        const responseOptionList = document.getElementById('responseOptionList');
        responseOptionList.innerHTML = '';
        
        responseOptions.forEach(option => {
            const li = document.createElement('li');
            li.textContent = `ID: ${option.id}, Valor: ${option.option_value}, Texto: ${option.option_text}`;
            responseOptionList.appendChild(li);
        });
    } catch (error) {
        console.error('Error al obtener opciones de respuesta:', error);
    }
}

// Actualizar opción de respuesta con PATCH
document.getElementById('patchResponseOptionForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const id = document.getElementById('patchId').value;
    const updates = {
        option_value: document.getElementById('patchOptionValue').value,
        comment_response: document.getElementById('patchCommentResponse').value,
        option_text: document.getElementById('patchOptionText').value
    };
    
    try {
        const response = await fetch(`${API_URL2}/${id}`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updates)
        });

        if (response.ok) {
            alert('Opción de respuesta parcialmente actualizada con éxito');
            fetchResponseOptions();
        } else {
            throw new Error('Error al actualizar la opción de respuesta');
        }
    } catch (error) {
        alert(error.message);
    }
});

// Obtener categorías para el selector
async function fetchCategories() {
    try {
        const response = await fetch('http://localhost:8080/api/categories_catalog');
        const categories = await response.json();
        
        const categorySelect = document.getElementById('categoryId');
        categorySelect.innerHTML = '<option value="">Selecciona una categoría</option>'; // Limpiar opciones actuales
        
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.id;
            option.textContent = `Categoría: ${category.name}`; // Ajusta según el campo que tengas
            categorySelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener categorías:', error);
    }
}

// Obtener preguntas para el selector
async function fetchQuestions() {
    try {
        const response = await fetch('http://localhost:8080/api/questions');
        const questions = await response.json();
        
        const questionSelect = document.getElementById('questionId');
        questionSelect.innerHTML = '<option value="">Selecciona una pregunta</option>'; // Limpiar opciones actuales
        
        questions.forEach(question => {
            const option = document.createElement('option');
            option.value = question.id;
            option.textContent = `Pregunta: ${question.question_text}`; // Ajusta según el campo que tengas
            questionSelect.appendChild(option);
        });
    } catch (error) {
        console.error('Error al obtener preguntas:', error);
    }
}

// Cargar opciones de respuesta, categorías y preguntas al inicio
fetchResponseOptions();
fetchCategories();
fetchQuestions();
